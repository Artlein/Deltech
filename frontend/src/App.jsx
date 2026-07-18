import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext';
import { CartProvider } from './context/CartContext';
import Navbar from './components/Navbar';
import Footer from './components/Footer';

// Customer Pages
import ShopPage from './pages/customer/ShopPage';
import ProductDetailPage from './pages/customer/ProductDetailPage';
import CartPage from './pages/customer/CartPage';
import LoginPage from './pages/customer/LoginPage';
import RegisterPage from './pages/customer/RegisterPage';
import OrdersPage from './pages/customer/OrdersPage';
import ChatPage from './pages/customer/ChatPage';

// Admin Pages
import AdminDashboardPage from './pages/admin/AdminDashboardPage';
import AdminCustomersPage from './pages/admin/AdminCustomersPage';

import { useAuth } from './hooks/useAuth';

// Protected Route components
const CustomerRoute = ({ children }) => {
  const { user } = useAuth();
  return user ? children : <Navigate to="/login" />;
};

const AdminRoute = ({ children }) => {
  const { user } = useAuth();
  const isAdmin = user && (user.role.includes('ADMIN'));
  return isAdmin ? children : <Navigate to="/login" />;
};

export default function App() {
  return (
    <AuthProvider>
      <CartProvider>
        <Router>
          <div style={{ display: 'flex', flexDirection: 'column', minHeight: '100vh' }}>
            <Navbar />
            <div style={{ flex: 1 }}>
              <Routes>
                {/* Public Customer Routes */}
                <Route path="/" element={<ShopPage />} />
                <Route path="/products/:id" element={<ProductDetailPage />} />
                <Route path="/login" element={<LoginPage />} />
                <Route path="/register" element={<RegisterPage />} />
                <Route path="/about" element={<div className="container"><h2>About Deltech</h2><p>Deltech Parking Systems & Solutions Inc. is a premier provider of automated parking systems, entry/exit terminals, barrier arms, and surveillance integration in the Philippines.</p></div>} />
                <Route path="/contact" element={<div className="container"><h2>Contact Us</h2><p>Address: J.P. Rizal St., Makati City, Philippines<br/>Email: contact@deltech.com</p></div>} />

                {/* Protected Customer Routes */}
                <Route path="/cart" element={<CustomerRoute><CartPage /></CustomerRoute>} />
                <Route path="/orders" element={<CustomerRoute><OrdersPage /></CustomerRoute>} />
                <Route path="/chat/:ticketId" element={<CustomerRoute><ChatPage /></CustomerRoute>} />

                {/* Protected Admin Routes */}
                <Route path="/admin/dashboard" element={<AdminRoute><AdminDashboardPage /></AdminRoute>} />
                <Route path="/admin/customers" element={<AdminRoute><AdminCustomersPage /></AdminRoute>} />
              </Routes>
            </div>
            <Footer />
          </div>
        </Router>
      </CartProvider>
    </AuthProvider>
  );
}
