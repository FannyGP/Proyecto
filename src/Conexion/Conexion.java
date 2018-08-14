/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;


import java.sql.*;

/**
 *
 * @author Dafne
 */
public class Conexion {
    static Connection con;
    Statement smt;
    ResultSet rs;
    
public static Connection ConectarBD(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost/producto"
                ,"root","");
        
    }
    catch(ClassNotFoundException | SQLException error){
        System.out.println("Error en la conexión ->"
                + error.getMessage());
        
    }
    return con;
}


public void Conectar(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost/producto"
                ,"root","");
        
    }
    catch(ClassNotFoundException | SQLException error){
        System.out.println("Error en la conexión ->"
                + error.getMessage());
    }
}

public void Desconectar(){
    try{
        con.close();
        
    }
    catch(SQLException error){
           System.out.println(
                   "Error al desconectar la base de datos "
                        + error.getMessage());
        
    }
}

    public boolean siExiste(String consulta){
        boolean existeRegistro = false;
        Conectar();
        
        try(PreparedStatement ps = con.prepareStatement(consulta)){
            rs = ps.executeQuery(); //ejecuta consulta
            if(rs.next()){
                if(rs.getRow()>0){
                    existeRegistro= true;
                }
            }
        }
        catch(SQLException e){
            System.out.println("Error en la consulta " +e.getMessage());
        }
        return existeRegistro;
    }

    public ResultSet busqueda(String consulta){
    Conectar();
    
        try{
            smt = con.createStatement(); //va la consulta
            rs = smt.executeQuery(consulta); //retornar la consulta
        }
        catch(SQLException e){
            System.out.println("Error en la consulta "
                            + e.getMessage());
        }
        return rs;
    }
    
    public int Insertar(String consulta){
        int resultado = 0;
    Conectar();
    
        try{
            smt = con.createStatement(); //va la consulta
            resultado = smt.executeUpdate(consulta); //retornar la consulta
        }
        catch(SQLException e){
            System.out.println("Error en la consulta "
                            + e.getMessage());
        }
        Desconectar();
        return resultado;
    }

   /* public int Modificar(String consulta) {
     int resultado = 0;
    Conectar();
    
        try{
            smt = con.createStatement(); //va la consulta
            resultado = smt.executeUpdate(consulta); //retornar la consulta
        }
        catch(SQLException e){
            System.out.println("Error en la consulta "
                            + e.getMessage());
        }
        Desconectar();
        return resultado;*/
       
    
}
