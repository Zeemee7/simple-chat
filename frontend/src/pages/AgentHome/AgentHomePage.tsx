import { useEffect, useState } from "react";
import ChatSession from "../../model/ChatSession.ts";
import { Box, Typography } from "@mui/joy";
import ChatSessionBox from "../../components/ChatSessionBox/ChatSessionBox.tsx";
import ChatSessionList from "../../components/ChatSessionList/ChatSessionList.tsx";
import axios from "axios";
import config from "../../config.ts";

function AgentHomePage() {
	const [activeSession, setActiveSession] = useState(undefined as ChatSession | undefined);
	const [sessions, setSessions] = useState([] as ChatSession[]);
	const [sessionsUpdated, setSessionsUpdated] = useState(false);

	function isChatSessionActive() {
		return activeSession !== undefined;
	}

	useEffect(() => {
		if (!sessionsUpdated) {
			axios.get(config.apiPrefix + '/chat-sessions')
				.then(response => {
					setSessions(response.data);
					setActiveSession(undefined);
					setSessionsUpdated(true);
				})
				.catch(error => {
					console.error(error);
				});
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
