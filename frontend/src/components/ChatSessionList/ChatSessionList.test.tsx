import { describe, expect, it } from "vitest";
import { render, screen } from "@testing-library/react";
import ChatSessionList from "./ChatSessionList.tsx";
import ChatSession from "../../model/ChatSession.ts";

describe("ChatSessionList component", () => {
	it('should show the chat sessions as list items ', async () => {
		const sessions: ChatSession[] = [
			{id: "A", startedAt: 10000000},
			{id: "B", startedAt: 20000000},
			{id: "C", startedAt: 30000000},
		]
		render(<ChatSessionList sessions={sessions}/>);

		const listItems = screen.queryAllByRole("button");
		expect(listItems).toHaveLength(3);
	});
});
