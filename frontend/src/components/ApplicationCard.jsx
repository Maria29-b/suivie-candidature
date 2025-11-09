import StatusBadge from "./StatusBadge";
import companyIcon from "../assets/company.png";
import calendarIcon from "../assets/calendar.png";
import documentIcon from "../assets/document.png";

export default function ApplicationCard({ item }) {
  return (
    <div className="rounded-xl border bg-white p-5 shadow-sm hover:shadow-md transition">
      <div className="flex items-start justify-between">
        <div>
          <h3 className="text-lg font-semibold">{item.title}</h3>
          <div className="mt-1 text-sm text-gray-500 flex items-center gap-3">
            
            {/* Entreprise */}
            <div className="flex items-center gap-1">
              <img src={companyIcon} alt="company icon" className="w-4 h-4" />
              {item.company}
            </div>

            {/* Date */}
            <div className="flex items-center gap-1">
              <img src={calendarIcon} alt="calendar icon" className="w-4 h-4" />
              {item.date}
            </div>
          </div>
        </div>

        {/* menu 3 points placeholder */}
        <button className="text-gray-400 hover:text-gray-600">⋮</button>
      </div>

      <div className="mt-4 flex items-center justify-between">
        <StatusBadge status={item.status} />
        <a
          href={item.cvUrl}
          className="text-sm text-gray-700 hover:underline flex items-center gap-2"
          target="_blank"
          rel="noopener noreferrer"
        >
          <img src={documentIcon} alt="cv icon" className="w-4 h-4" />
          {item.cvName}
        </a>
      </div>
    </div>
  );
}
