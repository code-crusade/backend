# backend

## Installing

Install with ```mvn clean install```

## Running

Run with ```docker-compose up```

You may need to add ```--no-cache``` when rebuilding, as the dockerfiles may not be optimal.

## Services

Some services are from the *cloud* family of Spring projects and are useful here, even for a single instance app like this.
We can take advantage of an edge service, a service registry and the flexibilty of the concept of cloud configs.

### mysql-service

This is the MySQL service, mapped to a volume mounted.

### config-service

The Spring Cloud Config service instance. It is setup with a local (empty) config file setup.

[See here for details.](https://cloud.spring.io/spring-cloud-config/)

### eureka-service

The service discovery server.

[Eureka services and clients explained.](https://stackoverflow.com/a/36558205/8377347)

### zuul-gateway

The proxy gateway and edge service.

[Nicely explained here](https://dzone.com/articles/microservices-communication-zuul-api-gateway-1)

### codecrusade-application

The actual application.

## TODOS

* Move the security from the application to the edge service;
* Regenerate server stubs with updated api spec and adapt model mappings;
  * Then add demo data;
* Add routes from zuul to link to app;
* Add SSL + mechanism to substitute with ETS certificates.



**Remember to update the liquibase migrations on changing entities**
