import { afterAll, afterEach, beforeAll, describe, expect, it } from "vitest";
import { render, screen } from "@testing-library/react";
import { http, HttpResponse } from "msw"
import { setupServer } from "msw/node";
import config from "../../config.ts";
import AgentHomePage from "./AgentHomePage.tsx";

const server = setupServer(
	http.get(config.apiPrefix + '/chat-sessions', () => {
		return HttpResponse.json([
			{id: "a", startedAt: 10000000},
		]);
	}),
	http.get(config.apiPrefix + '/chat-sessions/a/messages', () => {
		return HttpResponse.json([]);
	}),
)

beforeAll(() => server.listen());
afterEach(() => server.resetHandlers());
afterAll(() => server.close());

describe("AgentHomePage component", () => {
	it('displays the session list on render', async () => {
		render(<AgentHomePage/>);
		await screen.findAllByText(/^Chat started at/);

		expect(screen.queryByText("Please select a chat session")).toBeInTheDocument();
	});
});
