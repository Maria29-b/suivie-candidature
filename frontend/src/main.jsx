
import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import "./index.css";
import LoginPage from './pages/LoginPage';
import Dashboard from "./pages/Dashboard";
import NewApplication from "./pages/NewApplication";


const router = createBrowserRouter([
  { path: "/login", element: <LoginPage />},
  { path: "/", element: <Dashboard /> },
  { path: "/add-application", element: <NewApplication /> },
]);

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>
);
