import React, { useState, useEffect } from 'react';
import axios from '../../api/axios';

export default function AdminCustomersPage() {
  const [customers, setCustomers] = useState([]);

  const fetchCustomers = () => {
    axios.get('/admin/customers')
      .then(res => setCustomers(res.data))
      .catch(err => console.error(err));
  };

  useEffect(() => {
    fetchCustomers();
  }, []);

  const handleVerify = async (id, status) => {
    try {
      await axios.put(`/admin/customers/${id}/verify?isVerified=${status}`);
      alert('Verification status updated');
      fetchCustomers();
    } catch (err) {
      alert('Failed to update status');
    }
  };

  return (
    <div className="container">
      <h2 style={{ color: '#280068' }}>Customer Verification Manager</h2>
      <div className="card" style={{ marginTop: '2rem', padding: 0, overflowX: 'auto' }}>
        <table style={{ width: '100%', borderCollapse: 'collapse', textAlign: 'left' }}>
          <thead>
            <tr style={{ backgroundColor: '#f0f0f5', borderBottom: '2px solid #ddd' }}>
              <th style={{ padding: '1rem' }}>Name</th>
              <th style={{ padding: '1rem' }}>Username</th>
              <th style={{ padding: '1rem' }}>Email</th>
              <th style={{ padding: '1rem' }}>Phone</th>
              <th style={{ padding: '1rem' }}>Status</th>
              <th style={{ padding: '1rem' }}>Actions</th>
            </tr>
          </thead>
          <tbody>
            {customers.map(customer => (
              <tr key={customer.id} style={{ borderBottom: '1px solid #eee' }}>
                <td style={{ padding: '1rem' }}>{customer.nameCustomer}</td>
                <td style={{ padding: '1rem' }}>{customer.username}</td>
                <td style={{ padding: '1rem' }}>{customer.emailCustomer}</td>
                <td style={{ padding: '1rem' }}>{customer.phoneCustomer}</td>
                <td style={{ padding: '1rem' }}>
                  <span style={{
                    padding: '0.2rem 0.5rem',
                    borderRadius: '4px',
                    fontSize: '0.8rem',
                    fontWeight: 'bold',
                    backgroundColor: customer.isVerified ? '#d4edda' : '#f8d7da',
                    color: customer.isVerified ? '#155724' : '#721c24'
                  }}>
                    {customer.isVerified ? 'VERIFIED' : 'PENDING'}
                  </span>
                </td>
                <td style={{ padding: '1rem' }}>
                  {customer.isVerified ? (
                    <button onClick={() => handleVerify(customer.id, false)} className="btn btn-secondary" style={{ padding: '0.3rem 0.8rem', fontSize: '0.8rem' }}>Deactivate</button>
                  ) : (
                    <button onClick={() => handleVerify(customer.id, true)} className="btn btn-primary" style={{ padding: '0.3rem 0.8rem', fontSize: '0.8rem' }}>Verify</button>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
