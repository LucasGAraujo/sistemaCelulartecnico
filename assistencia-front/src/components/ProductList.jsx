import { useEffect, useState } from "react";
import api from "../services/api";

function ProductList() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    api.get("/products")
      .then(response => {
        setProducts(response.data);
      })
      .catch(error => console.error(error));
  }, []);

  return (
    <div>
      <h2>Produtos</h2>
      <ul>
        {products.map(product => (
          <li key={product.id}>
            {product.nome} - {product.marca} - R$ {product.preco}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default ProductList;