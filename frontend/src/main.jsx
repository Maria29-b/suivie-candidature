
import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import "./index.css";
import LoginPage from './pages/LoginPage';
import Dashboard from "./pages/Dashboard";
import NewApplication from "./pages/NewApplication";
import { AuthProvider } from "./context/AuthContext";

const router = createBrowserRouter([
  { path: "/login", element: <LoginPage />},
  { path: "/dashboard", element: <Dashboard /> },
  { path: "/add-application", element: <NewApplication /> },
]);

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <AuthProvider>
    <RouterProvider router={router} />
    </AuthProvider>
  </StrictMode>
);
