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
