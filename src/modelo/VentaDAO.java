package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    public int RegistrarVenta(Venta v){
        String sql = "INSERT INTO ventas (cliente, vendedor, total) VALUES(?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,v.getCliente());
            ps.setString(2,v.getVendedor());
            ps.setDouble(3,v.getTotal());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("eror");
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }        
        }
        return r;
    }
    public int RegistrarDetalle(Detalle Dv){
        String sql ="INSERT INTO detalleproduc (cod_producto,cantidad,precio,id_ventas) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,Dv.getCod_pro());
            ps.setInt(2,Dv.getCantidad());
            ps.setDouble(3,Dv.getPrecio());
            ps.setInt( 4,Dv.getId());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("reee");
            }        
        }
        return r;
    } 
    public int IdVenta(){
        int id = 0;
        String sql = "SELECT MAX(id) FROM ventas";
        try {
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }                        
        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;
    }
    public boolean ActualizarStoock (int cant, String cod){
        String sql = "UPDATE productos SET stock = ? WHERE codigo =? ";
        try {
            con =cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1,cant);
            ps.setString(2,cod);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        
    }
    public List ListarVentas(){
    List<Venta> ListarVenta= new ArrayList();
    String sql = "SELECT * FROM ventas";
        try {
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
           Venta vent = new Venta();
           vent.setId(rs.getInt("id"));
           vent.setCliente(rs.getString("cliente"));
           vent.setVendedor(rs.getString("vendedor"));
           vent.setTotal(rs.getDouble("total"));
           ListarVenta.add(vent);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ListarVenta;
    }
    
}
