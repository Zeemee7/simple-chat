# Simple Chat Application
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
