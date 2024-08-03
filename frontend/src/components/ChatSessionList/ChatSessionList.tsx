import ChatSession from "../../model/ChatSession.ts";
import { List } from "@mui/joy";
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
		<List size="md">
			{sessionItems}
		</List>
	);
}
