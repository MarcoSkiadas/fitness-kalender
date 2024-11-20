FROM  openjdk:22
EXPOSE 8080
COPY backend/target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]