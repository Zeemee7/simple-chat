import ChatSession from "../model/ChatSession.ts";
import { client } from "./ApiClient.ts";

class ChatSessionClient {
	createSession(): Promise<ChatSession> {
		return new Promise<ChatSession>((resolve, reject) => {
			client.post("/chat-sessions")
				.then(response => resolve(response.data))
				.catch(reject);
		});
	}

	getSessions(): Promise<ChatSession[]> {
		return new Promise<ChatSession[]>((resolve, reject) => {
			client.get("/chat-sessions")
				.then(response => resolve(response.data))
				.catch(reject);
		});
	}
}

export const chatSessionClient = new ChatSessionClient();
