FROM node:20-alpine3.20 as build
RUN apk add openjdk21 maven

WORKDIR /build
COPY . .

# Frontend build
WORKDIR /build/frontend
RUN npm install
RUN npm run build

# Backend build
WORKDIR /build/backend
RUN mvn package -DskipTests

# Run in fresh JRE image
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /build/backend/target/*.jar simple-chat.jar
CMD [ "java", "-jar", "simple-chat.jar"]

EXPOSE 8080
