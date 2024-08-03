import ChatSession from "../../model/ChatSession.ts";
import { Box, Button, Input, Stack, Typography } from "@mui/joy";
import { useEffect, useState } from "react";
import { chatMessageClient } from "../../api/ChatMessageClient.ts";
import ChatMessage from "../../model/ChatMessage.ts";
import ChatBubble from "../ChatBubble/ChatBubble.tsx";

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

	const messageBubbles = messages.map((message, index) =>
		<ChatBubble key={index} chatMessage={message} own={message.user === user}/>
	);

	return (
		<Box sx={{height: "100dvh", display: "flex", flexDirection: "column"}}>
			<Box sx={{padding: 2}}>
				<Typography level="title-lg">Chat started at {new Date(session.startedAt).toLocaleString()}</Typography>
			</Box>
			<Box sx={{padding: 2, display: "flex", flex: 1, overflowY: "auto", flexDirection: "column-reverse"}}>
				<Stack direction="column" spacing={2} justifyContent="flex-end">
					{messageBubbles}
				</Stack>
			</Box>
			<Box sx={{padding: 2}}>
				<form onSubmit={handleMessageSubmit}>
					<Input size="lg"
						   variant="outlined"
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
		</Box>
	);
}
