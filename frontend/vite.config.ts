import { defineConfig } from 'vite'
import "vitest/config";
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
	plugins: [react()],
	build: {
		outDir: "../backend/src/main/resources/static",
	},
	test: {
		environment: "jsdom",
		setupFiles: "./src/test-setup.ts",
	}
})
