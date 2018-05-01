# Example of microservice architecture
Implemented with:
* Java 10
* Kotlin
* Spring Cloud
* Gradle (DSL on Kotlin)
* JUnit

# SSL
`ui-gateway` works on port 443. SSL certificate was created by using following command:
```
keytool -genkey -alias test_key -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
```
and with *qwerty* password.