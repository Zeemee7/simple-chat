import { Button } from "@mui/joy";
import { useState } from "react";
import ChatSession from "../../model/ChatSession.ts";
import ChatSessionBox from "../../components/ChatSessionBox/ChatSessionBox.tsx";
import { chatSessionClient } from "../../api/ChatSessionClient.ts";


export default function CustomerHomePage() {
	const [activeSession, setActiveSession] = useState(undefined as ChatSession | undefined);

	function handleStartSessionButtonClick() {
		chatSessionClient.createSession()
			.then(setActiveSession)
			.catch(console.error);
	}

	function isChatSessionActive() {
		return activeSession !== undefined;
	}

	return (
		<>
			<h1>How can we help you?</h1>
			{isChatSessionActive() ? (
				<ChatSessionBox session={activeSession!}/>
			) : (
				<Button size="lg"
						onClick={handleStartSessionButtonClick}>
					Start chat with customer service
				</Button>
			)}
		</>
	);
}
