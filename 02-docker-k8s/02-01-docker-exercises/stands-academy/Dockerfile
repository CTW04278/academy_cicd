FROM maven:3.8.6-amazoncorretto-17 as build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY . .

RUN mvn -DskipTests clean package

FROM amazoncorretto:17
WORKDIR deployments
COPY --from=build app/target/quarkus-app/lib lib/
COPY --from=build app/target/quarkus-app/app app/
COPY --from=build app/target/quarkus-app/quarkus quarkus/
COPY --from=build app/target/quarkus-app/quarkus-run.jar quarkus-run.jar

ENTRYPOINT ["java", "-jar", "quarkus-run.jar"]