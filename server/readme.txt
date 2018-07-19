This project and the the project client are both from the online example at
https://developer.okta.com/blog/2017/12/04/basic-crud-angular-and-spring-boot.

The example is to integrate Spring boot 2 and Angular 5. 

The server is written using Spring boot and runs on localhost:8080.

The client is written using Angular 5 and runs on localhost:4200.

The client calls a service that gets data from localhost:8080. So there is a cross-origin issue. The server
implements the cross-origin to allow the action.  