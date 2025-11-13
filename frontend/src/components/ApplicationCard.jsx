import { useState } from 'react';
import { Edit } from 'lucide-react';
import EditCandidatureModal from './EditCandidatureModal';

export default function ApplicationCard({ item, onUpdate }) {
  const [isEditModalOpen, setIsEditModalOpen] = useState(false);

  const getStatusClass = (status) => {
    switch (status) {
      case 'PENDING': return 'status-pending';
      case 'ACCEPTED': return 'status-accepted';
      case 'REJECTED': return 'status-rejected';
      case 'IN_PROCESS': return 'status-in_process';
      default: return 'status-pending';
    }
  };

  const getStatusText = (status) => {
    switch (status) {
      case 'PENDING': return 'En attente';
      case 'ACCEPTED': return 'Accepté';
      case 'REJECTED': return 'Refusé';
      case 'IN_PROCESS': return 'En cours';
      default: return 'En attente';
    }
  };

  // Convertir les données pour le modal
  const getCandidatureData = () => {
    return {
      id: item.id,
      poste: item.title,
      entreprise: item.company,
      datePostulation: item.date,
      statut: getStatusText(item.status),
      cvUrl: item.cvUrl
    };
  };

  // Gérer la sauvegarde depuis le modal
  const handleSave = (updatedData) => {
    // Convertir les données du modal vers le format de l'application
    const updatedItem = {
      ...item,
      title: updatedData.poste,
      company: updatedData.entreprise,
      date: updatedData.datePostulation,
      status: convertStatusToKey(updatedData.statut)
    };

    // Appeler la fonction de mise à jour du parent si elle existe
    if (onUpdate) {
      onUpdate(updatedItem);
    }
    
    console.log('Candidature mise à jour:', updatedItem);
  };

  // Convertir le texte du statut vers la clé
  const convertStatusToKey = (statusText) => {
    switch (statusText) {
      case 'En attente': return 'PENDING';
      case 'Accepté': return 'ACCEPTED';
      case 'Refusé': return 'REJECTED';
      case 'En cours': return 'IN_PROCESS';
      default: return 'PENDING';
    }
  };

  return (
    <>
      <div className="application-card">
        <h3>{item.title}</h3>
        <p className="company">{item.company}</p>
        <p className="date">{item.date}</p>
        <div className="flex items-center justify-between">
          <span className={`status ${getStatusClass(item.status)}`}>
            {getStatusText(item.status)}
          </span>
          <div className="card-actions">
            {item.cvUrl && item.cvUrl !== "#" && (
              <a href={item.cvUrl} className="text-sm text-rose-600 hover:underline">
                Voir CV
              </a>
            )}
            <button 
              onClick={() => setIsEditModalOpen(true)}
              className="edit-btn"
              title="Modifier la candidature"
            >
              <Edit size={16} />
              Modifier
            </button>
          </div>
        </div>
      </div>

      {/* Modal d'édition */}
      <EditCandidatureModal
        isOpen={isEditModalOpen}
        onClose={() => setIsEditModalOpen(false)}
        candidature={getCandidatureData()}
        onSave={handleSave}
      />
    </>
  );
}