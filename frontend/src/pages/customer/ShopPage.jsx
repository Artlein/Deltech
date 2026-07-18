import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import axios from '../../api/axios';
import { useCart } from '../../hooks/useCart';

export default function ShopPage() {
  const [categorized, setCategorized] = useState({});
  const { addToCart } = useCart();

  useEffect(() => {
    axios.get('/products/categorized')
      .then(res => setCategorized(res.data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div>
      {/* Hero Section */}
      <div style={{
        position: 'relative',
        background: 'linear-gradient(135deg, #4a148c, #280068)',
        color: 'white',
        padding: '5rem 2rem',
        textAlign: 'center'
      }}>
        <div style={{ maxWidth: '800px', margin: '0 auto' }}>
          <h1 style={{ fontSize: '3rem', margin: 0, textTransform: 'uppercase' }}>Products</h1>
          <p style={{ fontSize: '1.2rem', marginTop: '1.5rem', lineHeight: '1.6', opacity: 0.9 }}>
            At Deltech Parking Systems and Solutions Inc., we provide state-of-the-art parking equipment and systems designed for efficiency, security, and convenience.
          </p>
        </div>
      </div>

      <div className="container">
        {/* Categories navigation */}
        <div style={{
          display: 'flex',
          justifyContent: 'center',
          gap: '1.5rem',
          margin: '2rem 0',
          fontWeight: 'bold',
          color: '#4a148c'
        }}>
          {Object.keys(categorized).map(cat => (
            <a key={cat} href={`#${cat}`} style={{ textTransform: 'uppercase' }}>{cat}</a>
          ))}
        </div>

        {/* Category Sections */}
        {Object.entries(categorized).map(([category, list]) => (
          <div key={category} id={category} style={{ marginBottom: '3rem' }}>
            <h2 style={{
              fontSize: '2rem',
              color: '#4a148c',
              borderBottom: '2px solid #4a148c',
              paddingBottom: '0.5rem',
              textTransform: 'uppercase',
              marginBottom: '1.5rem'
            }}>{category}</h2>

            <div className="grid-3">
              {list.map(product => (
                <div key={product.id} className="card" style={{
                  display: 'flex',
                  flexDirection: 'column',
                  justifyContent: 'space-between'
                }}>
                  <div>
                    <img
                      src={product.imgProduct ? `/uploads/${product.imgProduct}` : 'https://placehold.co/300x200'}
                      alt={product.nameProduct}
                      style={{ width: '100%', height: '200px', objectFit: 'contain', marginBottom: '1rem' }}
                    />
                    <h3 style={{ margin: '0 0 0.5rem 0', fontSize: '1.2rem' }}>
                      <Link to={`/products/${product.id}`} style={{ color: '#4a148c' }}>{product.nameProduct}</Link>
                    </h3>
                    <p style={{ color: '#666', fontSize: '0.9rem', height: '60px', overflow: 'hidden' }}>
                      {product.descriptionProduct}
                    </p>
                  </div>
                  <div style={{
                    marginTop: '1.5rem',
                    display: 'flex',
                    justifyContent: 'space-between',
                    alignItems: 'center'
                  }}>
                    <span style={{ fontSize: '1.2rem', fontWeight: 'bold' }}>
                      {product.currency} {product.priceProduct.toFixed(2)}
                    </span>
                    <button
                      onClick={() => addToCart(product.id, 1)}
                      className="btn btn-primary"
                      style={{ padding: '0.5rem 1rem', fontSize: '0.9rem' }}
                    >
                      Reserve
                    </button>
                  </div>
                </div>
              ))}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
