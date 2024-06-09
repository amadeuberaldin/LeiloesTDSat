import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConectaDAO {
    private static final Logger logger = Logger.getLogger(ConectaDAO.class.getName());
    private static Connection conn = null;
    
    private ConectaDAO() {
        // Construtor privado para evitar instância
    }

    public static Connection connectDB() throws SQLException {
        if (conn == null) {
            try (InputStream input = ConectaDAO.class.getClassLoader().getResourceAsStream("config.properties")) {
                Properties prop = new Properties();
                if (input == null) {
                    logger.log(Level.SEVERE, "Desculpe, não foi possível encontrar config.properties");
                    return null;
                }
                // Carregar o arquivo de propriedades
                prop.load(input);

                String url = prop.getProperty("db.url");
                String user = prop.getProperty("db.user");
                String password = prop.getProperty("db.password");

                conn = DriverManager.getConnection(url, user, password);
                logger.log(Level.INFO, "Conexão com o banco de dados estabelecida com sucesso.");
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Erro ao conectar ao banco de dados: {0}", e.getMessage());
                throw new SQLException("Erro ao conectar ao banco de dados", e);
            }
        }
        return conn;
    }
}
