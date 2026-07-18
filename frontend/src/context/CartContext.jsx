import React, { createContext, useState, useEffect, useContext } from 'react';
import axios from '../api/axios';
import { AuthContext } from './AuthContext';

export const CartContext = createContext();

export const CartProvider = ({ children }) => {
  const { user } = useContext(AuthContext);
  const [cartItems, setCartItems] = useState([]);

  const fetchCart = async () => {
    if (user) {
      try {
        const res = await axios.get('/cart');
        setCartItems(res.data);
      } catch (err) {
        console.error('Failed to fetch cart', err);
      }
    } else {
      setCartItems([]);
    }
  };

  useEffect(() => {
    fetchCart();
  }, [user]);

  const addToCart = async (productId, quantity = 1) => {
    if (!user) {
      alert('Please login to reserve items.');
      return;
    }
    try {
      await axios.post('/cart/add', { productId, quantity });
      fetchCart();
    } catch (err) {
      console.error(err);
    }
  };

  const updateCartItem = async (productId, quantity) => {
    try {
      await axios.put('/cart/update', { productId, quantity });
      fetchCart();
    } catch (err) {
      console.error(err);
    }
  };

  const removeFromCart = async (productId) => {
    try {
      await axios.delete(`/cart/remove/${productId}`);
      fetchCart();
    } catch (err) {
      console.error(err);
    }
  };

  const clearCart = async () => {
    try {
      await axios.delete('/cart/clear');
      setCartItems([]);
    } catch (err) {
      console.error(err);
    }
  };

  const getSubtotal = () => {
    return cartItems.reduce((sum, item) => sum + (item.productPrice * item.quantity), 0);
  };

  return (
    <CartContext.Provider value={{ cartItems, addToCart, updateCartItem, removeFromCart, clearCart, getSubtotal, fetchCart }}>
      {children}
    </CartContext.Provider>
  );
};
