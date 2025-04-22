FROM openjdk
ARG JAR_FILE=target/*.jar
COPY ./target/config-server-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]