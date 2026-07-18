import React, { useState, useEffect } from 'react';
import axios from '../../api/axios';

export default function OrdersPage() {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    axios.get('/orders/my')
      .then(res => setOrders(res.data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div className="container">
      <h1 style={{ color: '#280068' }}>My Reservations</h1>
      {orders.length === 0 ? (
        <p>You have not placed any reservations yet.</p>
      ) : (
        <div style={{ marginTop: '2rem' }}>
          {orders.map(order => (
            <div key={order.id} className="card" style={{ marginBottom: '1.5rem', borderLeft: '5px solid #4a148c' }}>
              <div style={{ display: 'flex', justifyContent: 'space-between', flexWrap: 'wrap', gap: '1rem' }}>
                <div>
                  <h3 style={{ margin: 0, color: '#4a148c' }}>Order {order.ordersNumber}</h3>
                  <span style={{ fontSize: '0.9rem', color: '#666' }}>Placed on: {new Date(order.orderDate).toLocaleDateString()}</span>
                </div>
                <div>
                  <span style={{
                    padding: '0.3rem 0.8rem',
                    borderRadius: '20px',
                    fontSize: '0.85rem',
                    fontWeight: 'bold',
                    backgroundColor: order.orderStatus === 'completed' ? '#d4edda' : '#fff3cd',
                    color: order.orderStatus === 'completed' ? '#155724' : '#856404',
                    textTransform: 'uppercase'
                  }}>
                    {order.orderStatus}
                  </span>
                </div>
              </div>

              <div style={{ marginTop: '1rem', borderTop: '1px solid #eee', paddingTop: '1rem', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                <div>
                  <strong>{order.productName}</strong> x {order.productQuantity}
                </div>
                <div style={{ fontWeight: 'bold' }}>
                  Subtotal: {order.currency} {parseFloat(order.subtotal).toFixed(2)}
                </div>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
