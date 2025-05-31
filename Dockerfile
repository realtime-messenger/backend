FROM openjdk:24-jdk
WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY .env .
COPY src src

RUN microdnf install -y findutils
RUN chmod +x gradlew
RUN ./gradlew build -x test
CMD ["java", "-jar", "./build/libs/backend-RELEASE.jar"]