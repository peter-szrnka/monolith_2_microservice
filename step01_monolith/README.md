# Step 1: Monolith web application
This is my sample application to demonstrate a classical monolithic application. This is the structure, what I'm currently using at my actual job. I'll do a refactor from Step 2, where I'll modify it to smaller microservices.

## Current status

| Build | Code coverage |
| ------------- | ------------- |

## Used technologies
- Spring Boot
- H2 and Redis (database)
- Junit with Mockito and PowerMockito

## Service Structure

The application contains 3 REST based webservice:

- BookService
- OrderService
- PaymentService