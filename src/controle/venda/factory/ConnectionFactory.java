package controle.venda.factory;

import java.sql.Connection;

/***
 * Classe responsável por fornecer uma instância da conexão
 *
 * @author frede
 * 
 */
public class ConnectionFactory {
    
    private static Connection instance;
    
    public static Connection getInstance() throws Exception{
        
        if(instance == null){
           ConnectionDatabase cd = new ConnectionDatabase();
           instance = cd.getConnection();
        }
        return instance;
    }
}
