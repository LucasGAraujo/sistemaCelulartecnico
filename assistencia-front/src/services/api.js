const BASE_URL = 'http://localhost:8080/api';

export const api = {
  orders: {
    listar: async () => (await fetch(`${BASE_URL}/orders`)).json(),
    criar: async (productId, customerId, descricao, valor) => {
      const params = new URLSearchParams({ productId, customerId, descricao, valor });
      const res = await fetch(`${BASE_URL}/orders?${params.toString()}`, { method: 'POST' });
      if (!res.ok) throw new Error('Erro ao criar ordem');
      return res.json();
    },
    finalizar: async (id) => {
      const res = await fetch(`${BASE_URL}/orders/${id}/finalizar`, { method: 'PATCH' });
      if (!res.ok) throw new Error('Erro ao finalizar ordem');
      return res.json();
    },
    deletar: async (id) => {
      const res = await fetch(`${BASE_URL}/orders/${id}`, { method: 'DELETE' });
      if (!res.ok) throw new Error('Erro ao deletar ordem');
    }
  },

  customers: {
    listar: async () => (await fetch(`${BASE_URL}/customers`)).json(),
    salvar: async (customer) => {
      const res = await fetch(`${BASE_URL}/customers`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(customer)
      });
      if (!res.ok) throw new Error('Erro ao salvar cliente');
      return res.json();
    },
    deletar: async (id) => {
      const res = await fetch(`${BASE_URL}/customers/${id}`, { method: 'DELETE' });
      if (!res.ok) throw new Error('Erro ao deletar cliente');
    }
  },

  products: {
    listar: async () => (await fetch(`${BASE_URL}/products`)).json(),
    salvar: async (customerId, nome, marca, preco) => {
      const params = new URLSearchParams({ customerId, nome, marca, preco });
      const res = await fetch(`${BASE_URL}/products?${params.toString()}`, { method: 'POST' });
      if (!res.ok) throw new Error('Erro ao salvar aparelho');
      return res.json();
    },
    deletar: async (id) => {
      const res = await fetch(`${BASE_URL}/products/${id}`, { method: 'DELETE' });
      if (!res.ok) throw new Error('Erro ao deletar produto');
    }
  }
};