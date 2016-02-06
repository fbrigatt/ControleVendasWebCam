/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.venda.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import controle.venda.factory.ConnectionFactory;
import controle.venda.model.Imagem;

/**
 *
 * @author frede
 */
public class ImagemDao implements Dao<Imagem> {

    private Connection connection;
    private ResultSet resultSet;

    public ImagemDao() {
        try {
            this.connection = ConnectionFactory.getInstance();
        } catch (Exception ex) {
            Logger.getLogger(ImagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insert(Imagem objeto) throws SQLException {
        PreparedStatement ps = this.connection.prepareStatement("INSERT INTO imagem(nome,source) VALUES(?,?)",
                Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, objeto.getNome());
        ps.setBinaryStream(2, objeto.getFile());
        ps.executeUpdate();
        this.resultSet = ps.getGeneratedKeys();
    }

    public int getIdLastInsert() throws SQLException {
        if (this.resultSet != null) {
            this.resultSet.beforeFirst();

            if (this.resultSet.next()) {
                return this.resultSet.getInt(1);
            }
        }
        return -1;
    }

    public void update(Imagem objeto) throws SQLException {
        PreparedStatement ps = this.connection.prepareStatement("UPDATE imagem SET nome = ?, source = ? WHERE id = ?");
        ps.setString(1, objeto.getNome());
        ps.setBinaryStream(2, objeto.getFile());
        ps.setInt(3, objeto.getId());
        ps.executeUpdate();
    }

    public void remove(Integer id) throws SQLException {
        PreparedStatement ps = this.connection.prepareStatement("DELETE FROM imagem WHERE id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public Imagem selectOne(Integer id) throws SQLException {
        PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM imagem WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Imagem imagem = new Imagem();
            imagem.setBlob(rs.getBlob("source"));
            imagem.setId(rs.getInt("id"));
            imagem.setNome(rs.getString("nome"));
            return imagem;
        }
        return null;
    }

    public ArrayList<Imagem> selectAll() throws SQLException {
        throw new SQLException("Método não implementado!");
    }

}
