
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    public void cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement prep = null;

        try {
            conn = ConectaDAO.connectDB();
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            prep.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto: " + e.getMessage());
        } finally {
            try {
                if (prep != null) {
                    prep.close();
                }
                if (conn != null) {
                    conn.close(); // Fechando a conexão após a operação
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        String sql = "SELECT * FROM produtos";
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet resultset = null;

        try {
            conn = ConectaDAO.connectDB();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                listagem.add(produto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar os produtos: " + e.getMessage());
        } finally {
            try {
                if (resultset != null) {
                    resultset.close();
                }
                if (prep != null) {
                    prep.close();
                }
                if (conn != null) {
                    conn.close(); // Fechando a conexão após a operação
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage());
            }
        }

        return listagem;
    }

    public void venderProduto(int id) {
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ? AND status = 'A Venda'";
        Connection conn = null;
        PreparedStatement prep = null;

        try {
            conn = ConectaDAO.connectDB();
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);

            int affectedRows = prep.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado ou já vendido.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender o produto: " + e.getMessage());
        } finally {
            try {
                if (prep != null) {
                    prep.close();
                }
                if (conn != null) {
                    conn.close(); // Fechando a conexão após a operação
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

    public ArrayList<ProdutosDTO> listarProdutosAVenda() {
        String sql = "SELECT * FROM produtos WHERE status = 'A Venda'";
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet resultset = null;

        try {
            conn = ConectaDAO.connectDB();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                listagem.add(produto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar os produtos: " + e.getMessage());
        } finally {
            try {
                if (resultset != null) {
                    resultset.close();
                }
                if (prep != null) {
                    prep.close();
                }
                if (conn != null) {
                    conn.close(); // Fechando a conexão após a operação
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage());
            }
        }

        return listagem;
    }

    public ProdutosDTO getProdutoPorNome(String nome) {
        String sql = "SELECT * FROM produtos WHERE nome = ?";
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet resultset = null;

        try {
            conn = ConectaDAO.connectDB();
            prep = conn.prepareStatement(sql);
            prep.setString(1, nome);
            resultset = prep.executeQuery();

            if (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                return produto;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o produto: " + e.getMessage());
        } finally {
            try {
                if (resultset != null) {
                    resultset.close();
                }
                if (prep != null) {
                    prep.close();
                }
                if (conn != null) {
                    conn.close(); // Fechando a conexão após a operação
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage());
            }
        }

        return null;
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet resultset = null;

        try {
            conn = ConectaDAO.connectDB();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                listagem.add(produto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar os produtos vendidos: " + e.getMessage());
        } finally {
            try {
                if (resultset != null) {
                    resultset.close();
                }
                if (prep != null) {
                    prep.close();
                }
                if (conn != null) {
                    conn.close(); // Fechando a conexão após a operação
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage());
            }
        }

        return listagem;
    }
}
