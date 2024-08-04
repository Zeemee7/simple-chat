import ChatSession from "../../model/ChatSession.ts";
import { Box, List, Typography } from "@mui/joy";
import ChatSessionListItem from "../ChatSessionListItem/ChatSessionListItem.tsx";

interface Props {
	onSessionSelected?: (chatSession: ChatSession) => void;
	sessions: ChatSession[];
	activeSession?: ChatSession;
}

export default function ChatSessionList({onSessionSelected, sessions, activeSession}: Readonly<Props>) {
	const sessionItems = sessions.map(session =>
		<ChatSessionListItem key={session.id} session={session} selected={session.id === activeSession?.id}
							 onClick={onSessionSelected ? () => onSessionSelected(session) : undefined}/>
	);

	return (
		<>
			<Box sx={{padding: 2}}>
				<Typography level="title-lg">Chat Sessions</Typography>
			</Box>
			{sessionItems.length ? (
				<List size="md">
					{sessionItems}
				</List>
			) : (
				<Box sx={{padding: 2}}>
					<Typography color="neutral" level="body-lg">No chat sessions yet.</Typography>
				</Box>
			)}
		</>
	);
}
