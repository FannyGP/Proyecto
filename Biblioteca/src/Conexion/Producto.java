/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;
import Conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Dafne
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
   
   Conexion conn = new Conexion();
   
   public Producto(){
       
   }

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
    
    public void buscarProducto(String cod){
        ResultSet rs;
        
        String consulta = "SELECT nombre, descripcion, cveCategoria, cantidad, "
                + "precioCompra, precioVenta, cveProveedor"
                + " FROM producto WHERE codigo=" +
                             cod;
        rs=conn.busqueda(consulta);
        
        try{
        while(rs.next()){
            
        }
    }
        catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
            
        }
   
}
