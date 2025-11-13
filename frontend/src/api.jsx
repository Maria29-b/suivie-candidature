import axios from "axios";

const API_URL = "http://localhost:8080/api/cvs";

export const getCvById = (idCv) => axios.get(`${API_URL}/${idCv}`);
export const updateCv = (idCv, cv) => axios.put(`${API_URL}/${idCv}`, cv);
export const deleteCv = (idCv) => axios.delete(`${API_URL}/${idCv}`);
