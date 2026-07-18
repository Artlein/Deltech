import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth';
import { useCart } from '../hooks/useCart';

export default function Navbar() {
  const { user, logout } = useAuth();
  const { cartItems } = useCart();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/');
  };

  const isAdmin = user && (user.role.includes('ADMIN'));

  return (
    <nav style={{
      backgroundColor: '#280068',
      color: '#fff',
      padding: '1rem 2rem',
      display: 'flex',
      justifyContent: 'space-between',
      alignItems: 'center',
      boxShadow: '0 4px 10px rgba(0,0,0,0.1)'
    }}>
      <div style={{ fontSize: '1.5rem', fontWeight: 'bold' }}>
        <Link to="/">DELTECH</Link>
      </div>
      <div style={{ display: 'flex', gap: '1.5rem', alignItems: 'center' }}>
        <Link to="/">Shop</Link>
        <Link to="/about">About Us</Link>
        <Link to="/contact">Contact</Link>
        
        {user ? (
          <>
            {isAdmin ? (
              <Link to="/admin/dashboard" style={{ color: '#FFC300', fontWeight: 'bold' }}>Admin Console</Link>
            ) : (
              <>
                <Link to="/cart">
                  <i className="fa fa-shopping-cart"></i> Cart ({cartItems.length})
                </Link>
                <Link to="/profile">Profile</Link>
                <Link to="/orders">My Orders</Link>
              </>
            )}
            <span style={{ fontSize: '0.9rem', opacity: 0.8 }}>Hi, {user.fullname}</span>
            <button onClick={handleLogout} style={{
              background: 'transparent',
              color: '#fff',
              border: '1px solid #fff',
              padding: '0.4rem 0.8rem',
              borderRadius: '4px',
              cursor: 'pointer'
            }}>Logout</button>
          </>
        ) : (
          <>
            <Link to="/login" className="btn btn-secondary" style={{ padding: '0.4rem 1rem', color: '#333' }}>Login</Link>
          </>
        )}
      </div>
    </nav>
  );
}
