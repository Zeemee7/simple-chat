import { useEffect, useState } from "react";
import ChatSession from "../../model/ChatSession.ts";
import { Box, Typography } from "@mui/joy";
import ChatSessionBox from "../../components/ChatSessionBox/ChatSessionBox.tsx";
import ChatSessionList from "../../components/ChatSessionList/ChatSessionList.tsx";
import { chatSessionClient } from "../../api/ChatSessionClient.ts";

function AgentHomePage() {
	const [activeSession, setActiveSession] = useState(undefined as ChatSession | undefined);
	const [sessions, setSessions] = useState([] as ChatSession[]);
	const [refreshSignal, setRefreshSignal] = useState(0);

	function isChatSessionActive() {
		return activeSession !== undefined;
	}

	function handleSessionSelected(chatSession: ChatSession) {
		setActiveSession(chatSession);
	}

	function refresh() {
		setRefreshSignal(refreshSignal + 1);
	}

	useEffect(() => {
		chatSessionClient.getSessions().then(chatSessions => {
			setSessions(chatSessions);
		}).catch(console.error);
		const intervalId = setInterval(() => refresh(), 1000);
		return () => clearInterval(intervalId);
	}, [refreshSignal]);

	return (
		<Box sx={{display: 'flex', minHeight: '100dvh'}}>
			<ChatSessionList sessions={sessions} onSessionSelected={handleSessionSelected} activeSession={activeSession}/>
			<Box>
				{isChatSessionActive() ? (
					// For now, user is hardcoded :-)
					<ChatSessionBox session={activeSession!} user="Service Agent"/>
				) : (
					<Typography>Please select a chat session</Typography>
				)}
			</Box>
		</Box>
	)
}

export default AgentHomePage
