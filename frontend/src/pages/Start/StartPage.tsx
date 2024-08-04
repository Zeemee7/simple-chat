import { Box, Button, Stack, Tooltip, Typography } from "@mui/joy";
import { ExternalLink } from "lucide-react";

export default function StartPage() {
	return (
		<Box sx={{display: "flex", flexDirection: "column", minHeight: "100dvh", justifyContent: "center"}}>
			<Stack spacing={4} sx={{padding: 2, alignItems: "center"}}>
				<Typography level="h1">Simon Erhardt's Simple Chat Application</Typography>
				<Typography level="body-sm">(Hover buttons to see description.)</Typography>
				<Stack direction="row" spacing={4}>
					<Tooltip title="This is used by the customer service to retrieve and answer all incoming customer requests." variant="soft">
						<Button component="a" href="/agent" target="_blank" startDecorator={<ExternalLink/>}>
							Agent's dashboard
						</Button>
					</Tooltip>
					<Tooltip title="This is used by the customer to start a chat with a customer service agent." variant="soft">
						<Button component="a" href="/customer" target="_blank" startDecorator={<ExternalLink/>}>
							Customer's entrypoint
						</Button>
					</Tooltip>
				</Stack>
			</Stack>
		</Box>
	)
}
