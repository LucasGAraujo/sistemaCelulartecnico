import { useEffect, useState } from 'react';
import { api } from '../src/services/api.js';

export default function App() {
  const [abaAtiva, setAbaAtiva] = useState('ordens');
  const [customers, setCustomers] = useState([]);
  const [products, setProducts] = useState([]);
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(false);

  const [nomeCliente, setNomeCliente] = useState('');
  const [emailCliente, setEmailCliente] = useState('');
  const [telefoneCliente, setTelefoneCliente] = useState('');

  const [produtoOwnerId, setProdutoOwnerId] = useState('');
  const [nomeProduto, setNomeProduto] = useState('');
  const [marcaProduto, setMarcaProduto] = useState('');
  const [precoProduto, setPrecoProduto] = useState('');

  const [selectedCustomerId, setSelectedCustomerId] = useState('');
  const [selectedProductId, setSelectedProductId] = useState('');
  const [descricaoOrdem, setDescricaoOrdem] = useState('');
  const [valorOrdem, setValorOrdem] = useState('');

  useEffect(() => {
    carregarDados();
  }, [abaAtiva]);

  const carregarDados = async () => {
    setLoading(true);
    try {
      if (abaAtiva === 'clientes' || abaAtiva === 'ordens') setCustomers(await api.customers.listar());
      if (abaAtiva === 'produtos' || abaAtiva === 'ordens') setProducts(await api.products.listar());
      if (abaAtiva === 'ordens') setOrders(await api.orders.listar());
    } catch (error) {
      console.error("Erro ao carregar dados:", error);
    } finally {
      setLoading(false);
    }
  };

  // ==========================================
  // 🔥 HANDLERS: CLIENTES
  // ==========================================
  const handleCriarCliente = async (e) => {
    e.preventDefault();
    try {
      await api.customers.salvar({ nome: nomeCliente, email: emailCliente, telefone: telefoneCliente });
      setNomeCliente(''); setEmailCliente(''); setTelefoneCliente('');
      carregarDados();
      alert('Cliente salvo com sucesso!');
    } catch (error) {
      alert('Erro ao salvar cliente.');
    }
  };

  const handleDeletarCliente = async (id) => {
    if (window.confirm('Tem certeza? Se deletar o cliente, seus aparelhos e ordens serão apagados juntos!')) {
      try {
        await api.customers.deletar(id);
        carregarDados();
      } catch (error) {
        alert('Erro ao deletar cliente.');
      }
    }
  };

  // ==========================================
  // 🔥 HANDLERS: PRODUTOS (APARELHOS)
  // ==========================================
  const handleCriarProduto = async (e) => {
    e.preventDefault();
    try {
      await api.products.salvar(produtoOwnerId, nomeProduto, marcaProduto, parseFloat(precoProduto));
      setNomeProduto(''); setMarcaProduto(''); setPrecoProduto(''); setProdutoOwnerId('');
      carregarDados();
      alert('Aparelho vinculado ao cliente com sucesso!');
    } catch (error) {
      alert('Erro ao salvar aparelho. Você selecionou o dono?');
    }
  };

  const handleDeletarProduto = async (id) => {
    if (window.confirm('Tem certeza? Se deletar o aparelho, as ordens dele serão apagadas!')) {
      try {
        await api.products.deletar(id);
        carregarDados();
      } catch (error) {
        alert('Erro ao deletar aparelho.');
      }
    }
  };

  // ==========================================
  // 🔥 HANDLERS: ORDENS
  // ==========================================
  const handleCriarOrdem = async (e) => {
    e.preventDefault();
    try {
      await api.orders.criar(selectedProductId, selectedCustomerId, descricaoOrdem, parseFloat(valorOrdem));
      setSelectedCustomerId(''); setSelectedProductId(''); setDescricaoOrdem(''); setValorOrdem('');
      carregarDados();
      alert('Ordem criada com sucesso!');
    } catch (error) {
      alert('Erro ao criar ordem. Verifique se o aparelho realmente pertence a este cliente.');
    }
  };

  const handleFinalizarOrdem = async (id) => {
    try {
      await api.orders.finalizar(id);
      carregarDados();
    } catch (error) {
      alert('Erro ao finalizar a ordem.');
    }
  };

  const handleDeletarOrdem = async (id) => {
    if (window.confirm('Tem certeza que deseja deletar esta ordem?')) {
      try {
        await api.orders.deletar(id);
        carregarDados();
      } catch (error) {
        alert('Erro ao deletar a ordem.');
      }
    }
  };

  // ==========================================
  // 🎨 COMPONENTES DE TELA
  // ==========================================

  const renderClientes = () => (
    <section>
      <h2>Gerenciar Clientes</h2>
      <form onSubmit={handleCriarCliente} style={styles.form}>
        <input type="text" placeholder="Nome do Cliente" value={nomeCliente} onChange={e => setNomeCliente(e.target.value)} required style={styles.input} />
        <input type="email" placeholder="E-mail" value={emailCliente} onChange={e => setEmailCliente(e.target.value)} required style={styles.input} />
        <input type="text" placeholder="Telefone" value={telefoneCliente} onChange={e => setTelefoneCliente(e.target.value)} required style={styles.input} />
        <button type="submit" style={styles.btnPrimary}>Cadastrar Cliente</button>
      </form>

      <h3>Lista de Clientes</h3>
      <ul style={styles.list}>
        {customers.map(c => (
          <li key={c.id} style={{ ...styles.card, display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
            <span><strong>{c.nome}</strong> - {c.email} ({c.telefone})</span>
            <button onClick={() => handleDeletarCliente(c.id)} style={styles.btnDanger}>🗑️ Deletar</button>
          </li>
        ))}
      </ul>
    </section>
  );

  const renderProdutos = () => (
    <section>
      <h2>Cadastrar Aparelho do Cliente</h2>
      <form onSubmit={handleCriarProduto} style={styles.form}>
        <select value={produtoOwnerId} onChange={e => setProdutoOwnerId(e.target.value)} required style={styles.input}>
          <option value="" disabled>1. Selecione o Dono do Aparelho...</option>
          {customers.map(c => <option key={c.id} value={c.id}>{c.nome}</option>)}
        </select>
        <input type="text" placeholder="Aparelho (Ex: iPhone 13)" value={nomeProduto} onChange={e => setNomeProduto(e.target.value)} required style={styles.input} />
        <input type="text" placeholder="Marca (Ex: Apple)" value={marcaProduto} onChange={e => setMarcaProduto(e.target.value)} required style={styles.input} />
        <input type="number" step="0.01" placeholder="Valor Estimado/Peça (R$)" value={precoProduto} onChange={e => setPrecoProduto(e.target.value)} required style={styles.input} />
        <button type="submit" style={styles.btnPrimary}>Vincular Aparelho</button>
      </form>

      <h3>Aparelhos Cadastrados</h3>
      <ul style={styles.list}>
        {products.map(p => {
          const dono = customers.find(c => c.id === p.customerId)?.nome || 'Desconhecido';
          return (
            <li key={p.id} style={{ ...styles.card, display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
              <span><strong>{p.nome}</strong> ({p.marca}) <br/><small style={{color: '#666'}}>Dono: {dono}</small></span>
              <button onClick={() => handleDeletarProduto(p.id)} style={styles.btnDanger}>🗑️ Deletar</button>
            </li>
          );
        })}
      </ul>
    </section>
  );

  const renderOrdens = () => {
    // 🔥 FILTRO MÁGICO: Filtra os aparelhos com base no cliente selecionado
    const aparelhosDoCliente = products.filter(p => p.customerId === Number(selectedCustomerId));

    return (
      <section>
        <h2>Nova Ordem de Serviço</h2>
        <form onSubmit={handleCriarOrdem} style={styles.form}>

          <select value={selectedCustomerId} onChange={e => {
            setSelectedCustomerId(e.target.value);
            setSelectedProductId(''); // Reseta o aparelho ao trocar de cliente
          }} required style={styles.input}>
            <option value="" disabled>1. Selecione o Cliente...</option>
            {customers.map(c => <option key={c.id} value={c.id}>{c.nome}</option>)}
          </select>

          <select value={selectedProductId} onChange={e => setSelectedProductId(e.target.value)} required disabled={!selectedCustomerId} style={styles.input}>
            <option value="" disabled>2. Selecione o Aparelho do Cliente...</option>
            {aparelhosDoCliente.length === 0 && selectedCustomerId ? (
               <option value="" disabled>Este cliente não possui aparelhos cadastrados</option>
            ) : (
               aparelhosDoCliente.map(p => <option key={p.id} value={p.id}>{p.nome}</option>)
            )}
          </select>

          <input type="text" placeholder="O que precisa ser feito? (Descrição)" value={descricaoOrdem} onChange={e => setDescricaoOrdem(e.target.value)} required style={styles.input} />
          <input type="number" step="0.01" placeholder="Valor Cobrado (R$)" value={valorOrdem} onChange={e => setValorOrdem(e.target.value)} required style={styles.input} />
          <button type="submit" style={styles.btnPrimary}>Abrir Ordem de Serviço</button>
        </form>

        <h3>Ordens em Andamento</h3>
        <ul style={styles.list}>
          {orders.map(o => (
            <li key={o.id} style={styles.card}>
              <h4 style={{ margin: '0 0 0.5rem 0' }}>Ordem #{o.id} - {o.descricaoServico}</h4>
              <p style={{ margin: '0.2rem 0' }}><strong>Cliente:</strong> {o.nomeCliente}</p>
              <p style={{ margin: '0.2rem 0' }}><strong>Aparelho:</strong> {o.nomeProduto}</p>
              <p style={{ margin: '0.2rem 0' }}><strong>Valor:</strong> R$ {o.valorServico?.toFixed(2)}</p>
              <p style={{ margin: '0.2rem 0' }}><strong>Status:</strong> {o.finalizada ? '✅ Finalizada' : '⏳ Pendente'}</p>

              <div style={{ marginTop: '1rem', display: 'flex', gap: '0.5rem' }}>
                {!o.finalizada && (
                  <button onClick={() => handleFinalizarOrdem(o.id)} style={styles.btnSuccess}>✅ Finalizar</button>
                )}
                <button onClick={() => handleDeletarOrdem(o.id)} style={styles.btnDanger}>🗑️ Deletar</button>
              </div>
            </li>
          ))}
        </ul>
      </section>
    );
  };

  return (
    <main style={{ padding: '2rem', maxWidth: '800px', margin: '0 auto', fontFamily: 'system-ui' }}>
      <h1 style={{ textAlign: 'center' }}>CellStore OS</h1>

      <nav style={{ display: 'flex', gap: '1rem', marginBottom: '2rem', borderBottom: '2px solid #eee', paddingBottom: '1rem' }}>
        <button onClick={() => setAbaAtiva('clientes')} style={abaAtiva === 'clientes' ? styles.tabActive : styles.tab}>👥 Clientes</button>
        <button onClick={() => setAbaAtiva('produtos')} style={abaAtiva === 'produtos' ? styles.tabActive : styles.tab}>📱 Aparelhos</button>
        <button onClick={() => setAbaAtiva('ordens')} style={abaAtiva === 'ordens' ? styles.tabActive : styles.tab}>🛠️ Ordens de Serviço</button>
      </nav>

      {loading && <p>Carregando dados do servidor...</p>}
      {!loading && abaAtiva === 'clientes' && renderClientes()}
      {!loading && abaAtiva === 'produtos' && renderProdutos()}
      {!loading && abaAtiva === 'ordens' && renderOrdens()}
    </main>
  );
}

const styles = {
  form: { display: 'flex', flexDirection: 'column', gap: '1rem', background: '#f8f9fa', padding: '1.5rem', borderRadius: '8px', marginBottom: '2rem' },
  input: { padding: '0.6rem', border: '1px solid #ccc', borderRadius: '4px', fontSize: '1rem' },
  btnPrimary: { padding: '0.8rem', background: '#0d6efd', color: '#fff', border: 'none', borderRadius: '4px', cursor: 'pointer', fontWeight: 'bold', fontSize: '1rem' },
  btnSuccess: { padding: '0.5rem 1rem', background: '#198754', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' },
  btnDanger: { padding: '0.5rem 1rem', background: '#dc3545', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' },
  tab: { padding: '0.6rem 1.2rem', border: 'none', background: 'transparent', cursor: 'pointer', fontSize: '1rem', borderRadius: '4px' },
  tabActive: { padding: '0.6rem 1.2rem', border: 'none', background: '#0d6efd', color: 'white', borderRadius: '4px', cursor: 'pointer', fontSize: '1rem', fontWeight: 'bold' },
  list: { listStyle: 'none', padding: 0 },
  card: { border: '1px solid #dee2e6', padding: '1.2rem', marginBottom: '1rem', borderRadius: '6px', background: '#fff' }
};