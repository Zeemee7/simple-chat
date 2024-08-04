# TODOs for Simple Chat Application
## Must have
- [x] Basic Maven/Git setup
- [x] Spring Boot backend foundation
- [x] React frontend foundation
- [x] Separate customer/agent frontend parts
- [x] Customer: Start a chat session
- [x] Agent: Show open chat sessions
- [x] Send messages
- [x] Show messages on load
- [x] ~~push~~ poll messages
- [x] Some general documentation (README)
- [x] More code documentation (JavaDoc, OpenAPI, JSDoc?)

## Nice to have
- [x] Make it look ~~nice~~ acceptable
- [x] Docker build
- [x] Online deployment (~~Vercel?,~~ Heroku)

## Future improvements
- [ ] Authentication and authorization for service agents
- [ ] CI Build pipeline
- [ ] Use WebSockets instead of polling.
- [ ] Increase test coverage where reasonable.
- [ ] Make Docker build more efficient (split npm/Maven package retrieval and build).
- [ ] Frontend: Focus on message input field automatically and carefully.
- [ ] Frontend: Output errors to the user, e.g. data fetching errors.
- [ ] Frontend: Desktop notifications.
- [ ] Frontend: Indicate chat partner activity ("writing...");
- [ ] Frontend: DTO <-> model mapping.
- [ ] Backend: Translate exceptions, e.g. 404.
- [ ] Backend: File-based/external database with migration detection.
- [ ] Frontend: Accessibility features.
- [ ] Frontend: Better layout on small screens.
- [ ] Frontend: Multi-language support.
- [ ] Split deployment of frontend and backend, to allow independent scaling (requires centralized data storage).
- [ ] End-to-end encryption.
- [ ] Qualify requests: Customer is being asked initial questions, answers are displayed to the agent.
- [ ] AI chat bot that is trained with the product/service documentation can answer simple questions.
- [ ] CRM integration: Customer identifies itself, agent can easily fetch existing information about the customer.
