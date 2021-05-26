![PrimeFaces icon](https://www.primefaces.org/wp-content/uploads/2016/10/prime_logo_new.png)


This is a sample maven project that used <strong>PF10.0.0</strong> version. If you have a PrimeFaces issue, please download or fork this project. Then, you should change these files by yourself so that PrimeFaces Team can see your problem. Finally, you can send a link or attach the project. <strong>Please make sure that project is runnable with the command below.</strong>

You can execute the sample with <strong>mvn jetty:run</strong> command and hit <strong>http://localhost:8080/primefaces-test</strong> to run the page.

This fork shows the issues I'm having trying to use the auto, multi file, advanced fileupload component.  
Please take a look at my bean code. I'm pretty much using the example that is in the showcase.  
* Select a set of files (csv) and these are added to map which file names (key values) are then displayed  
to the associated data table.
* Clear button will delete prior set of files and reset the fileupload to enabled... no files should be displayed.
* Select another set of files.

My findings are that I'm getting irregular results from the file upload selections, mostly incorrect.  
If someone could please advise me on what I might be doing incorrectly and how to fix? I believe I'm doing this as I should?


Per default the application uses Mojarra 2.2.x. 
You can also use other versions with the available maven profiles: myfaces22, myfaces23, mojarra23

`mvn clean jetty:run -Pmyfaces22`

`mvn clean jetty:run -Pmyfaces23`

`mvn clean jetty:run -Pmojarra22`

`mvn clean jetty:run -Pmojarra23`
