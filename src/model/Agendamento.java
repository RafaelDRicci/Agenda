/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import util.date.Data;

/**
 *
 * @author rafaeld
 */
public class Agendamento extends Data{
     
    private final int codAgendamento;
    private final VincularRotina vincularRotina;
    private int horaInicio;
    private int horaFinal;
    private String estado;
    private String descricao;
    private String justificativa;

    public Agendamento(int codAgendamento, VincularRotina vincularRotina, long millis) {
        super(vincularRotina.getRotina().getNome(), true, millis);
        this.codAgendamento = codAgendamento;
        this.vincularRotina = vincularRotina;
        horaInicio = vincularRotina.getHorarios()[0];
        horaFinal = vincularRotina.getHorarios()[vincularRotina.getHorarios().length-1];
        estado = "A FAZER";
        justificativa = estado+": ";
    }

    public int getCodAgendamento() {
        return codAgendamento;
    }

    public VincularRotina getVincularRotina() {
        return vincularRotina;
    }
     
    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(int horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public void setDescricao(){
        descricao = vincularRotina.getRotina()+" ("+vincularRotina.getRotina().getCodRotina()+ ")"
                +" VINCULADA PARA "+ vincularRotina.getUsuario()+ " ("+vincularRotina.getUsuario().getCodUsuario()+")"
                +" PERÍODO "+vincularRotina.getPeriodo()+ " AGENDADA PARA "+toString() +"DAS "+horaInicio+"h ATÉ "+horaFinal+"h";
    }
}
