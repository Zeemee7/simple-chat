import { afterAll, afterEach, beforeAll, describe, expect, it } from "vitest";
import { fireEvent, render, screen } from "@testing-library/react";
import { http, HttpResponse } from "msw"
import { setupServer } from "msw/node";
import CustomerHomePage from "./CustomerHomePage.tsx";
import config from "../../config.ts";

const startedAt = 123456789;
const server = setupServer(
	http.post(config.apiPrefix + '/chat-sessions', () => {
		return HttpResponse.json({id: "new-id", startedAt: startedAt});
	}),
)

beforeAll(() => server.listen());
afterEach(() => server.resetHandlers());
afterAll(() => server.close());

describe("CustomerHomePage component", () => {
	const buttonText = "Start chat with customer service";

	it('should display the button initially', () => {
		render(<CustomerHomePage/>);

		expect(screen.queryByText(buttonText)).toBeInTheDocument();
	});

	it('should create a chat session on button click', async () => {
		render(<CustomerHomePage/>);

		fireEvent.click(screen.getByText(buttonText));

		await screen.findByText(new RegExp("^Chat started at: " + new Date(startedAt).toLocaleString()));
		expect(screen.queryByText(buttonText)).not.toBeInTheDocument();
	});
});
