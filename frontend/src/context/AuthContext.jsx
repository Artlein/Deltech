import React, { createContext, useState, useEffect } from 'react';
import axios from '../api/axios';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [token, setToken] = useState(localStorage.getItem('token'));
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (token) {
      localStorage.setItem('token', token);
      // Decode simple claims or request verification if needed.
      // For simple local session restoration, parsing JWT is sufficient or we store user object
      const savedUser = localStorage.getItem('user');
      if (savedUser) {
        setUser(JSON.parse(savedUser));
      }
    } else {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      setUser(null);
    }
    setLoading(false);
  }, [token]);

  const login = async (username, password, otp, isAdmin = false) => {
    const endpoint = isAdmin ? '/auth/admin/login' : '/auth/customer/login';
    const response = await axios.post(endpoint, { username, password, otp });
    
    if (response.data.is2faRequired) {
      return { is2faRequired: true };
    }

    if (response.data.token) {
      setToken(response.data.token);
      const userObj = {
        id: response.data.id,
        username: response.data.username,
        fullname: response.data.fullname,
        role: response.data.role,
      };
      setUser(userObj);
      localStorage.setItem('user', JSON.stringify(userObj));
    }
    return response.data;
  };

  const register = async (name, username, email, phone, address, password) => {
    return await axios.post('/auth/customer/register', {
      name, username, email, phone, address, password
    });
  };

  const logout = () => {
    setToken(null);
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, token, loading, login, register, logout }}>
      {!loading && children}
    </AuthContext.Provider>
  );
};
