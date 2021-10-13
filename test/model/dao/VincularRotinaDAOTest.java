/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.NoSuchElementException;
import model.Rotina;
import model.Usuario;
import model.VincularRotina;
import model.vincularrotina.Anual;
import model.vincularrotina.DataUnica;
import model.vincularrotina.DatasAno;
import model.vincularrotina.Diario;
import model.vincularrotina.DiasMes;
import model.vincularrotina.DiasUteis;
import model.vincularrotina.Semanal;

import org.junit.Test;

/**
 *
 * @author rafaeld
 */

public class VincularRotinaDAOTest {
    
    final UsuarioDAO usuarioDAO;
    final RotinaDAO rotinaDAO;
    final VincularRotinaDAO vincularDAO;
    
    public VincularRotinaDAOTest() {
        
        usuarioDAO = new UsuarioDAO();
        rotinaDAO = new RotinaDAO();
        vincularDAO = new VincularRotinaDAO();
        
    }

    @Test
    public void testCloseConnection() {
    }

    @Test(expected = SQLException.class)
    public void testCreateVinculacaoExistente() throws SQLException{
        
        final Usuario usuario1 = usuarioDAO.read(126);
        final Rotina rotina1 = rotinaDAO.read(2);
        
        final Anual anual = new Anual(rotina1, usuario1);
        anual.setDia(5);
        anual.setMes(8);
        vincularDAO.create(anual);
        vincularDAO.create(anual);
    }
    
    @Test
    public void testAnual() throws SQLException{
        
        System.out.println("*******************************************************************");
        System.out.println("                        Test Anual");
        
        final Usuario usuario1 = usuarioDAO.read(121);
        final Rotina rotina1 = rotinaDAO.read(2);
        
        final Anual vincular = new Anual(rotina1, usuario1);
        vincular.setDia(5);
        vincular.setMes(8);
        vincularDAO.create(vincular);
         
        final Anual anual = (Anual) vincularDAO.read(rotina1, usuario1);
        System.out.println(anual);
        
        anual.setDia(6);
        anual.setMes(6);
        int[] horarios = {7,8,9};
        anual.setHorarios(horarios);
        anual.setPrioritario(true);
        anual.setReagendavel(true);
        anual.setHorarioFixo(true);
        vincularDAO.update(anual);
        
        final Anual anualAtt = (Anual) vincularDAO.read(rotina1, usuario1);
        System.out.println(anualAtt);
        
        vincularDAO.delete(anualAtt);

    }
    
    @Test
    public void testDataUnica() throws SQLException{
                
        System.out.println("*******************************************************************");
        System.out.println("                        Test Read DataUnica");

        final Usuario usuario2 = usuarioDAO.read(122);
        final Rotina rotina2 = rotinaDAO.read(3);
        
        final DataUnica vincular = new DataUnica(rotina2, usuario2);
        vincular.setData(new GregorianCalendar(2022, 11, 25));
        vincularDAO.create(vincular);
        
        DataUnica dataUnica =  (DataUnica) vincularDAO.read(rotina2, usuario2);
        System.out.println(dataUnica);
        vincularDAO.delete(vincular);

    }

    @Test
    public void testDatasAno() throws SQLException{
        
        System.out.println("*******************************************************************");
        System.out.println("                        Test DatasAno");
               
        Usuario usuario3 = usuarioDAO.read(129);
        Rotina rotina3 = rotinaDAO.read(4);

        final DatasAno vincular = new DatasAno(rotina3, usuario3);
        vincular.setDia(5);
        int[] meses = {5, 6, 7};
        vincular.setMeses(meses);
        vincularDAO.create(vincular);
        
        DatasAno datasAno = (DatasAno) vincularDAO.read(rotina3, usuario3);
        System.out.println(datasAno);
        vincularDAO.delete(vincular);
    }
    
    @Test
    public void testDiario() throws SQLException{
        
        System.out.println("*******************************************************************");
        System.out.println("                        Test Diario");
        
        Usuario usuario4 = usuarioDAO.read(130);
        Rotina rotina4 = rotinaDAO.read(5);
        
        final Diario vincular = new Diario(rotina4, usuario4);
        vincularDAO.create(vincular);
        
        Diario diario = (Diario) vincularDAO.read(rotina4, usuario4);

        System.out.println(diario);  
        vincularDAO.delete(vincular);
    }
    
   @Test
    public void testDiasMes() throws SQLException{
        
                
        System.out.println("*******************************************************************");
        System.out.println("                        Test DiasMes");
        
        final Usuario usuario5 = usuarioDAO.read(125);
        final Rotina rotina5 = rotinaDAO.read(6);
       
        final DiasMes vincular = new DiasMes(rotina5, usuario5);
        int[] dias = {1, 10, 20};
        vincular.setDias(dias);
        vincularDAO.create(vincular);
        
        final DiasMes diaMes = (DiasMes) vincularDAO.read(rotina5, usuario5);
        System.out.println(diaMes);
        
        vincularDAO.delete(vincular);
    }
    
    @Test
    public void testDiasUteis() throws SQLException{
        
        System.out.println("*******************************************************************");
        System.out.println("                        Test DiasUteis");
       
        Usuario usuario6 = usuarioDAO.read(126);
        Rotina rotina6 = rotinaDAO.read(7);
        
        int[] dias = {1, 10, 20};
        DiasUteis vincular = new DiasUteis(rotina6, usuario6);
        vincular.setDiasUteis(dias);
        vincularDAO.create(vincular);
        
        DiasUteis diasUteis = (DiasUteis) vincularDAO.read(rotina6, usuario6);

        System.out.println(diasUteis);
        vincularDAO.delete(vincular);
    }
    
    
    
    @Test
    public void testSemanal() throws SQLException{
        
        System.out.println("*******************************************************************");
        System.out.println("                        Test Semanal");
       
        Usuario usuario7 = usuarioDAO.read(132);
        Rotina rotina7 = rotinaDAO.read(8);
        
        final Semanal vincular = new Semanal(rotina7, usuario7);
        int[] diasSemana = {5};
        vincular.setDiasSemana(diasSemana);
        vincularDAO.create(vincular);
        
        final Semanal semanal = (Semanal) vincularDAO.read(rotina7, usuario7);

        System.out.println(semanal);
        vincularDAO.delete(vincular);
    }

    @Test(expected = NoSuchElementException.class)
    public void testReadVinculacaoNaoExistente() throws SQLException{
        
        Usuario usuario8 = usuarioDAO.read(132);
        Rotina rotina8 = rotinaDAO.read(2);
        VincularRotina vincularRotina = vincularDAO.read(rotina8, usuario8);
        System.out.println(vincularRotina);
    }
    

    @Test
    public void testListAll() throws SQLException {

       System.out.println("*******************************************************************");
       System.out.println("                        Test ListAll");
        
       List<VincularRotina> rotinasVinculadas = vincularDAO.listAll();
        
       rotinasVinculadas.forEach(rotinaVinculada -> {
           System.out.println(rotinaVinculada);
       });
    }

    
}
