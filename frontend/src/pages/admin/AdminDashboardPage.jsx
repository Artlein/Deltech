import React, { useState, useEffect } from 'react';
import axios from '../../api/axios';
import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend } from 'chart.js';
import { Bar } from 'react-chartjs-2';

ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend);

export default function AdminDashboardPage() {
  const [stats, setStats] = useState(null);

  useEffect(() => {
    axios.get('/admin/dashboard')
      .then(res => setStats(res.data))
      .catch(err => console.error(err));
  }, []);

  if (!stats) return <div className="container">Loading Dashboard...</div>;

  const chartData = {
    labels: ['Sales (USD)', 'Customers', 'Tickets', 'Active Chats'],
    datasets: [
      {
        label: 'Platform Activity Metrics',
        data: [stats.totalSales, stats.totalCustomers, stats.totalTickets, stats.totalInquiries],
        backgroundColor: 'rgba(74, 20, 140, 0.6)',
        borderColor: 'rgba(74, 20, 140, 1)',
        borderWidth: 1,
      },
    ],
  };

  return (
    <div className="container">
      <h1 style={{ color: '#280068' }}>Admin Console Dashboard</h1>
      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(4, 1fr)', gap: '1.5rem', margin: '2rem 0' }}>
        <div className="card" style={{ textAlign: 'center', backgroundColor: '#eef2ff' }}>
          <h3>Total Sales</h3>
          <span style={{ fontSize: '1.8rem', fontWeight: 'bold', color: '#4a148c' }}>
            ${stats.totalSales.toFixed(2)}
          </span>
        </div>
        <div className="card" style={{ textAlign: 'center', backgroundColor: '#ecfdf5' }}>
          <h3>Customers</h3>
          <span style={{ fontSize: '1.8rem', fontWeight: 'bold', color: '#059669' }}>
            {stats.totalCustomers}
          </span>
        </div>
        <div className="card" style={{ textAlign: 'center', backgroundColor: '#fffbeb' }}>
          <h3>Support Tickets</h3>
          <span style={{ fontSize: '1.8rem', fontWeight: 'bold', color: '#d97706' }}>
            {stats.totalTickets}
          </span>
        </div>
        <div className="card" style={{ textAlign: 'center', backgroundColor: '#fdf2f8' }}>
          <h3>Open inquiries</h3>
          <span style={{ fontSize: '1.8rem', fontWeight: 'bold', color: '#db2777' }}>
            {stats.totalInquiries}
          </span>
        </div>
      </div>

      <div className="card" style={{ padding: '2rem', marginTop: '2rem' }}>
        <h3>Activity Graph</h3>
        <div style={{ height: '300px' }}>
          <Bar data={chartData} options={{ responsive: true, maintainAspectRatio: false }} />
        </div>
      </div>
    </div>
  );
}
