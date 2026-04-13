import React, { useEffect, useState } from "react";
import api from "../api/api";
import ProductCard from "../components/ProductCard";

const Products = ({ updateCartCount }) => {
  const [products, setProducts] = useState([]);
  const [cartItems, setCartItems] = useState([]);
  const [search, setSearch] = useState("");
  const sessionId = "abc123";

  const fetchProducts = () => {
    api.get(`/products/search?name=${search}&inStock=true`)
      .then(res => setProducts(res.data))
      .catch(err => console.error(err));
  };

  const fetchCart = () => {
    api.get(`/cart?sessionId=${sessionId}`)
      .then(res => setCartItems(res.data.items))
      .catch(err => console.error(err));
  };

  useEffect(() => {
    fetchProducts();
    fetchCart();
  }, [search]);

  const addToCart = (productId) => {
    api.post(`/cart/add?sessionId=${sessionId}`, { productId, quantity: 1 })
      .then(res => {
        setCartItems(res.data.items);
        updateCartCount(res.data.items.length);
      })
      .catch(err => alert(err.response?.data?.error || "Error adding to cart"));
  };

  return (
    <div className="p-6">
      <input
        type="text"
        placeholder="Search products..."
        value={search}
        onChange={e => setSearch(e.target.value)}
        className="border p-2 rounded w-full md:w-1/3 mb-6"
      />
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        {products.map(p => (
          <ProductCard key={p.id} product={p} addToCart={addToCart} cartItems={cartItems} />
        ))}
      </div>
    </div>
  );
};

export default Products;