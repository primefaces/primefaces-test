![PrimeFaces icon](https://www.primefaces.org/wp-content/uploads/2016/10/prime_logo_new.png)


This is a sample maven project that uses <strong>Latest PrimeFaces Release version</strong>. If you have a PrimeFaces issue, please download or fork this project. Then, you should change these files by yourself so that PrimeFaces Team can see your problem. Finally, you can send a link or attach the project. <strong>Please make sure that project is runnable with the command below.</strong>

You can execute the sample with <strong>mvn jetty:run</strong> command and hit <strong>http://localhost:8080/primefaces-test</strong> to run the page.

Per default the application uses Mojarra 2.2.x running in Jetty w/ Apache OpenWebBeans. 
You can also use other JSF implementation versions with the available maven profiles: myfaces22, myfaces23, mojarra23

`mvn clean jetty:run -Pjetty-owb,myfaces22`

`mvn clean jetty:run -Pjetty-owb,myfaces23`

`mvn clean jetty:run -Pjetty-owb,mojarra22`

`mvn clean jetty:run -Pjetty-owb,mojarra23`

A maven profile also exists which allows the test application to be run in Payara5 with its own embedded Mojarra

`mvn clean package payara-micro:start -Ppayara5`
