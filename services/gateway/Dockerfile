FROM openjdk
ARG JAR_FILE=target/*.jar
COPY ./target/gateway-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]