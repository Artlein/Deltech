import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth';

export default function RegisterPage() {
  const { register } = useAuth();
  const navigate = useNavigate();

  const [name, setName] = useState('');
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [address, setAddress] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setSuccess('');
    try {
      await register(name, username, email, phone, address, password);
      setSuccess('Registration successful! Please wait for admin approval.');
      setTimeout(() => navigate('/login'), 3000);
    } catch (err) {
      setError(err.response?.data || err.message || 'Registration failed');
    }
  };

  return (
    <div style={{ minHeight: '80vh', display: 'flex', alignItems: 'center', justifyContext: 'center', justifyContent: 'center', padding: '2rem' }}>
      <div className="card" style={{ width: '100%', maxWidth: '500px', padding: '2.5rem' }}>
        <h2 style={{ textAlign: 'center', color: '#280068', textTransform: 'uppercase', marginBottom: '2rem' }}>Register Account</h2>

        {error && <div style={{ color: '#e74c3c', marginBottom: '1.5rem', fontWeight: 'bold', textAlign: 'center' }}>{error}</div>}
        {success && <div style={{ color: '#2ecc71', marginBottom: '1.5rem', fontWeight: 'bold', textAlign: 'center' }}>{success}</div>}

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Full Name</label>
            <input type="text" className="form-control" value={name} onChange={e => setName(e.target.value)} required />
          </div>

          <div className="form-group">
            <label>Username</label>
            <input type="text" className="form-control" value={username} onChange={e => setUsername(e.target.value)} required />
          </div>

          <div className="form-group">
            <label>Email Address</label>
            <input type="email" className="form-control" value={email} onChange={e => setEmail(e.target.value)} required />
          </div>

          <div className="form-group">
            <label>Phone Number</label>
            <input type="text" className="form-control" value={phone} onChange={e => setPhone(e.target.value)} required />
          </div>

          <div className="form-group">
            <label>Delivery / Company Address</label>
            <textarea className="form-control" value={address} onChange={e => setAddress(e.target.value)} rows="3" required />
          </div>

          <div className="form-group">
            <label>Password</label>
            <input type="password" className="form-control" value={password} onChange={e => setPassword(e.target.value)} required />
          </div>

          <button type="submit" className="btn btn-primary" style={{ width: '100%', marginTop: '1rem' }}>Register</button>
        </form>

        <p style={{ textAlign: 'center', marginTop: '1.5rem', fontSize: '0.9rem' }}>
          Already have an account? <Link to="/login" style={{ color: '#4a148c', fontWeight: 'bold' }}>Login here</Link>
        </p>
      </div>
    </div>
  );
}
