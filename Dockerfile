FROM node:20-alpine3.20 AS build
RUN apk add openjdk21 maven && apk cache clean

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
FROM eclipse-temurin:21-jre-alpine

# Heroku does not allow to run with root (it's better anyway)
RUN adduser -D chat
USER chat

WORKDIR /app
COPY --from=build --chown=chat:chat /build/backend/target/*.jar simple-chat.jar

# PORT environment variable is required by Heroku.
CMD [ "java", "-Dserver.port=${PORT}", "-jar", "simple-chat.jar"]
