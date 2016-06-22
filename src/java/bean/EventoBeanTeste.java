package bean;

import dao.EventoDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.security.auth.Subject;
import modelo.Evento;
import org.primefaces.component.row.Row;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@ManagedBean
@ViewScoped
public class EventoBeanTeste implements Serializable {

    private static Long idSchedule;
    private static final long serialVersionUID = 8458259708861027697L;
    //private ScheduleEvent event = new DefaultScheduleEvent();

    private Evento evento;
    private ScheduleModel eventModel;
    private List<Evento> listaEvento;
    private EventoDAO eDao;

    public static Long getIdSchedule() {
        return idSchedule;
    }

    public static void setIdSchedule(Long idSchedule) {
        EventoBeanTeste.idSchedule = idSchedule;
    }

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

    @PostConstruct
    public void inicializar() {

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

            evt.setEndDate(ev.getFim());
            evt.setStartDate(ev.getInicio());
            evt.setTitle(ev.getTitulo());
            evt.setData((Object)ev);
            evt.setDescription(ev.getDescricao());
            evt.setAllDay(true);//marcar evento estendido
            evt.setEditable(true);

            eventModel.addEvent(evt);
            //System.out.println(eventModel.getEvents());

        }

    }

    public void quandoSelecionado(SelectEvent selectevent) {
        ScheduleEvent event = (ScheduleEvent) selectevent.getObject();

        Evento evento = (Evento) event.getData();
        System.out.println(evento.getDescricao());
//        for (Evento ev : listaEvento) {
//            if (ev.getId() == (Evento) event.getData()) {
//                evento = ev;
//                break;
//                //(Long) event.getData()
//            }
//        }
    }

}
