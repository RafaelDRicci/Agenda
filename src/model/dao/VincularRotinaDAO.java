/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.VincularRotina;
import util.connection.database.ConnectionFactory;

/**
 *
 * @author rafaeld
 */
public abstract class VincularRotinaDAO {
    
    /*
    protected int codRotina;
    protected int codUsuario;
    protected boolean prioritario;
    protected boolean reagendavel;
    protected boolean horarioFixo;
    protected ArrayList<Integer> horarios;
    */
    
    protected Connection con;
    
    public VincularRotinaDAO(){
        con = ConnectionFactory.getConnection();
    }
    
    public void create(VincularRotina vincular){
        String sql = "Insert into AGENDA_VINCULARROTINA(CODROTINA, CODUSUARIO, "
                + "PRIORITARIO, REAGENDAVEL, HORARIOFIXO, DIAS, MESES, ANOS, "
                + "DIASSEMANA, DIASUTEIS, HORARIOS)";
        
        try {
        
            PreparedStatement stm = con.prepareStatement(sql);
            
        
        } catch (SQLException ex) {
            Logger.getLogger(VincularRotinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public abstract VincularRotina read(int codRotina, int codUsuario);
    
    public abstract void upadate();
    
    public abstract void delete(VincularRotina vincular);
    
    public abstract List<VincularRotina> list();
}
