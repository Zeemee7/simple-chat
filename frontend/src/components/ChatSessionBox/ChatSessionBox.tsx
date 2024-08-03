import ChatSession from "../../model/ChatSession.ts";
import { Box, Button, Input, Sheet } from "@mui/joy";
import { useEffect, useState } from "react";
import { chatMessageClient } from "../../api/ChatMessageClient.ts";
import ChatMessage from "../../model/ChatMessage.ts";

interface Props {
	session: ChatSession;
	user: string;
}

export default function ChatSessionBox({session, user}: Readonly<Props>) {
	const [message, setMessage] = useState("");
	const [messages, setMessages] = useState([] as ChatMessage[]);
	const [refreshSignal, setRefreshSignal] = useState(0);

	function handleMessageSubmit(event: React.FormEvent<HTMLFormElement>) {
		event.preventDefault();
		chatMessageClient.createMessage(session.id, user, message).then(() => {
			setMessage("");
			refresh();
		}).catch(console.error);
	}

	function refresh() {
		setRefreshSignal(refreshSignal + 1);
	}

	useEffect(() => {
		chatMessageClient.getMessages(session.id)
			.then((messages) => {
				setMessages(messages);
			})
			.catch(console.error);
		const intervalId = setInterval(() => refresh(), 1000);
		return () => clearInterval(intervalId);
	}, [session, refreshSignal]);

	const messageBubbles = messages.map(message =>
		<Box>
			<p>{message.user} {new Date(message.sentAt).toLocaleString()}</p>
			<Sheet variant="soft">{message.message}</Sheet>
		</Box>
	);

	return (
		<Box>
			<p>Chat started at: {new Date(session.startedAt).toLocaleString()} (session {session.id})</p>
			{messageBubbles}
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
