import { useState } from "react";
import { Link } from "react-router-dom";
import StatCard from "../components/StatCard";
import ApplicationCard from "../components/ApplicationCard";

const mockInitial = [
  { id: 1, title: "Développeur Full Stack", company: "Tech Innovate", date: "15/03/2024", status: "PENDING", cvName: "CV_Developpeur_FullStack.pdf", cvUrl: "#", },
  { id: 2, title: "Product Manager", company: "Digital Solutions", date: "10/03/2024", status: "ACCEPTED", cvName: "CV_Product_Manager.pdf", cvUrl: "#", },
  { id: 3, title: "UX Designer", company: "Creative Studio", date: "05/03/2024", status: "REJECTED", cvName: "CV_UX_Designer.pdf", cvUrl: "#", },
];

export default function Dashboard() {
  // Utiliser useState pour gérer les candidatures
  const [applications, setApplications] = useState(mockInitial);

  // Fonction pour mettre à jour une candidature
  const handleUpdateApplication = (updatedApp) => {
    setApplications(prevApps =>
      prevApps.map(app => 
        app.id === updatedApp.id ? updatedApp : app
      )
    );
    console.log('Application mise à jour:', updatedApp);
  };

  // Calculer les statistiques dynamiquement
  const total = applications.length;
  const pending = applications.filter(x => x.status === "PENDING").length;
  const inProcess = applications.filter(x => x.status === "IN_PROCESS").length;
  const accepted = applications.filter(x => x.status === "ACCEPTED").length;
  const rejected = applications.filter(x => x.status === "REJECTED").length;

  return (
    <div className="min-h-screen bg-gray-50 flex flex-col">
      {/* Header */}
      <div className="bg-white border-b">
        <div className="mx-auto max-w-6xl px-4 py-5 flex items-center justify-between">
          <div className="flex items-center gap-3">
            <div className="h-9 w-9 rounded-lg bg-rose-100 flex items-center justify-center">📂</div>
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
        {/* Stats */}
        <div className="mx-auto max-w-6xl px-4 py-6 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-5 gap-4">
          <StatCard title="Total" value={total} />
          <StatCard title="En attente" value={pending} dotColor="bg-blue-500" />
          <StatCard title="En cours" value={inProcess} dotColor="bg-yellow-500" />
          <StatCard title="Accepté" value={accepted} dotColor="bg-green-500" />
          <StatCard title="Refusé" value={rejected} dotColor="bg-red-500" />
        </div>

        {/* Grid candidatures */}
        <div className="mx-auto max-w-6xl px-4 pb-10 grid grid-cols-1 md:grid-cols-2 gap-5">
          {applications.map(item => 
            <ApplicationCard 
              key={item.id} 
              item={item}
              onUpdate={handleUpdateApplication}
            />
          )}
        </div>
      </div>

      {/* Footer */}
      <footer className="bg-white border-t py-6 mt-auto">
        <div className="mx-auto max-w-6xl px-4">
          <div className="flex flex-col md:flex-row justify-between items-center">
            <div className="flex items-center gap-3 mb-4 md:mb-0">
              <div className="h-6 w-6 rounded-lg bg-rose-100 flex items-center justify-center text-sm">📂</div>
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