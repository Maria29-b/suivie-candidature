// src/services/authService.js
const API_URL = "http://localhost:8080/api/auth";

export const login = async (pseudo, password) => {
  const response = await fetch(`${API_URL}/authenticate`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ pseudo, password }),
  });

  if (!response.ok) {
    const err = await response.json();
    throw new Error(err.message || "Erreur lors de la connexion");
  }

  return response.json(); // { token: "..." }
};
