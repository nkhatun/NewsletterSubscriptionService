# NewsletterSubscriptionService
Maven based spring boot application with microservice architecture configuration

## Description
The repository microservices application for the task. 

## Scope
1. USER-SERVICE - Creating users and update newsletter subscription status to SUBSCRIPTION-SERVICE.
2. SUBSCRIPTION-SERVICE - update newsletter subscription status to H2 database and fetch the user subscription details for a user.
3. SUBSCRIPTION-DETAILS-SERVICE - fetch the user subscription details for a user from SUBSCRIPTION-SERVICE

## Technology
- **Spring Boot**     - Server side framework
- **Lombok**          - Provides automated getter/setters
- **Actuator**        - Application insights on the fly
- **Spring Security** - Spring's security layer
- **Swagger**         - In-built swagger2 documentation support
- **Junit**           - Unit testing framework
- **Rest Assured**    - API testing
- **Eureka Client and server**    - service registry
- **Spring Cloud**    - Cloud configuaration
- **Hystrix**    - latency and fault tolerance library
- **Zipkin**    - trace management

## Application Set up and Build Steps
Run the below command from the root directory
1. `mvn clean install -Dmaven.test.skip=true`

## Running the server locally
Run the below command from the root directory
1. `mvn spring-boot:run`

- **Service Registry->** URL: http://<host-name>:8761/ 
- **Could Gateway->**  URL: http://<host-name>:8085/
- **ApiCloudGateway->** URL : http://<host-name>:8083/
- **Hystrixdashboard->** URL: http://<host-name>:8084/
- **NewsletterSubscriptionService->** URL: http://<host-name>:8080/
- **SubscriptionDetailsService->** URL: http://<host-name>:8082/
- **UserSubscriptionService->** URL: http://<host-name>:8081/
  
## Swagger Documentation
Swagger documentation for  can be accessed at the following URL -
1. http://<host-name>:8080/demo-subscriptions/swagger-ui.html
username: demo
password: demo
2.  http://<host-name>:8081/demo-user/swagger-ui.html
3.  http://<host-name>:demo-details/swagger-ui.html
  
## Unit test cases
Run the below command from the root directory
1. `mvn clean test`

## Build docker file
Run the below command from the root directory
1. `docker build -f Dockerfile -t <jar-name> .`
**View all images**
docker images
**Push image to container and run**
docker run -p <container-port>:<host-port> <jar-name>
  
## Run Zipkin server
1. downloaded the open zipkin from https://zipkin.io/pages/quickstart
2. java -jar <zipkin-jar>
  
## Time Spent
6 hr

## ENHANCEMENT SCOPE
1. Performance Testing
2. CI/CD 

