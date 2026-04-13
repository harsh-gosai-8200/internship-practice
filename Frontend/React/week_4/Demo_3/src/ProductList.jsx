import ProductCard from "./ProductCard";

function ProductList() {
  const products = [
    { id: 1, name: "Headphones", price: 99, inStock: true },
    { id: 2, name: "Keyboard", price: 120, inStock: false },
    { id: 3, name: "Mouse", price: 45, inStock: true },
  ];

  return (
    <div style={{ display: "flex", gap: "20px" }}>
      {products.map((product) => (
        <ProductCard
          key={product.id}
          name={product.name}
          price={product.price}
          inStock={product.inStock}
        />
      ))}
    </div>
  );
}

export default ProductList;
