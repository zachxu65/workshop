# mvcAngular

This project is to integrate Spring MVC with Angular 5, and build a single war file to deploy to Tomcat8 server. 
Angular 5 is the front end. Spring MVC is back end.  Angular 5 uses HttpClient to call the restful services provided by Spring MVC.
The front end and the back end can be deployed as two separated applications. But this project puts them together. 
 
## The Better Approach for Deploying Angular with Spring MVC

The better approach for deploying the projects is to deploy the Angular application and the Spring MVC separately. The two applications
should be created and built separately. The source files should be in different project folders. 


The Angular 5 project can be built and deployed to Tomcat using the port number 8080. And the Spring MVC project can be built and 
deployed to the same Tomcat running on the same port number 8080 as usual. This is fine because a Tomcat server can contain multiple web applications.


This separation does not work in the case of Spring boot because Spring boot and the Angular 5 application will run in two different
processes. The two processes can not use the same port number. 
 
## The Angular 5 application

This application is based on https://developer.okta.com/blog/2017/12/04/basic-crud-angular-and-spring-boot. 
Some notes about it:
1. It uses angular material package which is not included in the node_modules by default. You need to install it
   using the following command in the "ngapp" directory:
    npm install --save-exact @angular/material @angular/cdk
2. The angular.json file is modified with the following to change the output directory of the build command:
    "outputPath": "../webapp/static"

## The Spring MVC application

This application is based on http://websystique.com/springmvc/spring-mvc-4-angularjs-example.

## Build and Deploy
  Just run maven "-e clean tomcat:deploy" in eclipse. It should create a war file mvcAngular.war and deploy it to the
  Tomcat server. 
  Then open a browser(Chrome, Firefox, Edge, but not IE). Go to the url localhost:8080/mvcAngular/static/index.html
  It should display a list of cars and their images. 
  Note that the functions to add or delete cars are not working yet. But they can be implemented. 