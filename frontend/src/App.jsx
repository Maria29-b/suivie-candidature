import React from 'react'; // optionnel si ton environnement supporte JSX sans import React
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Dashboard from "./pages/Dashboard";
import NewApplication from "./pages/NewApplication";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Dashboard />} />
        <Route path="/new-application" element={<NewApplication />} />
      </Routes>
    </Router>
  );
}

export default App;
