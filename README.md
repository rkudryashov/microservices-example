[![Build Status](https://travis-ci.org/rkudryashov/microservices-example.svg?branch=master)](https://travis-ci.org/rkudryashov/microservices-example)

# Microservices example
This is an example of microservice architecture pattern implemented with following stack of Java-technologies:
* JDK 11
* Kotlin
* Spring Cloud
  * Spring Cloud Gateway
  * Spring Cloud Config
  * Spring Cloud Sleuth
  * Spring Cloud OpenFeign
  * Spring Cloud Netflix
    * Eureka
    * Hystrix
    * Ribbon
* Gradle with Kotlin DSL
* JUnit
* Other: Thymeleaf, Bootstrap, Webjars

### Diagram of components
![-](/etc/images/diagram.png)

### Config server
Stores [configs](/config-server/src/main/resources/config) of all microservices.

### Service discovery server
Performs Service registry function. Spring Cloud Netflix Eureka is used which is [Client-side service discovery](https://microservices.io/patterns/client-side-discovery.html). 
Each application sends heartbeats to Eureka server after registration.

### Items service
This is an example of back-end with [REST API](/items-service/src/main/kotlin/io/microservicesexample/itemsservice/RestApi.kt) 
implemented with Spring WebFlux. HTTP requests are processed with 
[handler](/items-service/src/main/kotlin/io/microservicesexample/itemsservice/ItemHandler.kt). Also microservice demonstrates 
example of [sending additional metadata](/items-service/src/main/kotlin/io/microservicesexample/itemsservice/EurekaAdditionalMetadataReporter.kt).

### Items UI
This is an example of front-end which uses Thymeleaf for HTML-pages rendering and Bootstrap CSS framework (plugs in with 
help of Webjars). Application contains a few examples of interacting with back-end (Items service) by using different API:
* [using](items-ui/src/main/kotlin/io/microservicesexample/itemsui/service/ItemsServiceClient.kt) RestTemplate
* [using](items-ui/src/main/kotlin/io/microservicesexample/itemsui/service/ItemsServiceClient.kt) WebClient
* [using](items-ui/src/main/kotlin/io/microservicesexample/itemsui/service/ItemsServiceFeignClient.kt) FeignClient and 
Hystrix

You can test all of these approaches by request to `http://localhost:8081/example`.

You can test Hystrix fallback working by request to `http://localhost:8081/hystrix-fallback`.

### UI gateway
Performs authentication and routing to UI microservices (only Items UI in this project). Routing implemented with Spring 
Cloud Gateway which settings as well as other settings are stored in [YAML-config](microservices-example/config-server/src/main/resources/config/ui-gateway.yml). 
Also application contains [route to Eureka](ui-gateway/src/main/kotlin/io/microservicesexample/uigateway/config/RoutesConfig.kt) 
accessed by `https://localhost/eureka`. All routes you can see by request to `https://localhost/actuator/gateway/routes`. Access to 
login page [implements](ui-gateway/src/main/kotlin/io/microservicesexample/uigateway/config/RoutesConfig.kt) using WebFlux. 
You can perform authentication with one of [hardcoded](ui-gateway/src/main/kotlin/io/microservicesexample/uigateway/config/SecurityConfig.kt) 
users. Application passes user's login and roles by [adding](ui-gateway/src/main/kotlin/io/microservicesexample/uigateway/misc/AddCredentialsGlobalFilter.kt) 
it to HTTP request headers. You can request to `https://localhost/items-ui/greeting` to see passed credentials. Sure you 
can access all other endpoints provided by Items UI. As well as Items UI this application uses Thymeleaf and Bootstrap for UI purposes.

##### SSL
`ui-gateway` works on port 443 and is available with HTTPS protocol. SSL certificate was created by using following command:
```
keytool -genkey -alias test_key -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
```
and with *qwerty* password.

##### Distributed tracing
![-](/etc/images/sleuth_tracing.png)

This schema is simple, see [here](https://spring.io/projects/spring-cloud-sleuth) for details.

### Building
Depending of your OS: `gradlew clean build` or `./gradlew clean build`.

Used Gradle 5 requires at least JDK 8.

### Running
Run all microservices in the same order as they are listed. If you are using Intellij IDEA with enabled Run Dashboard you 
should get something like:

![-](/etc/images/run_dashboard.png)
