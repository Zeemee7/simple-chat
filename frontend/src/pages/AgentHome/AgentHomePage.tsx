import { useEffect, useState } from "react";
import ChatSession from "../../model/ChatSession.ts";
import { Box, Typography } from "@mui/joy";
import ChatSessionBox from "../../components/ChatSessionBox/ChatSessionBox.tsx";
import ChatSessionList from "../../components/ChatSessionList/ChatSessionList.tsx";
import { chatSessionClient } from "../../api/ChatSessionClient.ts";

function AgentHomePage() {
	const [activeSession, setActiveSession] = useState(undefined as ChatSession | undefined);
	const [sessions, setSessions] = useState([] as ChatSession[]);
	const [sessionsUpdated, setSessionsUpdated] = useState(false);

	function isChatSessionActive() {
		return activeSession !== undefined;
	}

	useEffect(() => {
		if (!sessionsUpdated) {
			chatSessionClient.getSessions().then(chatSessions => {
				setSessions(chatSessions);
				setActiveSession(undefined);
				setSessionsUpdated(true);
			}).catch(console.error);
		}
	});

	return (
		<Box sx={{display: 'flex', minHeight: '100dvh'}}>
			<ChatSessionList sessions={sessions} onSessionSelected={setActiveSession} activeSession={activeSession}/>
			<Box>
				{isChatSessionActive() ? (
					<ChatSessionBox session={activeSession!}/>
				) : (
					<Typography>Please select a chat session</Typography>
				)}
			</Box>
		</Box>
	)
}

export default AgentHomePage
