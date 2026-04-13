import React from "react";
import { Link } from "react-router-dom";

const Navbar = ({ cartCount }) => {
  return (
    <nav className="bg-blue-600 p-4 text-white flex justify-between items-center">
      <Link to="/" className="font-bold text-xl">My Shop</Link>
      <div className="flex space-x-4 items-center">
        <Link to="/seller" className="hover:underline">Seller Dashboard</Link>
        <Link to="/cart" className="relative">
          Cart
          {cartCount > 0 && (
            <span className="absolute -top-2 -right-3 bg-red-500 rounded-full px-2 text-sm">{cartCount}</span>
          )}
        </Link>
      </div>
    </nav>
  );
};

export default Navbar;