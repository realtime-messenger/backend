FROM amazoncorretto:24
RUN dnf install -y findutils

WORKDIR /app

COPY . .

RUN ls -laht

RUN chmod +x gradlew
RUN ["./gradlew", "build"]
CMD ["java", "-jar", "./build/libs/backend-RELEASE.jar"]
