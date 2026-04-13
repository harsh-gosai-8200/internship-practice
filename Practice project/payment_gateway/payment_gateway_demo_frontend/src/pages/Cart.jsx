import React, { useEffect, useState } from "react";
import api from "../api/api";
import CartItem from "../components/CartItem";

const Cart = ({ updateCartCount }) => {
  const [cart, setCart] = useState({ items: [] });
  const sessionId = "abc123";

  const fetchCart = () => {
    api.get(`/cart?sessionId=${sessionId}`)
      .then(res => setCart(res.data))
      .catch(err => console.error(err));
  };

  useEffect(() => { fetchCart(); }, []);

  const updateQuantity = (productId, quantity) => {
    api.put(`/cart/update?sessionId=${sessionId}&productId=${productId}&quantity=${quantity}`)
      .then(res => setCart(res.data))
      .catch(err => alert(err.response?.data?.error || "Error updating quantity"));
  };

  const removeItem = (productId) => {
    api.delete(`/cart/remove?sessionId=${sessionId}&productId=${productId}`)
      .then(res => {
        setCart(res.data);
        updateCartCount(res.data.items.length);
      })
      .catch(err => alert(err.response?.data?.error || "Error removing item"));
  };

  return (
    <div className="p-6 max-w-3xl mx-auto">
      <h1 className="text-2xl font-bold mb-4">Your Cart</h1>
      {cart.items.length === 0 ? (
        <p className="text-gray-600">Your cart is empty.</p>
      ) : (
        cart.items.map(item => (
          <CartItem key={item.productId} item={item} updateQuantity={updateQuantity} removeItem={removeItem} />
        ))
      )}
    </div>
  );
};

export default Cart;