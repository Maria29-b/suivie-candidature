export default function ApplicationCard({ item }) {
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

  return (
    <div className="application-card">
      <h3>{item.title}</h3>
      <p className="company">{item.company}</p>
      <p className="date">{item.date}</p>
      <div className="flex items-center justify-between">
        <span className={`status ${getStatusClass(item.status)}`}>
          {getStatusText(item.status)}
        </span>
        {item.cvUrl && item.cvUrl !== "#" && (
          <a href={item.cvUrl} className="text-sm text-rose-600 hover:underline">
            Voir CV
          </a>
        )}
      </div>
    </div>
  );
}