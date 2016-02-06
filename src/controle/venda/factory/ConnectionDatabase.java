package controle.venda.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/***
 * Classe responsável por fornecer uma conexão
 *
 * @author frede
 * 
 */


public class ConnectionDatabase {
    
    private Connection con;
    
public Connection getConnection() throws Exception{
     try {
            /* Tenta se conectar ao Driver */
            Class.forName("com.mysql.jdbc.Driver");
            /* nome do banco que voce deu anteriormente ao seu alias */
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_hildeberto","root","1234");
        } catch (ClassNotFoundException e) {//capturando os erros da conexão
            throw new Exception("Erro conectar ao banco de dados: " + e.getMessage());
        } catch (SQLException sqle) {
            throw new Exception("Erro conectar ao banco de dados: " + sqle.getMessage());
        }
        return con;
    }
    
}
