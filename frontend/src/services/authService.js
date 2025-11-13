// src/services/authService.js
const API_URL = "http://localhost:8080/api/auth";

export const login = async (pseudo, password) => {
  const response = await fetch(`${API_URL}/authenticate`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ pseudo, password }),
  });

  if (!response.ok) {
    // Backend may return plain text (e.g. "Mot de passe incorrect") or JSON.
    // Try JSON first, otherwise fall back to text to avoid JSON parse errors.
    const contentType = response.headers.get('content-type') || '';
    let errMsg = 'Erreur lors de la connexion';
    try {
      if (contentType.includes('application/json')) {
        const err = await response.json();
        errMsg = err?.message || JSON.stringify(err) || errMsg;
      } else {
        const text = await response.text();
        errMsg = text || errMsg;
      }
    } catch (e) {
      
    }
    throw new Error(errMsg);
  }

  return response.json(); // { token: "..." }
};
