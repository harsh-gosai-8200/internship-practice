function ProductCard({ name, price, inStock }) {
  return (
    <div
      style={{
        border: "1px solid gray",
        padding: "15px",
        width: "200px",
        borderRadius: "8px",
      }}
    >
      <h3>{name}</h3>
      <p>Price: ${price}</p>

      {inStock ? (
        <p style={{ color: "green" }}>In Stock</p>
      ) : (
        <p style={{ color: "red" }}>Out of Stock</p>
      )}
    </div>
  );
}

export default ProductCard;
