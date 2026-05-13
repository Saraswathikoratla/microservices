FROM eclipse-temurin:17-jdk

EXPOSE 8081

ADD target/user-service-docker.jar user-service-docker.jar

ENTRYPOINT ["java","-jar","/user-service-docker.jar"]