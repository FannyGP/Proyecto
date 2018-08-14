/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import Conexion.Conexion;
import com.sun.istack.internal.Pool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Daphne
 */
public class Producto {

    private String codigo;
    private String nombre;
    private String descripcion;
    private int cveCategoria;
    private int cantidad;
    private double precioC;
    private double precioV;
    private int cveProveedor;

    private ResultSet rs;
    private String consulta;
    Statement st;

    Conexion conn = new Conexion();
    Connection c = null;

    public Producto() { //constructor
        try {

            c = Conexion.ConectarBD(); //se tiene la conexion
            st = c.createStatement();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Aviso", "Conexion nula", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int guardarProducto() {
        int res = 0;
        try {

            /*consulta= "INSERT INTO producto (codigo, nombre, descripcion, cveCategoria,"
           + "cantidad, precioCompra, precioVenta, cveProveedor ) ";
           
           if(conn.siExiste(consulta)){*/
            consulta = "SELECT*FROM producto where codigo=" + getCodigo();
            rs = st.executeQuery(consulta);

            if (rs.next()) {
                consulta = "UPDATE producto set nombre='" + getNombre() + "', descripcion='" + getDescripcion()
                        + "',cveCategoria=" + getCveCategoria() + ", cantidad=" + getCantidad() + ", precioCompra="
                        + getPrecioC() + ", precioVenta=" + getPrecioV() + ", cveProveedor=" + getCveProveedor()
                        + " where codigo=" + getCodigo();

                res = 2;

            } else {
                consulta = "INSERT INTO producto ( nombre, descripcion, cveCategoria, "
                        + "cantidad, precioCompra, precioVenta, cveProveedor ) "
                        + "VALUES('"
                        //+ getCodigo() + ",'"
                        + getNombre() + "','"
                        + getDescripcion() + "',"
                        + getCveCategoria() + ","
                        + getCantidad() + ","
                        + getPrecioC() + ","
                        + getPrecioV() + ","
                        + getCveProveedor() + ")";

                res = 1;
            }
             st.executeUpdate(consulta);

            /* }
           return res; */
 /*else{
           consulta= "UPDATE producto SET (codigo, nombre, descripcion, cveCategoria, "
           + "cantidad, precioCompra, precioVenta, cveProveedor ) "
           +"VALUES('"
           + getCodigo() + "','"
           + getNombre() + "','"
           + getDescripcion() + "','"
           + getCveCategoria() + ","
           + getCantidad() +","
           + getPrecioC() +","
           + getPrecioV() +","
           + getCveProveedor() +")";*/
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Aviso", "No se pudo agregar producto", JOptionPane.ERROR_MESSAGE);
        }
        return res;
    }

    /*public int modificarProducto(){
   int res= 0;
  
       consulta = "UPDATE producto SET (codigo, nombre, descripcion, cveCategoria, "
            + "cantidad, precioCompra, precioVenta, cveProveedor ) "
            +"VALUES('"
            + getCodigo() + "','"
            + getNombre() + "','"
           + getDescripcion() + "','"
           + getCveCategoria() + ","
           + getCantidad() +","
           + getPrecioC() +","
           + getPrecioV() +","
           + getCveProveedor() +")";
   
   res = conn.Insertar(consulta);
    
   return res;
   }  */
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String cod) {
        this.codigo = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCveCategoria() {
        return cveCategoria;
    }

    public void setCveCategoria(int cveCategoria) {
        this.cveCategoria = cveCategoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioC() {
        return precioC;
    }

    public void setPrecioC(double precioC) {
        this.precioC = precioC;
    }

    public double getPrecioV() {
        return precioV;
    }

    public void setPrecioV(double precioV) {
        this.precioV = precioV;
    }

    public int getCveProveedor() {
        return cveProveedor;
    }

    public void setCveProveedor(int cveProveedor) {
        this.cveProveedor = cveProveedor;
    }

    public int buscarProducto(String cod) {
        int ban = 0;

        consulta = "SELECT 1 FROM producto WHERE codigo=" + cod; //optimizarla
        if (conn.siExiste(consulta)) {

        }

        consulta = "SELECT nombre, descripcion, cveCategoria, cantidad, precioCompra, "
                + "precioVenta, cveProveedor "
                + " FROM "
                + "producto WHERE codigo=" + cod;
        rs = conn.busqueda(consulta); //si existe se ponen los datos en las variables

        try {
            while (rs.next()) {
                this.setNombre(rs.getString(1));
                this.setDescripcion(rs.getString(2));
                this.setCveCategoria(rs.getInt(3));
                this.setCantidad(rs.getInt(4));
                this.setPrecioC(rs.getDouble(5));
                this.setPrecioV(rs.getDouble(6));
                this.setCveProveedor(rs.getInt(7));

            }
            ban = 1;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return ban;
    }

}
