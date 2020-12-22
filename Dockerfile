FROM openjdk:8-jdk-alpine
COPY target/demo-0.0.1.jar moneyExchange.jar
ENTRYPOINT [ "java","-jar","moneyExchange.jar" ]
