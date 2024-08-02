import { describe, expect, it, vi } from "vitest";
import { fireEvent, render, screen } from "@testing-library/react";
import ChatSessionListItem from "./ChatSessionListItem.tsx";

describe("ChatSessionListItem component", () => {
	it('should show the chat session ', async () => {
		const startedAt = 123456789;
		render(<ChatSessionListItem session={{id: "A", startedAt: startedAt}}/>);

		expect(screen.queryByText(new RegExp("^Chat started at " + new Date(startedAt).toLocaleString()))).toBeInTheDocument();
	});

	it('should fire onClick event on button click ', async () => {
		const onClick = vi.fn();

		render(<ChatSessionListItem session={{id: "A", startedAt: 123456789}} onClick={onClick}/>);
		fireEvent.click(screen.getByRole("button"));

		expect(onClick).toHaveBeenCalledOnce();
	});
});
