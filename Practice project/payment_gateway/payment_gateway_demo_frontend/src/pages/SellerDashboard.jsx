import React, { useState } from "react";
import api from "../api/api";

const SellerDashboard = () => {
  const [productRequest, setProductRequest] = useState({
    name: "",
    description: "",
    price: "",
    stock: ""
  });

  const handleAddProduct = () => {
    api.post("/products", {
      ...productRequest,
      price: Number(productRequest.price),
      stock: Number(productRequest.stock)
    })
      .then(res => alert("Product added!"))
      .catch(err => alert(err.response?.data?.error || "Error adding product"));
  };

  return (
    <div className="p-6 max-w-md mx-auto">
      <h1 className="text-2xl font-bold mb-4">Seller Dashboard</h1>
      {["name", "description", "price", "stock"].map(field => (
        <input
          key={field}
          type={field === "price" || field === "stock" ? "number" : "text"}
          placeholder={field.charAt(0).toUpperCase() + field.slice(1)}
          value={productRequest[field]}
          onChange={e => setProductRequest({...productRequest, [field]: e.target.value})}
          className="border p-2 rounded w-full mb-3"
        />
      ))}
      <button
        className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 w-full"
        onClick={handleAddProduct}
      >
        Add Product
      </button>
    </div>
  );
};

export default SellerDashboard;