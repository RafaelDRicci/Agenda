/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import util.connection.database.ConnectionFactory;

/**
 *
 * @author rafaeld
 */
public abstract class GenericDAO<T> {
    
    protected Connection con;
    
    public GenericDAO(){
        con = ConnectionFactory.getConnection();
    }
    
    public void closeConnection() throws SQLException{
        con.close();
    }
    
    public abstract T read(int cod) throws SQLException, NoSuchElementException;
    
    public abstract void create(T object) throws SQLException;
    
    public abstract void update(T object) throws SQLException, NoSuchElementException;
    
    public abstract void delete(T object) throws SQLException, NoSuchElementException;
    
    public abstract List<T> listAll() throws SQLException, NoSuchElementException;
}
