import React from 'react'
import ReactDOM from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import StartPage from './pages/Start/StartPage.tsx'
import ErrorPage from "./pages/Error/ErrorPage.tsx";
import CustomerHomePage from "./pages/CustomerHome/CustomerHomePage.tsx";
import AgentHomePage from "./pages/AgentHome/AgentHomePage.tsx";
import { CssBaseline, CssVarsProvider } from "@mui/joy";
import './index.css'
import '@fontsource/inter';

const router = createBrowserRouter([
	{
		path: "/",
		element: <StartPage/>,
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
			<CssBaseline/>
			<RouterProvider router={router}/>
		</CssVarsProvider>
	</React.StrictMode>,
)
