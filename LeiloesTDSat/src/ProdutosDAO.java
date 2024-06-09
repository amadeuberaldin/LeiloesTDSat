
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
}
