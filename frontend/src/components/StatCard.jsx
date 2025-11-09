export default function StatCard({ title, value, dotColor }) {
  return (
    <div className="rounded-xl border bg-white p-5 flex items-center justify-between">
      <div>
        <p className="text-sm text-gray-500">{title}</p>
        <p className="text-2xl font-semibold mt-1">{value}</p>
      </div>
      {dotColor && <span className={`h-3 w-3 rounded-full ${dotColor}`} />}
    </div>
  );
}
