package org.primefaces.test.util;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertNotNull;

/**
 * Arquillian + Payara + Selenium + Graphene unit test.
 *
 * @author David Matějček
 */
public abstract class SeleniumTest extends ContainerTest {

    private static final Logger LOG = LoggerFactory.getLogger(SeleniumTest.class);

    /**
     * Access to the browser windows.
     */
    @Drone
    protected WebDriver browser;

    /**
     * Access to the user - browser keyboard and mouse interactions.
     */
    protected transient Actions userActions;

    /**
     * URL including the application context
     */
    @ArquillianResource
    protected URL contextUrl;

    /**
     * Element (div) with global messages.
     */
    @FindBy(id = "messages")
    protected WebElement messages;

    /**
     * Logs the test name and checks access to the browser.
     */
    @Override
    @Before
    public void before() {
        super.before();
        assertNotNull("this.browser", this.browser);
        this.userActions = new Actions(this.browser);
        LOG.info("context url={}", this.contextUrl);
        this.browser.manage().window().maximize();
        this.browser.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
        this.browser.manage().timeouts().setScriptTimeout(1, TimeUnit.MILLISECONDS);
//        this.userActions.pause(Duration.ofSeconds(3)).perform();
        this.browser.get(this.contextUrl.toExternalForm() + "/index.xhtml");
    }


    /**
     * Logs the test name, test time and the source html of the last page.
     *
     * @throws Exception
     */
    @Override
    @After
    public void after() throws Exception {
        LOG.info("source: \n{}", this.browser.getPageSource());
        super.after();
//        final File src = ((TakesScreenshot) this.browser).getScreenshotAs(OutputType.FILE);
//        final File file = new File(getTestProperties().getProperty("targetDirectory") +
//            File.separator + "screenshots",
//            getClass().getName() + "_" + getTestName() + ".jpg");
//        FileUtils.copyFile(src, file);
//        LOG.info("Screenshot saved to file: {}", file);
    }
}
