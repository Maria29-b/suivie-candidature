import { useNavigate, Link } from "react-router-dom";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";

const schema = yup.object({
  company: yup.string().required("Champ requis"),
  title: yup.string().required("Champ requis"),
  description: yup.string().nullable(),
  applied_date: yup.string().required("Champ requis"),
  status: yup.string().oneOf(["PENDING","ACCEPTED","REJECTED","IN PROCESS"]).required(),
  cv: yup.mixed().nullable(),
  cover: yup.mixed().nullable(),
});

export default function NewApplication() {
  const navigate = useNavigate();
  const { register, handleSubmit, formState: { errors } } = useForm({
    resolver: yupResolver(schema),
    defaultValues: {
      status: "PENDING",
    }
  });

  const onSubmit = async (values) => {
    // Exemple côté front : préparer FormData pour upload
    const fd = new FormData();
    fd.append("company", values.company);
    fd.append("title", values.title);
    fd.append("description", values.description || "");
    fd.append("applied_date", values.applied_date);
    fd.append("status", values.status);
    if (values.cv?.[0]) fd.append("cv", values.cv[0]);
    if (values.cover?.[0]) fd.append("cover", values.cover[0]);

    // TODO: envoyer à votre backend Django (endpoint POST /api/applications/)
    // await api.post("applications/", fd, { headers: { "Content-Type": "multipart/form-data" } });

    navigate("/"); // retour au dashboard après succès
  };

  return (
    <div className="min-h-screen bg-gray-50">
      {/* Top bar light */}
      <div className="bg-white border-b">
        <div className="mx-auto max-w-3xl px-4 py-4">
          <Link to="/" className="text-sm text-gray-600 hover:underline">← Retour</Link>
        </div>
      </div>

      <div className="mx-auto max-w-3xl px-4 py-8">
        <div className="rounded-2xl border bg-white p-6 md:p-8">
          <h1 className="text-2xl font-semibold">Nouvelle candidature</h1>
          <p className="text-gray-600 mt-1">Remplissez les informations de votre candidature</p>

          <form onSubmit={handleSubmit(onSubmit)} className="mt-8 space-y-6" encType="multipart/form-data">
            {/* Entreprise */}
            <div>
              <label className="block text-sm font-medium">Entreprise *</label>
              <input
                className="mt-1 w-full rounded-lg border px-3 py-2 focus:outline-none focus:ring-2 focus:ring-rose-400"
                placeholder="Ex: Tech Company"
                {...register("company")}
              />
              {errors.company && <p className="text-sm text-red-600 mt-1">{errors.company.message}</p>}
            </div>

            {/* Titre du poste */}
            <div>
              <label className="block text-sm font-medium">Titre du poste *</label>
              <input
                className="mt-1 w-full rounded-lg border px-3 py-2 focus:outline-none focus:ring-2 focus:ring-rose-400"
                placeholder="Ex: Développeur Full Stack"
                {...register("title")}
              />
              {errors.title && <p className="text-sm text-red-600 mt-1">{errors.title.message}</p>}
            </div>

            {/* Description */}
            <div>
              <label className="block text-sm font-medium">Description du poste</label>
              <textarea
                rows={4}
                className="mt-1 w-full rounded-lg border px-3 py-2 focus:outline-none focus:ring-2 focus:ring-rose-400"
                placeholder="Décrivez brièvement le poste..."
                {...register("description")}
              />
            </div>

            {/* Date */}
            <div>
              <label className="block text-sm font-medium">Date de candidature *</label>
              <input
                type="date"
                className="mt-1 w-full rounded-lg border px-3 py-2 focus:outline-none focus:ring-2 focus:ring-rose-400"
                {...register("applied_date")}
              />
              {errors.applied_date && <p className="text-sm text-red-600 mt-1">{errors.applied_date.message}</p>}
            </div>

            {/* Statut */}
            <div>
              <label className="block text-sm font-medium">Statut</label>
              <select
                className="mt-1 w-full rounded-lg border px-3 py-2 bg-white focus:outline-none focus:ring-2 focus:ring-rose-400"
                {...register("status")}
                defaultValue="PENDING"
              >
                <option value="PENDING">En attente</option>
                <option value="IN_PROCESS">En cours</option>
                <option value="ACCEPTED">Accepté</option>
                <option value="REJECTED">Refusé</option>
              </select>
            </div>

            {/* Fichiers */}
            <div className="space-y-4">
              <div>
                <label className="block text-sm font-medium">CV</label>
                <input type="file" accept=".pdf,.doc,.docx" {...register("cv")} />
                <p className="text-xs text-gray-500">Formats acceptés : PDF, DOC, DOCX</p>
              </div>
              <div>
                <label className="block text-sm font-medium">Lettre de motivation</label>
                <input type="file" accept=".pdf,.doc,.docx" {...register("cover")} />
                <p className="text-xs text-gray-500">Formats acceptés : PDF, DOC, DOCX</p>
              </div>
            </div>

            {/* Actions */}
            <div className="flex items-center justify-end gap-4 pt-2">
              <Link to="/" className="px-4 py-2 rounded-lg border hover:bg-gray-50">
                Annuler
              </Link>
              <button
                type="submit"
                className="px-5 py-2.5 rounded-lg bg-rose-600 text-white hover:bg-rose-700"
              >
                Ajouter la candidature
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
