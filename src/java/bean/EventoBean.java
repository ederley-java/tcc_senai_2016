package bean;

import dao.EventoDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Evento;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@ManagedBean
@ViewScoped
public class EventoBean implements Serializable {

    private Evento evento;
    private ScheduleModel eventModel;
    private List<Evento> listaEvento;
    private EventoDAO eDao;

    private ScheduleEvent event = new DefaultScheduleEvent();

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public List<Evento> getListaEvento() {
        return listaEvento;
    }

    public void setListaEvento(List<Evento> listaEvento) {
        this.listaEvento = listaEvento;
    }

    public EventoDAO geteDao() {
        return eDao;
    }

    public void seteDao(EventoDAO eDao) {
        this.eDao = eDao;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public EventoBean() {
    }

    @PostConstruct
    public void init() {

        eDao = new EventoDAO();
        evento = new Evento();
        eventModel = new DefaultScheduleModel();

        try {
            listaEvento = eDao.todosEventos();
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro no sql."));
        }

        for (Evento ev : listaEvento) {
            DefaultScheduleEvent evt = new DefaultScheduleEvent();

            evt.setStartDate(ev.getInicio());
            evt.setEndDate(ev.getFim());
            evt.setTitle(ev.getTitulo());
            evt.setData(ev.getId());
            evt.setDescription(ev.getDescricao());
            evt.setAllDay(false);//marcar evento estendido
            evt.setEditable(true);

            if (ev.isStatus() == true) {
                evt.setStyleClass("emp1");
            } else if (ev.isStatus() == false) {
                evt.setStyleClass("emp2");
            }

            eventModel.addEvent(evt);

        }

    }

    public void quandoSelecionado(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();

        for (Evento ev : listaEvento) {
            if (Objects.equals(ev.getId(), event.getData())) {/* 'if' compara 'getId' c/ 'getData()', 'getData' recebe valor do
'selectEvent.getObject();' que atribui em 'event', e compara c/ 'getId' que recebe valor temporário da 'listaEvento'. 
Após verificação da condição 'if', resultando em igualdade é executado a atribuição de 'ev' em 'event' */
                evento = ev;
                break;//'Stop' após verificar a primeira condição, para não dar continuidade no laço de repetição!
            }
            System.out.println(evento.getDescricao());
        }
    }

    public void quandoNovo(SelectEvent selectEvent) {

        ScheduleEvent event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());

        evento = new Evento();
        evento.setInicio(new Timestamp(event.getStartDate().getTime()));
        evento.setFim(new Timestamp(event.getEndDate().getTime()));
        System.out.println(new Timestamp(event.getEndDate().getTime()));
        //evento.setInicio(new java.sql.Date(event.getStartDate().getTime()));
        //evento.setFim(new java.sql.Date(event.getEndDate().getTime()));

    }

    public void salvar() {
        if (evento.getId() == null) {
            if (evento.getInicio().getTime() <= evento.getFim().getTime()) {
                eDao = new EventoDAO();
                try {
                    eDao.salvar(evento);
                    init();
                } catch (Exception ex) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar evento", "Erro: " + ex.getMessage()));
                }
                evento = new Evento();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data inicial não pode ser maior que a inicial", ""));
            }
        } else {
            try {
                eDao.atualizar(evento);
                init();
            } catch (Exception ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar evento", "Erro: " + ex.getMessage()));
            }
        }
    }

    public void quandoMovido(ScheduleEntryMoveEvent eventoMovido) {
        for (Evento ev : listaEvento) {
            if (ev.getId() == (Long) eventoMovido.getScheduleEvent().getData()) {
                evento = ev;
                eDao = new EventoDAO();
                try {
                    eDao.atualizar(evento);
                    init();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar evento", "Erro: " + ex.getMessage()));
                }
                break;
            }
        }
    }

    public void quandoRedimencionar(ScheduleEntryMoveEvent eventoRedimencionar) {
        for (Evento ev : listaEvento) {
            if (ev.getId() == (Long) eventoRedimencionar.getScheduleEvent().getData()) {
                evento = ev;
                eDao = new EventoDAO();
                try {
                    eDao.atualizar(evento);
                    init();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar evento", "Erro: " + ex.getMessage()));
                }
                break;
            }
        }
    }

}
