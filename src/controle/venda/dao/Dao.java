/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.venda.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author frede
 */
public interface Dao<T> {

    public void insert(T objeto) throws SQLException;

    public void update(T objeto) throws SQLException;

    public void remove(Integer id) throws SQLException;

    public T selectOne(Integer id) throws SQLException;

    public ArrayList<T> selectAll() throws SQLException;

}
