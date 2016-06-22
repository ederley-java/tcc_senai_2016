/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import org.primefaces.model.ScheduleEvent;

/**
 *
 * @author Projetos_Web
 */
public class EventoScheduleEvent implements ScheduleEvent, Serializable{

    private Evento evento;

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
    @Override
    public String getId() {
       return evento.getId().toString();
    }

    @Override
    public void setId(String string) {
        //nada
    }

    @Override
    public Object getData() {
        return null;
    }

    @Override
    public String getTitle() {
        return evento.getTitulo();
    }

    @Override
    public Date getStartDate() {
        return evento.getInicio();
    }

    @Override
    public Date getEndDate() {
        return evento.getFim();
    }

    @Override
    public boolean isAllDay() {
        return false;
    }

    @Override
    public String getStyleClass() {
        return "";
    }

    @Override
    public boolean isEditable() {
        return false;
    }

    @Override
    public String getDescription() {
        return evento.getDescricao();
    }
    
}
