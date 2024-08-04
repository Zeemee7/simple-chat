# Simple Chat Application
## Execution
Start the Simple Chat Application with one of the following:
1. Docker:
   ```
   docker build -t simple-chat .
   docker run -d -p 8080:8080 -e PORT=8080 --name simple-chat simple-chat
   ```
   (The `PORT` variable has to be set for the image to be runnable in Heroku)  

2. Using npm and Maven, see [Development](#development) section.

Then, open http://localhost:8080 in a browser or use two browsers/clients to try out actual chatting.

> [!NOTE]
> Since an in-memory database is used for the sake of this demonstration, no data is preserved between restarts of the application.

## Development
### Prerequisites
- Maven > 3.9
- JDK > 21
- node.js > 20
- npm > 10.8
### Setup
1. Install frontend dependencies:
   ```
   cd frontend
   npm install
   ```
2. For me, it worked best to open `frontend` and `backend` as separate projects in the IDE (IntelliJ).

### Local server during development
To have a full stack dev environment without the need to configure a proxy or CORS, Vite is configured copy the frontend output directly to where Spring Boot expects it.
> [!IMPORTANT]
> Order is important, otherwise Spring Boot might not recognize index.html as welcome page.

Start frontend compiler with hot reload:
```
cd frontend
npm run watch
```
Start backend using IDE or the Spring Boot plugin:
```
cd backend
mvn spring-boot:run
```
> [!IMPORTANT]
> Caveat: In contrast to Vite's dev server (which you also can run with `npm run dev`), you have to refresh the page manually to apply front end changes.

### Frontend Unit Tests
Frontend unit tests are written using [Vitest](https://vitest.dev) and [React Testing Library](https://testing-library.com/docs/react-testing-library/intro).  
To run the tests:
```
npm run test
```
Run locally, this will run the tests, watch for changes, and re-run them if necessary.

> [!NOTE]
> In CI environments (`CI` env variable being truthy) it will only run the tests once.

### Backend Unit Tests
Backend unit tests split into
- Unit tests (`*Test.java`)
- Integration tests (`*IT.java`)

Both can be run at the same time with:
```
mvn clean verify
```

### Swagger UI
Spring REST controllers are exposed as OpenAPI doc with Swagger UI, at the `/api/v1/swagger-ui.html` endpoint, e.g. http://localhost:8080/api/v1/swagger-ui.html.

### Heroku Publishing
Prerequisites:
- Docker daemon is running
- `heroku login` happened

To create an application:
```
heroku container:login
heroku create --stack container
```

To publish and check:
```
heroku container:push web
heroku container:release web
heroku open
heroku logs --tail
```

### Things to do
See [TODO.md](TODO.md).
