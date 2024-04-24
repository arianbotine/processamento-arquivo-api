FROM amazoncorretto:17-alpine-jdk AS build
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN chmod +x mvnw

RUN ./mvnw install -DskipTests

FROM amazoncorretto:17-alpine-jdk

RUN apk add --no-cache tzdata
ENV TZ America/Sao_Paulo

RUN addgroup -S nonroot \
    && adduser -S nonroot -G nonroot
USER nonroot

COPY --from=build /app/target/conversor-*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]




