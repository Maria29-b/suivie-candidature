export default function StatCard({ title, value, dotColor }) {
  return (
    <div className="stat-card">
      <h3>{title}</h3>
      <div className="flex items-center">
        {dotColor && <span className={`dot ${dotColor}`}></span>}
        <span className="value">{value}</span>
      </div>
    </div>
  );
}