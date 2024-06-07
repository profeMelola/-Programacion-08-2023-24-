package es.daw.ordinaria.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import es.daw.ordinaria.modelBD.Pedido;


public class DaoPedido implements Dao<Pedido>{

    private Connection con = null;

    public DaoPedido() throws SQLException{
        con = DBConnection.getConnection();
    }

    @Override
    public Pedido select(int id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");

    }

    @Override
    public List<Pedido> selectAll() throws SQLException {

        System.out.println("OBTENIENDO PEDIDOS...........");

        try(PreparedStatement ps = con.prepareStatement("SELECT id,total,fecha,id_cliente FROM pedido")){
            
            ResultSet rs = ps.executeQuery();

            List<Pedido> pedidos = null;

            while (rs.next()) {
                if (pedidos == null) {
                    pedidos = new ArrayList<>();
                }

                Pedido p = new Pedido();
                p.setId(rs.getInt("id"));

                String dateString = rs.getString("fecha");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(dateString, formatter);                
                //p.setFecha(rs.getDate("fecha").toLocalDate());
                p.setFecha(localDate);

                p.setPrecio(rs.getDouble("total"));
                p.setIdCliente(rs.getInt("id_cliente"));

                pedidos.add(p);

            }

            return pedidos;

        }
        
    }

    @Override
    public void insert(Pedido t) throws SQLException {
        try(PreparedStatement ps = con.prepareStatement("INSERT INTO pedido "
                + "(total, fecha, id_cliente) "
                + "VALUES (?, ?, ?)")){
            
            ps.setDouble(1, t.getPrecio());
            ps.setDate(2, java.sql.Date.valueOf(t.getFecha()));
            ps.setInt(3, t.getIdCliente());
            
            ps.executeUpdate();
        }
    }

    @Override
    public void update(Pedido t) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Pedido t) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void delete(int id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
