![PrimeFaces icon](https://www.primefaces.org/wp-content/uploads/2016/10/prime_logo_new.png)


This is a sample maven project that uses <strong>Latest PrimeFaces Release version</strong> and the jakarta-namespace (Jakarta EE 9+) instead of the javax-namespace (Java EE 8). If you have a PrimeFaces issue, please download or fork this project. Then, you should change these files by yourself so that PrimeFaces Team can see your problem. Finally, you can send a link or attach the project. <strong>Please make sure that project is runnable with the command below.</strong>

You can execute the sample with <strong>mvn jetty:run</strong> command and hit <strong>http://localhost:8080/primefaces-test</strong> to run the page.

Per default the application uses Mojarra 4.0.x. 
You can also use other versions with the available maven profiles: myfaces40, mojarra40

`mvn clean jetty:run -Pmyfaces40`

`mvn clean jetty:run -Pmojarra40`
