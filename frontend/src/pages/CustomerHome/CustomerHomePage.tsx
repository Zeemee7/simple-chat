import { Button } from "@mui/joy";
import { useState } from "react";
import ChatSession from "../../model/ChatSession.ts";
import ChatSessionBox from "../../components/ChatSessionBox/ChatSessionBox.tsx";
import config from "../../config.ts";
import axios from "axios";

export default function CustomerHomePage() {
	const [activeSession, setActiveSession] = useState(undefined as ChatSession | undefined);

	function startSession() {
		axios.post(config.apiPrefix + '/chat-sessions')
			.then(response => {
				setActiveSession(response.data);
			})
			.catch(error => {
				console.error(error);
			});
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
						onClick={startSession}>
					Start chat with customer service
				</Button>
			)}
		</>
	);
}
