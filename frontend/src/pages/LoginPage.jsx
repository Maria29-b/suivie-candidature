import { useState } from 'react';
import { Eye, EyeOff, Briefcase } from 'lucide-react';
import './LoginPage.css'; // Importez le fichier CSS

export default function LoginPage() {
  const [showPassword, setShowPassword] = useState(false);
  const [formData, setFormData] = useState({
    email: '',
    password: ''
  });
  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();
    setIsLoading(true);
    
    setTimeout(() => {
      console.log('Connexion avec:', formData);
      setIsLoading(false);
      alert('Connexion réussie ! Redirection vers le dashboard...');
    }, 1500);
  };

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  return (
    <div className="login-container">
      <div className="login-content">
        {/* Logo et titre */}
        <div className="login-header">
          <div className="logo-box">
            <Briefcase className="logo-icon" />
          </div>
          <h1 className="login-title">TrackMyJob</h1>
          <p className="login-subtitle">Connectez-vous à votre compte</p>
        </div>

        {/* Formulaire de connexion */}
        <div className="login-card">
          <div className="form-container">
            {/* Pseudo*/}
            <div className="form-group">
              <label htmlFor="text" className="form-label">
                Pseudo
              </label>
              <input
                type="text"
                id="pseudo"
                name="pseudo"
                value={formData.text}
                onChange={handleChange}
                required
                className="form-input"
                placeholder="Votre pseudo"
              />
            </div>

            {/* Mot de passe */}
            <div className="form-group">
              <label htmlFor="password" className="form-label">
                Mot de passe
              </label>
              <div className="password-input-container">
                <input
                  type={showPassword ? 'text' : 'password'}
                  id="password"
                  name="password"
                  value={formData.password}
                  onChange={handleChange}
                  required
                  className="form-input password-input"
                  placeholder="••••••••"
                />
                <button
                  type="button"
                  onClick={() => setShowPassword(!showPassword)}
                  className="password-toggle"
                >
                  {showPassword ? <EyeOff size={20} /> : <Eye size={20} />}
                </button>
              </div>
            </div>

            {/* Bouton de connexion */}
            <button
              onClick={handleSubmit}
              disabled={isLoading}
              className={`submit-button ${isLoading ? 'loading' : ''}`}
            >
              {isLoading ? (
                <span className="loading-content">
                  <svg className="spinner" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                    <circle className="spinner-circle" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4"></circle>
                    <path className="spinner-path" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                  </svg>
                  Connexion...
                </span>
              ) : (
                'Se connecter'
              )}
            </button>
          </div>

        </div>

        {/* Footer */}
        <p className="footer-text">
          En vous connectant, vous acceptez nos{' '}
          <button className="footer-link">Conditions d'utilisation</button>
          {' '}et notre{' '}
          <button className="footer-link">Politique de confidentialité</button>
        </p>
      </div>
    </div>
  );
}