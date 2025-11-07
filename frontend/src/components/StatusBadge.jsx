export default function StatusBadge({ status }) {
  const map = {
    PENDING:   { label: "En attente",  cls: "bg-blue-100 text-blue-700" },
    IN_PROCESS:{ label: "En cours",    cls: "bg-yellow-100 text-yellow-700" },
    ACCEPTED:  { label: "Accepté",     cls: "bg-green-100 text-green-700" },
    REJECTED:  { label: "Refusé",      cls: "bg-red-100 text-red-700" },
  };

  const s = map[status] ?? map.PENDING;

  return (
    <span
      className={`inline-flex items-center px-3 py-1 rounded-full text-sm font-medium ${s.cls}`}
    >
      {s.label}
    </span>
  );
}
