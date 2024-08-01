import ChatSession from "../../model/ChatSession.ts";

interface Props {
	session: ChatSession
}

export default function Chat({session}: Readonly<Props>) {
	return (
		<>
			<p>Chat started at: {session.startedAt.toLocaleString()}</p>
		</>
	);
}
