# Simple Chat Application
## Execution
Start the Simple Chat Application with one of the following:
1. Docker:
   ```
   docker build -t simple-chat .
   docker run -d -p 8080:8080 --name simple-chat simple-chat
   ```
2. Using npm and Maven, see [Development](#development) section.

Then, open [http://localhost:8080]() in a browser or use two browsers/clients to try out actual chatting.

> [!NOTE]
> Since an in-memory database is used for the sake of this demonstration, no data is preserved between restarts of the application.

## Development
### Prerequisites
- Maven > 3.9
- JDK > 21
- node.js > 20
- npm > 10.8
### Setup
Install frontend dependencies:
```
cd frontend
npm install
```
### Local server during development
Start backend using IDE or the Spring Boot plugin:
```
cd backend
mvn spring-boot:run
```
Start frontend's hot reload:
```
cd frontend
npm run watch
```
### Unit Tests
Unit tests are written using [Vitest](https://vitest.dev) and [React Testing Library](https://testing-library.com/docs/react-testing-library/intro).  
To run the tests:
```
npm run test
```
Run locally, this will run the tests and watch for changes.  
In CI environments (`CI` env variable being truthy) it will only run the tests once.
