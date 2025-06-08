FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-discounts2.jar
ENTRYPOINT ["java","-jar","/ms-discounts2.jar"]