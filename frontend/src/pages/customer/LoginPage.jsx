import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth';

export default function LoginPage() {
  const { login } = useAuth();
  const navigate = useNavigate();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [otp, setOtp] = useState('');
  const [isAdmin, setIsAdmin] = useState(false);
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    try {
      const res = await login(username, password, otp, isAdmin);
      if (res && res.token) {
        if (isAdmin) {
          navigate('/admin/dashboard');
        } else {
          navigate('/');
        }
      }
    } catch (err) {
      setError(err.response?.data?.message || err.message || 'Login failed');
    }
  };

  return (
    <div style={{
      minHeight: '80vh',
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      padding: '2rem'
    }}>
      <div className="card" style={{ width: '100%', maxWidth: '450px', padding: '2.5rem' }}>
        <h2 style={{ textAlign: 'center', color: '#280068', textTransform: 'uppercase', marginBottom: '2rem' }}>
          {isAdmin ? 'Admin Console' : 'Customer Login'}
        </h2>

        {error && <div style={{ color: '#e74c3c', marginBottom: '1.5rem', fontWeight: 'bold', textAlign: 'center' }}>{error}</div>}

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Username</label>
            <input
              type="text"
              className="form-control"
              value={username}
              onChange={e => setUsername(e.target.value)}
              required
            />
          </div>

          <div className="form-group">
            <label>Password</label>
            <input
              type="password"
              className="form-control"
              value={password}
              onChange={e => setPassword(e.target.value)}
              required
            />
          </div>

          <div className="form-group">
            <label>Authenticator OTP (If enabled)</label>
            <input
              type="text"
              className="form-control"
              value={otp}
              onChange={e => setOtp(e.target.value)}
              placeholder="e.g. 123456"
            />
          </div>

          <div className="form-group" style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', margin: '1.5rem 0' }}>
            <input
              type="checkbox"
              id="adminCheckbox"
              checked={isAdmin}
              onChange={e => setIsAdmin(e.target.checked)}
            />
            <label htmlFor="adminCheckbox" style={{ margin: 0, fontWeight: 'normal', cursor: 'pointer' }}>Login as Administrator</label>
          </div>

          <button type="submit" className="btn btn-primary" style={{ width: '100%' }}>Login</button>
        </form>

        {!isAdmin && (
          <p style={{ textAlign: 'center', marginTop: '1.5rem', fontSize: '0.9rem' }}>
            Don't have an account? <Link to="/register" style={{ color: '#4a148c', fontWeight: 'bold' }}>Register here</Link>
          </p>
        )}
      </div>
    </div>
  );
}
