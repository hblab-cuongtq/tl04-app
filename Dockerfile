FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY certs /certs

COPY build/libs/*.jar tl04.jar

EXPOSE 3000

ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java","-jar","/app/tl04.jar"]
