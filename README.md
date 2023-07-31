# Device Assigning

The following was discovered as part of building this project:

* The JVM level was changed from '11' to '17', review
  the [JDK Version Range](https://github.com/spring-projects/spring-framework/wiki/Spring-Framework-Versions#jdk-version-range)
  on the wiki for more details.

# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.2/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#data.sql.jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#web)

### Guides

The following guides illustrate how to use some features used in the project concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Swagger document can be found in

``` 
  http://localhost:8080/swagger-ui/index.html
``` 
### Actuator  endpoint is exposed via the following url

  ``` 
  http://localhost:8080/actuator
 ``` 

### Setup and Run

* Build and install
  ```
    mvn clean install
  ```
* Run application 
  ```
  java -jar target/patro-0.0.1-SNAPSHOT.jar
  ```
### Missing things considering time spent
* some unit tests is missing.
* Some kind of centralized error handling with ErrorHandlingControllerAdvice.
* I just kept one resource for the simple but that could be bifurcated into multiple considering more complexity.
* Create a container based application for cloud readiness.
* Monitoring using promethium and Grafana.