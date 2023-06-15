FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar springkey.jar
ENTRYPOINT ["java","-jar","/springkey.jar"]
