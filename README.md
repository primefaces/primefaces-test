![PrimeFaces icon](https://www.primefaces.org/wp-content/uploads/2016/10/prime_logo_new.png)

# PrimeFaces Test

This is a sample maven project that uses <strong>Latest PrimeFaces Release</strong> version. If you have a PrimeFaces issue, please download or fork this project. Then, you should change these files by yourself so that PrimeFaces Team can see your problem. Finally, you can send a link or attach the project. <strong>Please make sure that project is runnable with the command below.</strong>

You can execute the sample with <strong>mvn jetty:run</strong> command and hit <strong>http://localhost:8080/</strong> to run the page.

### Jakarta EE10 Version
***

PrimeFaces Test is setup to run again Jakarta EE10 profile using Jetty 12. You can also use other versions with the available maven profiles: mojarra40, myfaces40

`mvn clean jetty:run -Pmojarra40`

`mvn clean jetty:run -Pmyfaces40`

### Server Port
***

By default the application runs on port 8080 but if you would like to use a different port you can pass `-Djetty.port=5000` like:

`mvn clean jetty:run -Djetty.port=5000`


### JPA Lazy Datatable
***

The branch `jpa` contains a PrimeFaces Test setup to run with JPA using the JPA LazyDatatable advanced example.

### Legacy JSF Versions
***

The branch `javax` contains a PrimeFaces Test setup to run again Jakarta EE10 profile using Jetty 9. Per default the application uses Mojarra 2.3.x. 
You can also use other versions with the available maven profiles: myfaces23, myfaces23next, mojarra23

`mvn clean jetty:run -Pmyfaces23`

`mvn clean jetty:run -Pmyfaces23next`

`mvn clean jetty:run -Pmojarra23`

### Visual Studio Code Quickstart
***

See the [quickstart guide for running in Visual Studio Code](./vscode-quickstart.md) for more information.
