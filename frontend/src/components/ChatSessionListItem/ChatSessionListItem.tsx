import ChatSession from "../../model/ChatSession.ts";
import { ListItem, ListItemButton, ListItemContent } from "@mui/joy";
import { MouseEventHandler } from "react";

interface Props {
	onClick?: MouseEventHandler
	session: ChatSession
	selected?: boolean
}

export default function ChatSessionListItem({onClick, session, selected = false}: Readonly<Props>) {
	return (
		<ListItem>
			<ListItemButton selected={selected} onClick={onClick}>
				<ListItemContent>Chat started at {new Date(session.startedAt).toLocaleString()}</ListItemContent>
			</ListItemButton>
		</ListItem>
	);
}
