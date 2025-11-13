import { useState, useEffect } from 'react';
import { X, Save, Briefcase, Building2, Calendar, Tag } from 'lucide-react';
import './EditCandidatureModal.css';

export default function EditCandidatureModal({ isOpen, onClose, candidature, onSave }) {
  const [formData, setFormData] = useState({
    poste: '',
    entreprise: '',
    datePostulation: '',
    statut: 'En attente'
  });

  // Pré-remplir le formulaire avec les données de la candidature
  useEffect(() => {
    if (candidature) {
      setFormData({
        poste: candidature.poste || '',
        entreprise: candidature.entreprise || '',
        datePostulation: candidature.datePostulation || '',
        statut: candidature.statut || 'En attente'
      });
    }
  }, [candidature]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSave({ ...candidature, ...formData });
    onClose();
  };

  if (!isOpen) return null;

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal-content" onClick={(e) => e.stopPropagation()}>
        {/* Header */}
        <div className="modal-header">
          <div className="modal-title-container">
            <Briefcase className="modal-icon" />
            <h2 className="modal-title">Modifier la candidature</h2>
          </div>
          <button className="close-button" onClick={onClose}>
            <X size={24} />
          </button>
        </div>

        {/* Body */}
        <div className="modal-body">
          <div className="form-group">
            <label htmlFor="poste" className="form-label">
              <Briefcase size={18} />
              Poste
            </label>
            <input
              type="text"
              id="poste"
              name="poste"
              value={formData.poste}
              onChange={handleChange}
              className="form-input"
              placeholder="Ex: Développeur Full Stack"
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="entreprise" className="form-label">
              <Building2 size={18} />
              Entreprise
            </label>
            <input
              type="text"
              id="entreprise"
              name="entreprise"
              value={formData.entreprise}
              onChange={handleChange}
              className="form-input"
              placeholder="Ex: Tech Innovate"
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="datePostulation" className="form-label">
              <Calendar size={18} />
              Date de postulation
            </label>
            <input
              type="date"
              id="datePostulation"
              name="datePostulation"
              value={formData.datePostulation}
              onChange={handleChange}
              className="form-input"
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="statut" className="form-label">
              <Tag size={18} />
              Statut
            </label>
            <select
              id="statut"
              name="statut"
              value={formData.statut}
              onChange={handleChange}
              className="form-input form-select"
              required
            >
              <option value="En attente">En attente</option>
              <option value="En cours">En cours</option>
              <option value="Accepté">Accepté</option>
              <option value="Refusé">Refusé</option>
            </select>
          </div>
        </div>

        {/* Footer */}
        <div className="modal-footer">
          <button className="cancel-button" onClick={onClose}>
            Annuler
          </button>
          <button className="save-button" onClick={handleSubmit}>
            <Save size={18} />
            Enregistrer
          </button>
        </div>
      </div>
    </div>
  );
}