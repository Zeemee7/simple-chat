import { describe, expect, it } from "vitest";
import { fireEvent, render, screen } from "@testing-library/react";
import CustomerHomePage from "./CustomerHomePage.tsx";
import '@testing-library/jest-dom/vitest'

describe("CustomerHomePage component", () => {
	const buttonText = "Start chat with customer service";

	it('should display the button initially', () => {
		render(<CustomerHomePage/>);

		expect(screen.queryByText(buttonText)).toBeInTheDocument();
	});

	it('should create a chat session on button click', async () => {
		render(<CustomerHomePage/>);

		fireEvent.click(screen.getByText(buttonText));

		await screen.findByText(/^Chat started at/);
		expect(screen.queryByText(buttonText)).not.toBeInTheDocument();
	});
});
