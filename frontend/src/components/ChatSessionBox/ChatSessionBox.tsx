import ChatSession from "../../model/ChatSession.ts";
import { Box, Button, Input } from "@mui/joy";
import { useState } from "react";
import { chatMessageClient } from "../../api/ChatMessageClient.ts";

interface Props {
	session: ChatSession;
	user: string;
}

export default function ChatSessionBox({session, user}: Readonly<Props>) {
	const [message, setMessage] = useState("");

	function handleMessageSubmit(event: React.FormEvent<HTMLFormElement>) {
		event.preventDefault();
		chatMessageClient.createMessage(session.id, user, message).then(() => {
			setMessage("");
		}).catch(console.error);
	}

	return (
		<Box>
			<p>Chat started at: {new Date(session.startedAt).toLocaleString()}</p>
			<form onSubmit={handleMessageSubmit}>
				<Input size="lg"
					   placeholder="Neue Nachricht..."
					   value={message}
					   onChange={(event) => setMessage(event.target.value)}
					   endDecorator={
						   <Button
							   variant="solid"
							   color="primary"
							   type="submit"
							   sx={{borderTopLeftRadius: 0, borderBottomLeftRadius: 0}}>
							   Send
						   </Button>
					   }/>
			</form>
		</Box>
	);
}
