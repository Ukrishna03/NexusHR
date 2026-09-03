FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

COPY . .

RUN chmod +x mvnw && ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=build /app/target/nexushr-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 6161

ENTRYPOINT ["java", "-jar", "app.jar"]