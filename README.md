# Microservices example
This is example of microservice architecture pattern

### Example consist of 5 microservices
![-](/etc/diagram.png)

### Implemented with
* Java 11
* Kotlin
* Spring Cloud
* Gradle Kotlin DSL
* JUnit

### Other used technologies
* Webjars

### SSL
`ui-gateway` works on port 443. SSL certificate was created by using following command:
```
keytool -genkey -alias test_key -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
```
and with *qwerty* password.