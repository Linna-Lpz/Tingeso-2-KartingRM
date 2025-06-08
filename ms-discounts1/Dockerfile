FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-discounts1.jar
ENTRYPOINT ["java","-jar","/ms-discounts1.jar"]