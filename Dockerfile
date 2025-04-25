FROM openjdk:24
WORKDIR /app
COPY build/libs/backend-RELEASE.jar .
COPY .env .

CMD ["java", "-jar", "backend-RELEASE.jar"]
