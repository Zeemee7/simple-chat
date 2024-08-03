CREATE TABLE chat_session
(
	id         UUID NOT NULL,
	started_at TIMESTAMP(9),
	PRIMARY KEY (id)
);
CREATE TABLE chat_message
(
	id              UUID         NOT NULL,
	sent_at         TIMESTAMP(9) NOT NULL,
	chat_session_id UUID         NOT NULL,
	message         TEXT         NOT NULL,
	user            VARCHAR(255) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (chat_session_id)
		REFERENCES chat_session (id)
		ON DELETE CASCADE
);
