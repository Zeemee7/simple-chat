import ChatSession from "../../model/ChatSession.ts";
import { Box } from "@mui/joy";

interface Props {
	session: ChatSession;
}

export default function ChatSessionBox({session}: Readonly<Props>) {
	return (
		<Box>
			<p>Chat started at: {new Date(session.startedAt).toLocaleString()}</p>
		</Box>
	);
}
