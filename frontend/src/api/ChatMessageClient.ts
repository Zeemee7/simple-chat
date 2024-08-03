import ChatMessage from "../model/ChatMessage.ts";
import { client } from "./ApiClient.ts";

class ChatMessageClient {
	createMessage(chatSessionId: string, user: string, message: string): Promise<ChatMessage> {
		return new Promise<ChatMessage>((resolve, reject) => {
			client.post(`/chat-sessions/${chatSessionId}/messages`, {
				user: user,
				message: message,
			})
				.then(response => resolve(response.data))
				.catch(reject);
		});
	}

	getMessages(chatSessionId: string): Promise<ChatMessage[]> {
		return new Promise<ChatMessage[]>((resolve, reject) => {
			client.get(`/chat-sessions/${chatSessionId}/messages`)
				.then(response => resolve(response.data))
				.catch(reject);
		});
	}
}

export const chatMessageClient = new ChatMessageClient();
