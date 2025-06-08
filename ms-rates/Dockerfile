FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-rates.jar
ENTRYPOINT ["java","-jar","/ms-rates.jar"]