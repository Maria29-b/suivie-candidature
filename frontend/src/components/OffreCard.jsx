import { Link } from "react-router-dom";
import { Trash2, Edit } from "lucide-react";

export default function OffreCard({ offre, onDelete }) {
  return (
    <div className="bg-white shadow-md p-4 rounded-xl border">
      <h3 className="text-lg font-semibold text-gray-800">{offre.titrePoste}</h3>
      <p className="text-sm text-gray-600 mt-1">{offre.description}</p>
      <a
        href={offre.lienSource}
        target="_blank"
        rel="noreferrer"
        className="text-sm text-rose-600 hover:underline mt-2 block"
      >
        Voir l’offre
      </a>

      <div className="flex justify-end gap-3 mt-4">
        <Link
          to={`/edit-offre/${offre.idOffre}`}
          className="flex items-center gap-1 text-blue-600 hover:text-blue-800"
        >
          <Edit size={16} /> Modifier
        </Link>

        <button
          onClick={() => onDelete(offre.idOffre)}
          className="flex items-center gap-1 text-red-600 hover:text-red-800"
        >
          <Trash2 size={16} /> Supprimer
        </button>
      </div>
    </div>
  );
}
