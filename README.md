# Monolith service demo

This is my sample application to demonstrate a classical monolithic application. This is the structure, what I'm currently using at my actual job. I'll do a refactor on another repository.

## About the application
This application represents a fictive Book store with a simple order management and a payment service. The books can be saved, deleted, listed, and displayed via REST API. The orders and payments can be initiated, finalized, and cancelled. It's not a fully functioning online store, it's just demonstrates a simple,
monolithic system.

## Used technologies
- Java 8
- Spring Boot
- Redis, H2
- Project Lombok
- JUnit, Mockito

## Run

To run the application, open a command line and type in the following command:

> java -jar monolith-service-0.0.1-SNAPSHOT.jar

## Databases
The application has a switchable DAO layer with 2 implementation: Redis and H2.

## Redis
In this project I'll use Redis as a primary database with Docker for Windows 10.

### Installation
> docker run --name some-redis -d redis

### Run
Start a command line and type in the following command:
> docker run -p 16379:6379 --name redis-server -d redis --requirepass "mypass"

If you want to use it with a persistent storage, use the next command:
> docker run -p 16379:6379 --name redis-server -d redis --requirepass "mypass" --appendonly yes

## H2
This is just for demonstrating the flexibility of the DAO layer and for running unittests.