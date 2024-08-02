import { Button } from "@mui/joy";
import { useState } from "react";
import ChatSession from "../../model/ChatSession.ts";
import Chat from "../../components/Chat/Chat.tsx";

export default function CustomerHomePage() {
	const [activeSession, setActiveSession] = useState(null as ChatSession | null);

	function startSession() {
		setActiveSession({id: "TBD", startedAt: new Date().getMilliseconds()});
	}

	function isChatSessionActive() {
		return activeSession !== null;
	}

	return (
		<>
			<h1>How can we help you?</h1>
			{isChatSessionActive() ? (
				<Chat session={activeSession!}/>
			) : (
				<Button size="lg"
						onClick={startSession}>
					Start chat with customer service
				</Button>
			)}
		</>
	);
}
