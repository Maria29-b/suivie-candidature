import { useState, useEffect } from "react";
import { Briefcase } from 'lucide-react';
import { Link } from "react-router-dom";
import StatCard from "../components/StatCard";
import ApplicationCard from "../components/ApplicationCard";

export default function Dashboard() {
  const [applications, setApplications] = useState([]);
  const [loadError, setLoadError] = useState(null);

  // 🔹 Charger les candidatures depuis le backend
  useEffect(() => {
    fetch("http://localhost:8080/api/applications")
      .then(res => res.json())
      .then(data => {
        // Défensive : le backend peut retourner :
        // - un tableau direct de candidatures -> data = [ ... ]
        // - un objet paginé -> data = { content: [...], totalElements: 10 }
        // - une erreur ou du HTML (si endpoint manquant) -> data = {...} ou string
        console.log('applications payload:', data);
        let apps = [];
        if (Array.isArray(data)) {
          apps = data;
        } else if (data && Array.isArray(data.content)) {
          apps = data.content;
        } else if (data && Array.isArray(data.items)) {
          // fallback for some APIs
          apps = data.items;
        } else {
          // Received something unexpected
          setLoadError('Réponse inattendue du serveur pour les candidatures');
          console.warn('Unexpected applications payload:', data);
        }
        setApplications(apps);
      })
      .catch(err => {
        console.error(err);
        setLoadError('Erreur lors du chargement des candidatures');
      });
  }, []);

  // 🔹 Calculer les statistiques dynamiquement
  const total = applications.length;
  const pending = applications.filter(x => x.status === "PENDING").length;
  const inProcess = applications.filter(x => x.status === "IN_PROCESS").length;
  const accepted = applications.filter(x => x.status === "ACCEPTED").length;
  const rejected = applications.filter(x => x.status === "REJECTED").length;

  // 🔹 Mettre à jour une candidature
  const handleUpdateApplication = (updatedApp) => {
    setApplications(prevApps =>
      prevApps.map(app => 
        app.id === updatedApp.id ? updatedApp : app
      )
    );
    console.log('Application mise à jour:', updatedApp);
  };

  // Fonction pour supprimer une candidature
  const handleDeleteApplication = (appId) => {
    setApplications(prevApps => prevApps.filter(app => app.id !== appId));
    console.log('Application supprimée:', appId);
  };


  return (
    <div className="min-h-screen bg-gray-50 flex flex-col">
      {/* Header */}
      <div className="bg-white border-b">
        <div className="mx-auto max-w-6xl px-4 py-5 flex items-center justify-between">
          <div className="flex items-center gap-3">
            <div className="logo-box">
              <Briefcase className="logo-icon" />
            </div>
            <h1 className="text-xl font-semibold">TrackMyJob</h1>
          </div>
          <Link 
            to="/add-application" 
            className="px-4 py-2 rounded-lg bg-rose-600 text-white hover:bg-rose-700"
          >
            + Nouvelle candidature
          </Link>
        </div>
      </div>

      {/* Main Content */}
      <div className="flex-1">
        {/* Stats compactes */}
        <div className="mx-auto max-w-6xl px-4 py-6">
          <div className="grid grid-cols-2 sm:grid-cols-3 lg:grid-cols-5 gap-3">
            <StatCard title="Total" value={total} />
            <StatCard title="En attente" value={pending} dotColor="bg-blue-500" />
            <StatCard title="En cours" value={inProcess} dotColor="bg-yellow-500" />
            <StatCard title="Accepté" value={accepted} dotColor="bg-green-500" />
            <StatCard title="Refusé" value={rejected} dotColor="bg-red-500" />
          </div>
        </div>

        {/* Séparateur comme dans l'image 1 */}
        <div className="mx-auto max-w-6xl px-4">
          <hr className="border-t border-gray-300 my-6" />
        </div>

        {/* Grid candidatures - PARTIE MANQUANTE AJOUTÉE */}
        <div className="mx-auto max-w-6xl px-4 pb-10">
          <div className="grid grid-cols-1 md:grid-cols-2 gap-5">
            {loadError ? (
              <div className="col-span-1 md:col-span-2 text-red-600">{loadError}. Voir console pour plus de détails.</div>
            ) : (
              applications.map(item => (
              <ApplicationCard 
                key={item.id} 
                item={item} 
                onUpdate={handleUpdateApplication}
                onDelete={handleDeleteApplication}
              />
              ))
            )}
          </div>
        </div>
      </div>

      {/* Footer */}
      <footer className="bg-white border-t py-6 mt-auto">
        <div className="mx-auto max-w-6xl px-4">
          <div className="flex flex-col md:flex-row justify-between items-center">
            <div className="flex items-center gap-3 mb-4 md:mb-0">
              <div className="logo-box">
                <Briefcase className="logo-icon" />
              </div>
              <span className="font-semibold text-gray-800">TrackMyJob</span>
            </div>
            
            <div className="text-sm text-gray-600 mb-4 md:mb-0">
              © {new Date().getFullYear()} TrackMyJob. Tous droits réservés.
            </div>
            
            <div className="flex gap-4">
              <a href="#" className="text-sm text-gray-600 hover:text-rose-600 transition-colors">
                Mentions légales
              </a>
              <a href="#" className="text-sm text-gray-600 hover:text-rose-600 transition-colors">
                Politique de confidentialité
              </a>
              <a href="#" className="text-sm text-gray-600 hover:text-rose-600 transition-colors">
                Contact
              </a>
            </div>
          </div>
        </div>
      </footer>
    </div>
  );
}
