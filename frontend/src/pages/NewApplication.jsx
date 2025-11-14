import { useNavigate, Link } from "react-router-dom";
import { useState } from "react";
import { useAuth } from "../context/AuthContext"; // pour récupérer l'idUser
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";

const schema = yup.object({
  company: yup.string().required("Champ requis"),
  title: yup.string().required("Champ requis"),
  description: yup.string().nullable(),
  applied_date: yup.string().required("Champ requis"),
  status: yup.string().oneOf(["PENDING","ACCEPTED","REJECTED","IN_PROCESS"]).required(),
  cv: yup.mixed().nullable(),
  cover: yup.mixed().nullable(),
});

export default function NewApplication() {
  const navigate = useNavigate();
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [submitError, setSubmitError] = useState(null);
  const { user } = useAuth(); // récupère l'utilisateur courant, contient idUser et token
  const { register, handleSubmit, formState: { errors } } = useForm({
    resolver: yupResolver(schema),
    defaultValues: { status: "PENDING" }
  });

  const onSubmit = async (values) => {
    setIsSubmitting(true);
    setSubmitError(null);

    // Build JSON payload avec idUser
    const payload = {
      company: values.company,
      title: values.title,
      description: values.description || "",
      appliedDate: values.applied_date,
      status: values.status,
      idUser: user?.idUser // ajouté pour respecter la FK
    };

    try {
      const token = localStorage.getItem("token"); // ou user?.token si stocké dans le contexte
      const response = await fetch("http://localhost:8080/api/applications", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          ...(token ? { Authorization: `Bearer ${token}` } : {}),
        },
        body: JSON.stringify(payload),
      });

      if (!response.ok) {
        const ct = response.headers.get("content-type") || "";
        let errMsg = "Erreur lors de la création";
        try {
          if (ct.includes("application/json")) {
            const err = await response.json();
            errMsg = err?.message || JSON.stringify(err) || errMsg;
          } else {
            errMsg = await response.text();
          }
        } catch (e) {}
        throw new Error(errMsg);
      }

      const data = await response.json();
      console.log("Candidature créée :", data);
      navigate("/dashboard"); // retour au dashboard
    } catch (err) {
      console.error(err);
      setSubmitError(err.message || 'Erreur lors de la création');
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="min-h-screen bg-gray-50">
      <div className="bg-white border-b">
        <div className="mx-auto max-w-3xl px-4 py-4">
          <Link to="/dashboard" className="text-sm text-gray-600 hover:underline">← Retour</Link>
        </div>
      </div>

      <div className="mx-auto max-w-3xl px-4 py-8">
        <div className="rounded-2xl border bg-white p-6 md:p-8">
          <h1 className="text-2xl font-semibold">Nouvelle candidature</h1>
          <p className="text-gray-600 mt-1">Remplissez les informations de votre candidature</p>

          <form onSubmit={handleSubmit(onSubmit)} className="mt-8 space-y-6" encType="multipart/form-data">
            {/* Le reste du formulaire reste inchangé */}
            <div>
              <label className="block text-sm font-medium">Entreprise *</label>
              <input
                className="mt-1 w-full rounded-lg border px-3 py-2 focus:outline-none focus:ring-2 focus:ring-rose-400"
                placeholder="Ex: Tech Company"
                {...register("company")}
              />
              {errors.company && <p className="text-sm text-red-600 mt-1">{errors.company.message}</p>}
            </div>

            <div>
              <label className="block text-sm font-medium">Titre du poste *</label>
              <input
                className="mt-1 w-full rounded-lg border px-3 py-2 focus:outline-none focus:ring-2 focus:ring-rose-400"
                placeholder="Ex: Développeur Full Stack"
                {...register("title")}
              />
              {errors.title && <p className="text-sm text-red-600 mt-1">{errors.title.message}</p>}
            </div>

            <div>
              <label className="block text-sm font-medium">Description du poste</label>
              <textarea
                rows={4}
                className="mt-1 w-full rounded-lg border px-3 py-2 focus:outline-none focus:ring-2 focus:ring-rose-400"
                placeholder="Décrivez brièvement le poste..."
                {...register("description")}
              />
            </div>

            <div>
              <label className="block text-sm font-medium">Date de candidature *</label>
              <input
                type="date"
                className="mt-1 w-full rounded-lg border px-3 py-2 focus:outline-none focus:ring-2 focus:ring-rose-400"
                {...register("applied_date")}
              />
              {errors.applied_date && <p className="text-sm text-red-600 mt-1">{errors.applied_date.message}</p>}
            </div>

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

            <div className="flex items-center justify-end gap-4 pt-2">
              <Link to="/dashboard" className="px-4 py-2 rounded-lg border hover:bg-gray-50">
                Annuler
              </Link>
              <button
                type="submit"
                disabled={isSubmitting}
                className={`px-5 py-2.5 rounded-lg bg-rose-600 text-white hover:bg-rose-700 ${isSubmitting ? 'opacity-60 cursor-not-allowed' : ''}`}
              >
                {isSubmitting ? 'Enregistrement...' : 'Ajouter la candidature'}
              </button>
            </div>
            {submitError && (
              <div className="text-red-600 text-sm pt-2">{submitError}</div>
            )}
          </form>
        </div>
      </div>
    </div>
  );
}
