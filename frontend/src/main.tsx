import React from 'react'
import ReactDOM from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import LandingPage from './LandingPage.tsx'
import ErrorPage from "./ErrorPage.tsx";
import CustomerHomePage from "./CustomerHomePage.tsx";
import AgentHomePage from "./AgentHomePage.tsx";
import { CssBaseline, CssVarsProvider } from "@mui/joy";
import './index.css'
import '@fontsource/inter';

const router = createBrowserRouter([
	{
		path: "/",
		element: <LandingPage/>,
		errorElement: <ErrorPage/>,
	},
	{
		path: "/customer",
		element: <CustomerHomePage/>,
	},
	{
		path: "/agent",
		element: <AgentHomePage/>,
	}
]);

ReactDOM.createRoot(document.getElementById('root')!).render(
	<React.StrictMode>
		<CssVarsProvider>
			<CssBaseline />
			<RouterProvider router={router}/>
		</CssVarsProvider>
	</React.StrictMode>,
)
