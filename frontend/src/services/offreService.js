const API_URL = "http://localhost:8080/api/offres";

export async function getAllOffres() {
  const res = await fetch(API_URL);
  return await res.json();
}

export async function getOffreById(id) {
  const res = await fetch(`${API_URL}/${id}`);
  return await res.json();
}

export async function createOffre(offre) {
  const res = await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(offre),
  });
  return await res.json();
}

export async function updateOffre(id, offre) {
  const res = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(offre),
  });
  return await res.json();
}

export async function deleteOffre(id) {
  await fetch(`${API_URL}/${id}`, { method: "DELETE" });
}
