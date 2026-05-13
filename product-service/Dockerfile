FROM eclipse-temurin:17-jdk

EXPOSE 8085

ADD target/product-service-docker.jar product-service-docker.jar

ENTRYPOINT ["java","-jar","/product-service-docker.jar"]