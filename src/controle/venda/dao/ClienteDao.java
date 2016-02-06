/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.venda.dao;

import controle.venda.factory.ConnectionFactory;
import controle.venda.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsável por inserir, alterar, remover e pesquisar um Cliente
 * 
 * @author frede
 * 
 */
public class ClienteDao implements Dao<Cliente>{
    
    private Connection connection;
    
    public ClienteDao(){
        try {
            this.connection = ConnectionFactory.getInstance();
        } catch(Exception ex){
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // INSERIR
    @Override
    public void insert(Cliente objeto) throws SQLException {
          String sql = "INSERT INTO clientes"
                     + "(cpf,"
                     + "nome)"
                     + "VALUES"
                     + "(?,?)";
        
        PreparedStatement ps = connection.prepareStatement(sql);
        
        ps.setString(1, objeto.getCpf());  // armazena a string cpf lido pelo getCpf 
        ps.setString(2, objeto.getNome()); // armazena a string nome lido pelo getNome
        ps.execute();
    }

    // ALTERAR
    @Override
    public void update(Cliente objeto) throws SQLException {
        String sql = "UPDATE clientes SET "
                     + "nome = ? "
                     + "WHERE cpf like ?";
        
        PreparedStatement ps = connection.prepareStatement(sql);
        
        ps.setString(1, objeto.getNome());
        ps.setString(2, objeto.getCpf());
        ps.execute();
    }

    // EXCLUIR
    @Override
    public void remove(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Método não implementado");
    }
    
    // EXCLUIR
    public void remove(String cpf) throws SQLException {
        String sql = "DELETE FROM clientes "
                     + "WHERE cpf like ?";
        
        PreparedStatement ps = connection.prepareStatement(sql);
        
        ps.setString(1, cpf);
        ps.executeUpdate();
    }

    // PESQUISAR UM CLIENTE PELO ID
    @Override
    public Cliente selectOne(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Método não implementado.");
    }
    
    // PESQUISAR UM CLIENTE PELO CPF
    public Cliente selectOne(String cpf) throws SQLException {
        String sql = "SELECT * FROM clientes "
                   + "WHERE cpf like ?";
        
        PreparedStatement ps = connection.prepareStatement(sql);
        
        ps.setString(1, cpf);
        
        ResultSet rs = ps.executeQuery();
        rs.beforeFirst(); // coloca o cursor antes do primeiro registro
        
        if(rs.next()){
            Cliente cliente = new Cliente();
            
            cliente.setCpf(rs.getString("cpf"));
            cliente.setNome(rs.getString("nome"));
            
            return cliente;
        }
        return null;
    }


    // PESQUISAR TODOS
    @Override
    public ArrayList<Cliente> selectAll() throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        
        String sql = "SELECT * FROM clientes ";
               //    + "WHERE cpf like ?";
        
        PreparedStatement ps = connection.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        rs.beforeFirst(); // coloca o cursor antes do primeiro registro
        
        while(rs.next()){
            Cliente cliente = new Cliente();
            cliente.setCpf(rs.getString("cpf"));
            cliente.setNome(rs.getString("nome"));
            clientes.add(cliente);
        }
        return clientes;
    }
    
    public ArrayList<Cliente> search(String pesquisa) throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM clientes WHERE cpf like ? or nome like ?");
        ps.setString(1,"%"+ pesquisa + "%");
        ps.setString(2, "%" + pesquisa + "%");
        ResultSet rs = ps.executeQuery();
        rs.beforeFirst(); // coloca o curso antes do primeiro registro

        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setCpf(rs.getString("cpf"));
            cliente.setNome(rs.getString("nome"));
            
           // Imagem imagem = new Imagem();
           // imagem.setId(rs.getInt("id_imagem"));
           // cliente.setImagem(imagem);

            clientes.add(cliente);
        }

        return clientes;

    }

    
}
