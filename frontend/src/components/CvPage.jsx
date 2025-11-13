import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getCvById, updateCv, deleteCv } from "../api";

const CvPage = () => {
  const { idCv } = useParams(); // récupérer l'id du CV depuis l'URL
  const navigate = useNavigate();
  const [cv, setCv] = useState(null);
  const [editMode, setEditMode] = useState(false);
  const [titre, setTitre] = useState("");
  const [description, setDescription] = useState("");

  useEffect(() => {
    fetchCv();
  }, [idCv]);

  const fetchCv = async () => {
    try {
      const response = await getCvById(idCv);
      setCv(response.data);
      setTitre(response.data.titre);
      setDescription(response.data.description);
    } catch (error) {
      console.error("Erreur lors de la récupération du CV", error);
    }
  };

  const handleUpdate = async (e) => {
    e.preventDefault();
    await updateCv(idCv, { ...cv, titre, description });
    setEditMode(false);
    fetchCv();
  };

  const handleDelete = async () => {
    if (window.confirm("Voulez-vous vraiment supprimer ce CV ?")) {
      await deleteCv(idCv);
      navigate("/"); // revenir à la page d'accueil ou autre
    }
  };

  if (!cv) return <p>Chargement du CV...</p>;

  return (
    <div>
      <h2>CV : {cv.titre}</h2>

      {!editMode ? (
        <div>
          <p><strong>Titre :</strong> {cv.titre}</p>
          <p><strong>Description :</strong> {cv.description}</p>
          <p><strong>Candidat :</strong> {cv.candidat?.nom}</p>
          <p><strong>Date de création :</strong> {cv.dateCreation}</p>

          <button onClick={() => setEditMode(true)}>Modifier</button>
          <button onClick={handleDelete}>Supprimer</button>
        </div>
      ) : (
        <form onSubmit={handleUpdate}>
          <div>
            <label>Titre :</label>
            <input value={titre} onChange={(e) => setTitre(e.target.value)} required />
          </div>
          <div>
            <label>Description :</label>
            <textarea value={description} onChange={(e) => setDescription(e.target.value)} required />
          </div>
          <button type="submit">Enregistrer</button>
          <button type="button" onClick={() => setEditMode(false)}>Annuler</button>
        </form>
      )}
    </div>
  );
};

export default CvPage;