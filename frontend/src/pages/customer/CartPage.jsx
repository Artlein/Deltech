import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useCart } from '../../hooks/useCart';
import axios from '../../api/axios';

export default function CartPage() {
  const { cartItems, updateCartItem, removeFromCart, getSubtotal, clearCart } = useCart();
  const [note, setNote] = useState('');
  const navigate = useNavigate();

  const handleCheckout = async () => {
    try {
      await axios.post('/orders/checkout', { noteCustomer: note });
      alert('Reservation submitted successfully! An email summary has been sent.');
      clearCart();
      navigate('/orders');
    } catch (err) {
      alert('Checkout failed: ' + (err.response?.data || err.message));
    }
  };

  if (cartItems.length === 0) {
    return (
      <div className="container" style={{ textAlign: 'center', padding: '5rem 2rem' }}>
        <h2>Your reservation cart is empty</h2>
        <button onClick={() => navigate('/')} className="btn btn-primary" style={{ marginTop: '1.5rem' }}>
          Back to Shop
        </button>
      </div>
    );
  }

  return (
    <div className="container">
      <h1 style={{ color: '#280068' }}><i className="fa fa-shopping-cart"></i> Reserved Items List</h1>
      <div style={{ display: 'grid', gridTemplateColumns: '2fr 1fr', gap: '2rem', marginTop: '2rem' }}>
        <div>
          {cartItems.map(item => (
            <div key={item.id} className="card" style={{
              display: 'flex',
              justifyContent: 'space-between',
              alignItems: 'center',
              marginBottom: '1rem'
            }}>
              <div>
                <h3 style={{ margin: 0 }}>{item.productName}</h3>
                <span style={{ color: '#666' }}>{item.currency} {item.productPrice.toFixed(2)}</span>
              </div>
              <div style={{ display: 'flex', alignItems: 'center', gap: '1rem' }}>
                <input
                  type="number"
                  min="1"
                  value={item.quantity}
                  onChange={e => updateCartItem(item.product.id, parseInt(e.target.value))}
                  className="form-control"
                  style={{ width: '80px', textAlign: 'center' }}
                />
                <button
                  onClick={() => removeFromCart(item.product.id)}
                  style={{ background: 'transparent', border: 'none', color: '#e74c3c', cursor: 'pointer', fontSize: '1.2rem' }}
                >
                  <i className="fa fa-trash"></i>
                </button>
              </div>
            </div>
          ))}
        </div>

        <div className="card" style={{ height: 'fit-content' }}>
          <h3>Summary</h3>
          <div style={{ display: 'flex', justifySelf: 'stretch', justifyContent: 'space-between', margin: '1rem 0', fontWeight: 'bold' }}>
            <span>Subtotal</span>
            <span>{cartItems[0]?.currency} {getSubtotal().toFixed(2)}</span>
          </div>

          <div className="form-group">
            <label>Reservation Note / Customer Name</label>
            <input
              type="text"
              value={note}
              onChange={e => setNote(e.target.value)}
              placeholder="e.g. Rush order / Company Delivery"
              className="form-control"
            />
          </div>

          <button onClick={handleCheckout} className="btn btn-primary" style={{ width: '100%', marginTop: '1rem' }}>
            Place Reservation Request
          </button>
        </div>
      </div>
    </div>
  );
}
