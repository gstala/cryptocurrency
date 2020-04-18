FROM openjdk:8
ARG apiKey
ENV API_KEY=$apiKey
COPY . /app
WORKDIR /app
RUN ./mvnw clean package
WORKDIR /app/target
EXPOSE 8080
ENTRYPOINT ["java","-jar","cryptocurrency-0.0.1-SNAPSHOT.jar"]
