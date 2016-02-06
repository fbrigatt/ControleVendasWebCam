/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.venda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import controle.venda.factory.ConnectionFactory;
import controle.venda.model.Imagem;
import controle.venda.model.Produto;

/**
 *
 * @author frede
 */
public class ProdutoDao implements Dao<Produto> {

    private Connection connection;

    public ProdutoDao() {
        try {
            this.connection = ConnectionFactory.getInstance();
        } catch (Exception ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insert(Produto objeto) throws SQLException {
        PreparedStatement ps = this.connection.prepareStatement("insert into produto(codigoBarras,descricao,preco,id_imagem) values(?,?,?,?)");
        ps.setString(1, objeto.getCodigoBarras());
        ps.setString(2, objeto.getDescricao());
        ps.setFloat(3, objeto.getPreco());
        ps.setInt(4, objeto.getImagem().getId());
        ps.execute();
    }

    public void update(Produto objeto) throws SQLException {
        PreparedStatement ps = this.connection.prepareStatement("UPDATE produto SET descricao = ? , preco = ?, id_imagem = ? WHERE codigoBarras like ?");
        ps.setString(1, objeto.getDescricao());
        ps.setFloat(2, objeto.getPreco());
        ps.setInt(3,objeto.getImagem().getId());
        ps.setString(4, objeto.getCodigoBarras());
        
        ps.executeUpdate();
    }

    public void remove(Integer id) {
        throw new IllegalArgumentException("Método não implementado com parametro Integer!");
    }

    public void remove(String codigoBarras) throws SQLException {
        PreparedStatement ps = this.connection.prepareStatement("DELETE FROM produto WHERE codigoBarras like ?");
        ps.setString(1, codigoBarras);
        ps.executeUpdate();
    }

    public Produto selectOne(Integer id) throws SQLException {
        throw new SQLException("Metodo nao implementado");
    }
    
    public Produto selectOne(String codigoBarras) throws SQLException{
        PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM produto WHERE codigoBarras like ?");
        ps.setString(1, codigoBarras);
        ResultSet rs = ps.executeQuery();
        rs.beforeFirst(); // coloca o curso antes do primeiro registro

        if (rs.next()) {
            Produto produto = new Produto();
            produto.setCodigoBarras(rs.getString("codigoBarras"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setPreco(rs.getFloat("preco"));
            
            Imagem imagem = new Imagem();
            imagem.setId(rs.getInt("id_imagem"));
            produto.setImagem(imagem);

            return produto;
        }

        return null;
    }
    
    public ArrayList<Produto> selectAll() throws SQLException {
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM produto");
        ResultSet rs = ps.executeQuery();
        rs.beforeFirst(); // coloca o curso antes do primeiro registro

        while (rs.next()) {
            Produto produto = new Produto();
            produto.setCodigoBarras(rs.getString("codigoBarras"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setPreco(rs.getFloat("preco"));
            
            Imagem imagem = new Imagem();
            imagem.setId(rs.getInt("id_imagem"));
            produto.setImagem(imagem);

            produtos.add(produto);
        }

        return produtos;

    }
    
     public ArrayList<Produto> search(String pesquisa) throws SQLException {
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM produto WHERE codigoBarras like ? or descricao like ?");
        ps.setString(1,"%"+ pesquisa + "%");
        ps.setString(2, "%" + pesquisa + "%");
        ResultSet rs = ps.executeQuery();
        rs.beforeFirst(); // coloca o curso antes do primeiro registro

        while (rs.next()) {
            Produto produto = new Produto();
            produto.setCodigoBarras(rs.getString("codigoBarras"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setPreco(rs.getFloat("preco"));
            
            Imagem imagem = new Imagem();
            imagem.setId(rs.getInt("id_imagem"));
            produto.setImagem(imagem);

            produtos.add(produto);
        }

        return produtos;

    }
     
}
