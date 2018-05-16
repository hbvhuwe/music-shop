# music-shop

## Project consist of several parts:
 - music-shop-client (client standalone application)
 - music-shop-web (client web application)
 - music-shop-server (REST server of music shop)

### Music shop client JavaFX application can use type of connections:
 - [JDBC](https://ru.wikipedia.org/wiki/Java_Database_Connectivity)
 - [JPA(Hibernate)](http://hibernate.org/)
 - through the [REST API](https://ru.wikipedia.org/wiki/REST)

Used libraries:
 - [Retrofit](http://square.github.io/retrofit/)
 - [Gson](https://github.com/google/gson)
 - [Hibernate](http://hibernate.org/)
 - [JUnit](https://junit.org/junit4/)
 - MySQL connector

### Music shop web application uses JPA provided by [EclipseLink](https://ru.wikipedia.org/wiki/EclipseLink)
Used libraries:
 - javax.persistence
 - EclipseLink
 - MySQL connector
 - [Java Servlet API](https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api)

### Music shop REST server uses JPA connection and [Jersey](https://jersey.github.io/)
Used libraries:
 - [Gson](https://github.com/google/gson)
 - [Jersey](https://jersey.github.io/)
 - javax.persistence
 - EclipseLink
 - MySQL connector
 - [Java Servlet API](https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api)
