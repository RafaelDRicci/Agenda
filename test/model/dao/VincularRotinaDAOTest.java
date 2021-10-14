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
 *Teste do DAO vincular rotina.
 * Foram testados todas as operações (CRUDE) em único teste para cada tipo de vinculação.
 * Os testes foram feitos dessa forma, pois as operações gravam dados em banco de dados, que ao final são excluídos.
 * Os testes de criação inserem dados no banco que outros testes utilizam, e devido que os testes não são feitos de forma ordenada, 
 * vários falhavam.
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
        //Leitura de um usuário e rotina
        final Usuario usuario1 = usuarioDAO.read(121);
        final Rotina rotina1 = rotinaDAO.read(2);
        //Criação de uma vinculação anual e gravando no banco
        final Anual vincular = new Anual(rotina1, usuario1);
        vincular.setDia(5);
        vincular.setMes(8);
        vincularDAO.create(vincular);
         //Leitura da vinculação gravada anteriormente
        final Anual anual = (Anual) vincularDAO.read(rotina1, usuario1);
        System.out.println(anual);
        //Atualização 
        anual.setDia(6);
        anual.setMes(6);
        int[] horarios = {7,8,9};
        anual.setHorarios(horarios);
        anual.setPrioritario(true);
        anual.setReagendavel(true);
        anual.setHorarioFixo(true);
        vincularDAO.update(anual);
        //Leitura da vinculação após a atualização
        final Anual anualAtt = (Anual) vincularDAO.read(rotina1, usuario1);
        System.out.println(anualAtt);
        //Apagar a vinculação do banco 
        vincularDAO.delete(anualAtt);

    }
    
    
    @Test
    public void testDataUnica() throws SQLException{
                
        System.out.println("*******************************************************************");
        System.out.println("                        Test DataUnica");

        final Usuario usuario2 = usuarioDAO.read(122);
        final Rotina rotina2 = rotinaDAO.read(3);
        
        final DataUnica vincular = new DataUnica(rotina2, usuario2);
        vincular.setData(new GregorianCalendar(2022, 11, 25));
        vincularDAO.create(vincular);

        final DataUnica dataUnica =  (DataUnica) vincularDAO.read(rotina2, usuario2);
        System.out.println(dataUnica);
        
        GregorianCalendar data = new GregorianCalendar(2022, 10, 25);
        dataUnica.setData(data);
        dataUnica.setPrioritario(true);
        dataUnica.setReagendavel(true);
        dataUnica.setHorarioFixo(true);
        final int[] horarios = {7,8,9};
        dataUnica.setHorarios(horarios);
        vincularDAO.update(dataUnica);
        
        final DataUnica dataUnicaAtt = (DataUnica) vincularDAO.read(rotina2, usuario2);
        System.out.println(dataUnicaAtt);
        
        vincularDAO.delete(dataUnicaAtt);

    }

    @Test
    public void testDatasAno() throws SQLException{
        
        System.out.println("*******************************************************************");
        System.out.println("                        Test DatasAno");
               
        Usuario usuario3 = usuarioDAO.read(129);
        Rotina rotina3 = rotinaDAO.read(4);

        final DatasAno vincular = new DatasAno(rotina3, usuario3);
        vincular.setDia(5);
        final int[] meses = {5, 6, 7};
        vincular.setMeses(meses);
        vincularDAO.create(vincular);
        
        final DatasAno datasAno = (DatasAno) vincularDAO.read(rotina3, usuario3);
        System.out.println(datasAno);
        
        datasAno.setDia(8);
        final int[] mesesAtt = {3, 9, 12};
        datasAno.setMeses(mesesAtt);
        datasAno.setPrioritario(true);
        datasAno.setReagendavel(true);
        datasAno.setHorarioFixo(true);
        final int[] horarios = {7,8,9};
        datasAno.setHorarios(horarios);
        vincularDAO.update(datasAno);
        
        final DatasAno dataAnoAtt = (DatasAno)vincularDAO.read(rotina3, usuario3);
        System.out.println(dataAnoAtt);
        
        vincularDAO.delete(dataAnoAtt);      
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

        diario.setPrioritario(true);
        diario.setReagendavel(true);
        diario.setHorarioFixo(true);
        final int[] horarios = {7,8,9};
        diario.setHorarios(horarios);
        vincularDAO.update(diario);
        
        final Diario diarioAtt = (Diario) vincularDAO.read(rotina4, usuario4);
        System.out.println(diarioAtt);  
        
        vincularDAO.delete(diarioAtt);
    }
    
   @Test
    public void testDiasMes() throws SQLException{
                  
        System.out.println("*******************************************************************");
        System.out.println("                        Test DiasMes");
        
        final Usuario usuario5 = usuarioDAO.read(125);
        final Rotina rotina5 = rotinaDAO.read(6);
       
        final DiasMes vincular = new DiasMes(rotina5, usuario5);
        final int[] dias = {1, 10, 20};
        vincular.setDias(dias);
        vincularDAO.create(vincular);
        
        final DiasMes diasMes = (DiasMes) vincularDAO.read(rotina5, usuario5);
        System.out.println(diasMes);
        
        final int[] diasAtt = {10, 20};
        diasMes.setDias(diasAtt);
        diasMes.setPrioritario(true);
        diasMes.setReagendavel(true);
        diasMes.setHorarioFixo(true);
        final int[] horarios = {7,8,9};
        diasMes.setHorarios(horarios);
        vincularDAO.update(diasMes);
        
        final DiasMes diasMesAtt = (DiasMes)vincularDAO.read(rotina5, usuario5);
        System.out.println(diasMesAtt);
        
        vincularDAO.delete(diasMesAtt);
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
        
        final int[] uteis = {5, 10};
        diasUteis.setDiasUteis(uteis);
        diasUteis.setPrioritario(true);
        diasUteis.setReagendavel(true);
        diasUteis.setHorarioFixo(true);
        final int[] horarios = {7,8,9};
        diasUteis.setHorarios(horarios);
        vincularDAO.update(diasUteis);
        
        final DiasUteis diasUteisAtt = (DiasUteis)vincularDAO.read(rotina6, usuario6);
        System.out.println(diasUteisAtt);
        vincularDAO.delete(diasUteisAtt);
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

        semanal.setDiasSemana(diasSemana);
        semanal.setPrioritario(true);
        semanal.setReagendavel(true);
        semanal.setHorarioFixo(true);
        final int[] horarios = {7,8,9};
        semanal.setHorarios(horarios);
        vincularDAO.update(semanal);
        
        final Semanal semanalAtt = (Semanal)vincularDAO.read(rotina7, usuario7);
        System.out.println(semanalAtt);
        vincularDAO.delete(semanalAtt);
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
