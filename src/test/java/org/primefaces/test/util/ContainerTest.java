package org.primefaces.test.util;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.glassfish.embeddable.CommandResult;
import org.glassfish.embeddable.CommandRunner;
import org.glassfish.embeddable.GlassFishException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Arquillian + Payara unit test.
 *
 * @author David Matějček
 */
@RunWith(Arquillian.class)
@ArquillianSuiteDeployment
public abstract class ContainerTest {

  private static final Logger LOG = LoggerFactory.getLogger(ContainerTest.class);
  private static final Pattern P_JAR_FILE_NAME = Pattern.compile("\\-[0-9\\.]*(-SNAPSHOT)?\\.");

  private static boolean containerInitialized;
  private static final Properties properties = new Properties();
  private long start;

  /**
   * JUnit rule to know name of the current test.
   */
  @Rule
  public final TestName name = new TestName();

  /**
   * JUnit rule to log stacktraces of all exceptions.
   */
  @Rule
  public final TestWatcher exceptionLogger = new TestWatcher() {

      @Override
      protected void failed(final Throwable e, final Description description) {
          LOG.error(description.getDisplayName() + " failed!", e);
          super.failed(e, description);
      }
  };


  /**
   * Only to mark the class initialization in logs
   */
  @BeforeClass
  public static void initContainer() {
      LOG.info("initContainer()");
  }


  /**
   * Initialize test class.
   *
   * @return {@link EnterpriseArchive} to deploy to the container.
   * @throws Exception exception
   */
  @Deployment
  public static WebArchive getArchiveToDeploy() throws Exception {
      if (!containerInitialized) {
          initEnvironment();
          containerInitialized = true;
      }

      final File xhtmlFiles = new File(properties.getProperty("xhtmlfiles"));
      final Path relComponentPath = Paths.get("resources", "components", "materialDialogContent.xhtml");
      final WebArchive war = ShrinkWrap.create(WebArchive.class, "x.war") //
          .addPackages(true, "org.primefaces.webapp")
          .addAsResource("log4j.properties")
          .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
          .addAsWebInfResource("log4j.properties", "log4j.properties")
          .addAsWebInfResource("WEB-INF/web.xml", "web.xml")
          .addAsWebInfResource("WEB-INF/faces-config.xml", "faces-config.xml")
          .addAsWebResource(new File(xhtmlFiles, "autoCompleteTest.xhtml"))
          .addAsWebResource(new File(xhtmlFiles, "index.xhtml"))
          .addAsWebResource(xhtmlFiles.toPath().resolve(relComponentPath).toFile(), relComponentPath.toString())
      ;//

      final PomEquippedResolveStage pom = Maven.resolver().loadPomFromFile("pom.xml")
          .importCompileAndRuntimeDependencies();
      final List<File> files = new ArrayList<>();
      final File[] resolved = pom.resolve().withoutTransitivity().asFile();
      files.addAll(Arrays.asList(resolved));
      for (final File file : files) {
          war.addAsLibrary(file, P_JAR_FILE_NAME.matcher(file.getName()).replaceFirst("."));
      }
      war.as(ZipExporter.class).exportTo(new File(properties.getProperty("targetDirectory"), "x.war"), true);
      LOG.info(war.toString(true));
      return war;
  }




  /**
   * @return name of the test method.
   */
  public String getTestName() {
      return this.name.getMethodName();
  }


  /**
   * @return test configuration.
   */
  public static Properties getTestProperties() {
      // initEnvironment() is invoked only by test classloader
      // but the properties are used also from the test-war file.
      if (properties.isEmpty()) {
          loadProperties();
      }
      return properties;
  }


  /**
   * Logs the test name and checks access to the browser.
   */
  @Before
  public void before() {
      LOG.info("before(). Test name: {}", this.name.getMethodName());
      this.start = System.currentTimeMillis();
  }


  /**
   * Logs the test name, test time and the source html of the last page.
   *
   * @throws Exception
   */
  @After
  public void after() throws Exception {
      LOG.info("after(). Test name: {}, test time: {} ms", this.name.getMethodName(),
          System.currentTimeMillis() - this.start);
  }


  private static void loadProperties() {
      try (
          final InputStream testPropsIs = ContainerTest.class.getClassLoader().getResourceAsStream("test.properties")) {
          properties.load(testPropsIs);
      } catch (final Exception e) {
          throw new IllegalStateException("Cannot load test.properties from classpath!", e);
      }
  }


  private static void initEnvironment() throws Exception {
      LOG.debug("initEnvironment()");
      LOG.debug("System properties:\n  {}", System.getProperties());

      loadProperties();

      try {
          runCommand("set", "configs.config.server-config.jms-service.type=DISABLED");
          runCommand("set", "configs.config.server-config.admin-service.das-config.deploy-xml-validation=none");
          runCommand("set", "configs.config.server-config.iiop-service.iiop-listener.orb-listener-1.port=17300");
          runCommand("set", "configs.config.server-config.iiop-service.iiop-listener.SSL.port=17301");
          runCommand("set", "configs.config.server-config.iiop-service.iiop-listener.SSL_MUTUALAUTH.port=17302");
      } catch (final Exception e) {
          throw new IllegalStateException(e);
      }
  }


  /**
   * Execute the command with parameters and return a result.
   *
   * @param command
   * @param parameters
   * @return result of the command
   * @throws GlassFishException - cannot communicate with the instance
   * @throws IllegalStateException - invalid parameters or command
   */
  private static CommandResult runCommand(final String command, final String... parameters) throws GlassFishException {
      LOG.debug("runCommand(command={}, parameters={})", command, parameters);

      final CommandRunner runner;
      try {
          final InitialContext ctx = new InitialContext();
          runner = (CommandRunner) ctx.lookup(CommandRunner.class.getCanonicalName());
          Objects.requireNonNull(runner, "No command runner instance found in initial context!");
      } catch (final NamingException e) {
          throw new IllegalStateException("Cannot run command " + command, e);
      }

      final CommandResult result = runner.run(command, parameters);
      checkCommandResult(command, result);
      return result;
  }


  private static void checkCommandResult(final String cmd, final CommandResult result) {
      LOG.info("Command: {}\n  Result.status:\n  {}\n  Result.out:\n  {}\n  Result.failCause:\n  {}\n", cmd,
          result.getExitStatus(), result.getOutput(), result.getFailureCause());

      if (result.getExitStatus().ordinal() != 0) {
          throw new IllegalStateException("Command '" + cmd + "' was unsuccessful: " + result.getOutput(),
              result.getFailureCause());
      }
  }

}
