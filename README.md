![PrimeFaces icon](https://www.primefaces.org/wp-content/uploads/2016/10/prime_logo_new.png)

# PrimeFaces Test

This is a sample maven project that uses **Latest PrimeFaces Release** version. If you have a PrimeFaces issue, please download or fork this project. Then, you should change these files by yourself so that PrimeFaces Team can see your problem. Finally, you can send a link or attach the project. **Please make sure that the project is runnable with the command below**.

You can execute the sample against Jetty/OpenWebBeans with the `mvn jetty:run` maven command and navigate to **http://localhost:8080/** to access the page.

This project is configured to produce Java bytecode compatible with Java 11 and higher.  Java versions before Java 11 are no longer supported.

### Jakarta EE10 Version
***

PrimeFaces Test is set up by default to run against Jakarta EE10 profile using Jetty 12. You can also use other JSF implementations with the available maven profiles: `mojarra40`, `myfaces40`.  When specifying additional maven profiles, it is necessary to also explicitly include specification of the `jetty` profile.

`mvn clean jetty:run -Pjetty,mojarra40`

`mvn clean jetty:run -Pjetty,myfaces40`

### Server Port
***

By default, the application runs on port 8080 but if you would like to use a different port you can pass `-Djetty.port=5000` like:

`mvn clean jetty:run -Djetty.port=5000`

### JPA Lazy Datatable
***

The branch `jpa` contains a PrimeFaces Test setup to run with JPA using the JPA LazyDatatable advanced example.

### Legacy JSF Versions
***

The branch `javax` contains a PrimeFaces Test setup to run against Jakarta EE10 profile using Jetty 9. Per default the application uses Mojarra 2.3.x.
This maven project uses maven profiles to configure web container and JSF implementations/versions.  By default, the profiles for Jetty/OpenWebBeans and the Mojarra 2.3.x JSF implementations are enabled, but it is also possible to use other JSF implementations and versions with combinations of available maven profiles: jetty-owb, myfaces23, myfaces23next, mojarra23

`mvn clean jetty:run -Pjetty,myfaces23`

`mvn clean jetty:run -Pjetty,myfaces23next`

`mvn clean jetty:run -Pjetty,mojarra23`

### Visual Studio Code Quickstart
***

See the [quickstart guide for running in Visual Studio Code](./vscode-quickstart.md) for more information.

### Running with Payara
***

The `payara` branch contain a PrimeFaces Test setup to run against a JakartaEE profile using Payara v6 and its bundled Mojarra implementation of the Faces specification.  When using this setup, it is possible to use the `payara-micro:start` command and navigate to **http://localhost:8080/** to access the page.

Payara v6 will be used, supports JakartaEE v10 with the jakarta package namespace, and works with Java11 through Java21. Use it with the following maven invocation:

`mvn clean verify payara-micro:start -Ppayara6`

A custom port can also be specified:

`mvn clean verify payara-micro:start -Ppayara6 -Dpayara6.port=5000`
