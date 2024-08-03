import { Box, Sheet, Stack, Typography } from "@mui/joy";
import ChatMessage from "../../model/ChatMessage.ts";

interface Props {
	chatMessage: ChatMessage;
	own: boolean;
}

export default function ChatSessionListItem({chatMessage, own}: Readonly<Props>) {
	return (
		<Box sx={{display: "flex", flexDirection: own ? "row-reverse" : "row"}}>
			<Box sx={{maxWidth: "75%", minWidth: "auto"}}>
				<Stack spacing={2} direction="row" sx={{justifyContent: "space-between"}}>
					<Typography level="body-xs">{own ? "You" : chatMessage.user}</Typography>
					<Typography level="body-xs">{new Date(chatMessage.sentAt).toLocaleString()}</Typography>
				</Stack>
				<Sheet
					color={own ? "primary" : "neutral"}
					variant={own ? "solid" : "soft"}
					sx={{
						padding: 1.25,
						borderRadius: "lg",
						borderBottomRightRadius: own ? 0 : 'lg',
						borderTopLeftRadius: own ? 'lg' : 0,
					}}>{chatMessage.message}</Sheet>
			</Box>
		</Box>
	);
}
