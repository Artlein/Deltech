import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from '../../api/axios';
import { useCart } from '../../hooks/useCart';
import { useAuth } from '../../hooks/useAuth';

export default function ProductDetailPage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const { user } = useAuth();
  const { addToCart } = useCart();
  const [product, setProduct] = useState(null);
  const [inquiry, setInquiry] = useState('');

  useEffect(() => {
    axios.get(`/products/${id}`)
      .then(res => setProduct(res.data))
      .catch(err => console.error(err));
  }, [id]);

  const handleInquiry = async (e) => {
    e.preventDefault();
    if (!user) {
      alert('Please log in to submit inquiries.');
      return;
    }
    try {
      const res = await axios.post('/tickets', {
        productId: product.id,
        initialMessage: inquiry
      });
      alert('Support ticket created successfully!');
      navigate(`/chat/${res.data.ticketId}`);
    } catch (err) {
      alert('Failed to submit ticket');
    }
  };

  if (!product) return <div className="container">Loading...</div>;

  return (
    <div className="container">
      <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '3rem', margin: '2rem 0' }}>
        <div>
          <img
            src={product.imgProduct ? `/uploads/${product.imgProduct}` : 'https://placehold.co/500x400'}
            alt={product.nameProduct}
            style={{ width: '100%', borderRadius: '8px', objectFit: 'contain' }}
          />
        </div>
        <div>
          <h1 style={{ color: '#4a148c', marginTop: 0 }}>{product.nameProduct}</h1>
          <span style={{ fontSize: '1.5rem', fontWeight: 'bold', display: 'block', margin: '1rem 0' }}>
            {product.currency} {product.priceProduct.toFixed(2)}
          </span>
          <p style={{ whiteSpace: 'pre-line', lineHeight: '1.6', color: '#555' }}>
            {product.descriptionProduct}
          </p>

          <div style={{ marginTop: '2rem', display: 'flex', gap: '1rem' }}>
            <button onClick={() => addToCart(product.id, 1)} className="btn btn-primary">
              Reserve Item
            </button>
          </div>

          <div style={{ marginTop: '3rem', padding: '1.5rem', backgroundColor: '#f0f0f5', borderRadius: '8px' }}>
            <h3>Inquire about this product</h3>
            <form onSubmit={handleInquiry}>
              <textarea
                value={inquiry}
                onChange={e => setInquiry(e.target.value)}
                placeholder="Ask us anything about this equipment..."
                rows="4"
                className="form-control"
                style={{ marginBottom: '1rem', resize: 'vertical' }}
                required
              />
              <button type="submit" className="btn btn-secondary">Submit Inquiry</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
