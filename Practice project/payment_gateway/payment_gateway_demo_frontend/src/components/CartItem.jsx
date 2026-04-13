import React, { useState } from "react";

const CartItem = ({ item, updateQuantity, removeItem }) => {
  const [qty, setQty] = useState(item.quantity);

  const handleUpdate = () => {
    if (qty > item.productStock) {
      alert(`Cannot exceed available stock: ${item.productStock}`);
      setQty(item.productStock);
      return;
    }
    if (qty < 1) {
      alert("Quantity must be at least 1");
      setQty(1);
      return;
    }
    updateQuantity(item.productId, qty);
  };

  return (
    <div className="flex justify-between items-center border-b py-3">
      <div>
        <h3 className="font-bold">{item.productName}</h3>
        <p className="text-gray-600">₹{item.productPrice}</p>
        <p className="text-gray-500">Stock: {item.productStock}</p>
      </div>
      <div className="flex items-center space-x-2">
        <input
          type="number"
          min="1"
          max={item.productStock}
          className="border w-16 text-center rounded"
          value={qty}
          onChange={e => setQty(Number(e.target.value))}
        />
        <button className="bg-green-600 text-white px-2 py-1 rounded" onClick={handleUpdate}>
          Update
        </button>
        <button className="bg-red-600 text-white px-2 py-1 rounded" onClick={() => removeItem(item.productId)}>
          Remove
        </button>
      </div>
    </div>
  );
};

export default CartItem;