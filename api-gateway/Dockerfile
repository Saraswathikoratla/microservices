FROM eclipse-temurin:17-jdk

EXPOSE 8080

ADD target/api-gateway-docker.jar api-gateway-docker.jar

ENTRYPOINT ["java","-jar","/api-gateway-docker.jar"]