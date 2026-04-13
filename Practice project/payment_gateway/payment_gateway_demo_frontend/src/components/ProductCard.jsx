import React, { useState, useEffect } from "react";

const ProductCard = ({ product, addToCart, cartItems }) => {
  const [added, setAdded] = useState(false);

  useEffect(() => {
    // check if product already in cart
    if (cartItems.some(item => item.productId === product.id)) {
      setAdded(true);
    }
  }, [cartItems, product.id]);

  const handleAddToCart = () => {
    if (product.stock === 0) return;
    addToCart(product.id);
    setAdded(true);
  };

  return (
    <div className="bg-white rounded-lg shadow-md hover:shadow-xl transition transform hover:-translate-y-1 hover:scale-105 p-6 flex flex-col justify-between">
      <div>
        <h2 className="text-xl font-semibold text-gray-800">{product.name}</h2>
        <p className="text-gray-600 mt-2">{product.description}</p>
        <p className="text-blue-600 font-bold mt-3 text-lg">₹{product.price}</p>
        <p className="text-gray-500 mt-1">Stock: {product.stock}</p>
      </div>
      <button
        className={`mt-4 w-full py-2 rounded font-semibold text-white transition ${
          added
            ? "bg-green-500 hover:bg-green-600"
            : product.stock === 0
              ? "bg-gray-400 cursor-not-allowed"
              : "bg-blue-600 hover:bg-blue-700"
        }`}
        onClick={handleAddToCart}
        disabled={added || product.stock === 0}
      >
        {added ? "Added to Cart" : product.stock === 0 ? "Out of Stock" : "Add to Cart"}
      </button>
    </div>
  );
};

export default ProductCard;