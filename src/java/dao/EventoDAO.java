package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Evento;

public class EventoDAO {

    PreparedStatement pstm;
    ResultSet rs;
    private Connection conexao;

    public void salvar(Evento evento) {
        try {
            String sql = "INSERT INTO evento(titulo_evento, inicio_evento, fim_evento, status_evento, desc_evento) VALUES (?, ?, ?, ?, ?)";

            conexao = ConnectionManager.getConnection();
            pstm = conexao.prepareStatement(sql);

            pstm.setString(1, evento.getTitulo());
            pstm.setTimestamp(2, new Timestamp(evento.getInicio().getTime()));
            pstm.setTimestamp(3, new Timestamp(evento.getFim().getTime()));
            pstm.setBoolean(4, evento.isStatus());
            pstm.setString(5, evento.getDescricao());

            pstm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizar(Evento evento) {
        try {
            String sql = "UPDATE evento SET titulo_evento=?, inicio_evento=?, fim_evento=?, status_evento=?,"
                    + "desc_evento=? WHERE id_evento=?";

            conexao = ConnectionManager.getConnection();
            pstm = conexao.prepareStatement(sql);

            pstm.setString(1, evento.getTitulo());
            pstm.setTimestamp(2, new Timestamp(evento.getInicio().getTime()));
            pstm.setTimestamp(3, new Timestamp(evento.getFim().getTime()));
            pstm.setBoolean(4, evento.isStatus());
            pstm.setString(5, evento.getDescricao());
            pstm.setLong(6, evento.getId());

            pstm.executeUpdate();
            //pstm.setDate(2, new java.sql.Date(evento.getInicio().getTime()));
            //pstm.setDate(3, new java.sql.Date(evento.getFim().getTime()));

        } catch (SQLException ex) {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Evento> todosEventos() throws ParseException {
        List<Evento> eventos = new ArrayList<Evento>();
        try {

            String sql = "SELECT *  FROM evento";

            conexao = ConnectionManager.getConnection();
            pstm = conexao.prepareStatement(sql);

            rs = pstm.executeQuery();

            while (rs.next()) {
                Evento e = new Evento();
                e.setId(rs.getLong(1));
                e.setTitulo(rs.getString(2));
                e.setInicio(rs.getTimestamp(3));
                e.setFim(rs.getTimestamp(4));
                e.setStatus(rs.getBoolean(5));
                e.setDescricao(rs.getString(6));
                eventos.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eventos;
    }
}
