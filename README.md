# Microservices example
This is example of microservice architecture pattern

### Example consist of 5 microservices
![-](/etc/images/diagram.png)

### Config server
This microservice stores configs of all microservices

### Service discovery server
Eureka server is used

### Items service
Example of backend

### Items UI
Example of UI

### UI gateway
This microservice performs authentication and routing to UI microservice

##### SSL
`ui-gateway` works on port 443. SSL certificate was created by using following command:
```
keytool -genkey -alias test_key -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
```
and with *qwerty* password.

##### Distributed tracing
![-](/etc/images/sleuth_tracing.png)

### Building
`gradlew clean build`

### Running
Run all microservices in the same order as they are listed. You should get something like:

![-](/etc/images/run_dashboard.png)

### Implemented with
* Java 11
* Kotlin
* Spring Cloud (Spring Cloud Gateway, Spring Cloud Sleuth, Netflix (Eureka, Hystrix, Feign, Ribbon))
* Gradle Kotlin DSL
* JUnit

### Other used technologies
* Webjars
* Thymeleaf
