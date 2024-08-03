package simonerhardt.simplechat.api.chat;

public record ChatMessageDto(String id, String chatSessionId, long sentAt, String user, String message) {
}
