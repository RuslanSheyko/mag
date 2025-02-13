# Mag Service Tests Project

Mag service is a Spring Boot application created for learning spring. 
---

### Technologies / Changes

* Java 21
* Spring Boot 3.2(with virtual threads)
* Maven
* Use docker-compose
* TestContainers for Integration Tests
* JDBC Client
* Swagger API documentation
* TODO: Monitoring and Observability using Spring Boot Actuator and Micrometer

---

### How to Build

- Navigate to the root directory and run: `./mvn clean install`

### How to Run

1. Change to `/scripts`
2. run `./start-services.sh`
3. Run `./create-database.sh`
4. Launch the application using: `mvn clean install spring-boot:run`
5. To interact with the database, you can run:
    - `docker ps` to obtain the Container ID for the postgres image, then execute:
    - `docker exec -it ${containerId} psql mag -U postgres`
    - While in the container, run `\dt mag.` to view the list of created tables.

### API Documentation

Swagger: Access the Swagger API documentation
at: [http://localhost:8080/mag-docs/swagger-ui/index.html](http://localhost:8080/mag-docs/swagger-ui/index.html) when
the application is running.


[Postman Collection](mag.postman_collection.json)

[Sequence diagram](mag-sequence-diagram.png)

---

### Design Decisions

**Backend Service**
I have used Spring Boot 3.2(Spring MVC), which was released recently and added support for Virtual Threads on JDK 21.
(To use Virtual Threads, I just set the property `spring.threads.virtual.enabled` to `true`.)

Our Tomcat will use virtual threads for HTTP requests, means our application runs on virtual threads to achieve high throughput.

**JDBC Client** is used, since Spring Framework 6.1 introduced JDBC Client that gives us a fluent API for talking to a database.

**API Clients**
* Integration tests to call the mag APIs.
* Swagger is used to call the endpoints.
* Postman collection are also included.

**Service(Client) to service(backend) communication**
There are two ways to call backend APIs:

* Spring MVC app(Servlet stack)
    * old way: Rest template(feature complete; a lot of overloaded methods(confusing) for making service call)
    * new way: Rest Client(Fluent API)

* Spring Webflux(Reactive stack)
  Web Client(Fluent API)

In a real-world application, we might need to have a separate (frontend) application to interact with backend service.

---

#### Metrics

I recommend to set up Prometheus as the monitoring backend and Grafana for creating informative dashboards to visualize and analyze data.
Metrics should be exposed through HTTP, and Prometheus can be configured to scrape data at regular intervals from the /prometheus endpoint.

We can take advantage of Spring Boot production-ready features that are packed in a module
called actuator https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html.
