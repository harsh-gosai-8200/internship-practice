import React, { useState } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Products from "./pages/Products";
import Cart from "./pages/Cart";
import SellerDashboard from "./pages/SellerDashboard";

function App() {
  const [cartCount, setCartCount] = useState(0);

  return (
    <Router>
      <Navbar cartCount={cartCount} />
      <Routes>
        <Route path="/" element={<Products updateCartCount={setCartCount} />} />
        <Route path="/cart" element={<Cart updateCartCount={setCartCount} />} />
        <Route path="/seller" element={<SellerDashboard />} />
      </Routes>
    </Router>
  );
}

export default App;