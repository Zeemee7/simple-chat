import { Box, Button, Stack, Typography } from "@mui/joy";
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
		<Box sx={{display: "flex", flexDirection: "column", minHeight: "100dvh", justifyContent: "center"}}>
			{isChatSessionActive() ? (
				// For now, user is hardcoded :-)
				<ChatSessionBox session={activeSession!} user="Customer"/>
			) : (
				<Stack spacing={4} sx={{padding: 2, alignItems: "center"}}>
					<Typography level="h1">How can we help you?</Typography>
					<Button size="lg"
							onClick={handleStartSessionButtonClick}>
						Start chat with customer service
					</Button>
				</Stack>
			)}
		</Box>
	);
}
