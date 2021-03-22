package com_db_config_coffee;

import com.db.PruebaPool.PoolConexiones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Coffee {
    
    public void modificarVentas(String nombre, int ventas) 
            throws TiendaCafesException, SQLException{
        
        String updateString = "UPDATE COFFEES " +
                 " SET SALES = " + ventas +
                 " WHERE COF_NAME LIKE '" +  nombre +  "'";
                 
        Connection con = PoolConexiones.getConexionLibre();
        if(con == null){
            throw new TiendaCafesException("Tienda. No se puede modificar ventas. No hay conexión db");
        }

        Statement st = con.createStatement();
        int modificados = st.executeUpdate(updateString);
        if (modificados == 0){
            throw new TiendaCafesException(("Tienda. No existe el  café " + nombre));
        }
        
        PoolConexiones.liberaConexion(con);
        
    }//fin metodo
    
    public void modificarVentasPrepared(String nombre, int ventas) throws TiendaCafesException, SQLException{
        String updateString = "UPDATE COFFEES " + 
                " SET SALES = ?" +
                " WHERE COF_NAME LIKE ?";
        Connection con = PoolConexiones.getConexionLibre();
        if(con == null){
            throw new TiendaCafesException("Tienda. No se puede modificar ventas. No hay conexion db");
        }
        PreparedStatement st = con.prepareStatement(updateString);
        st.setInt(1, ventas);
        st.setString(2, nombre);
        int modificados = st.executeUpdate();
        if(modificados == 0){
            throw new TiendaCafesException("Tienda. No existe el cafe " + nombre);
        }
        PoolConexiones.liberaConexion(con);
    }
    
    public void vender(String nombreCafe, int cantidad) throws TiendaCafesException, SQLException{
        String query = "INSERT INTO VENTAS VALUES('26-11-2020', '" + nombreCafe + "'," + cantidad + ")";
        Connection con = PoolConexiones.getConexionLibre();
        if(con == null){
            throw new TiendaCafesException("Tienda. No se puede modificar ventas. No hay conexion db");
        }
        PreparedStatement st = con.prepareStatement(query);
        java.util.Date hoy = new java.util.Date();
        st.setDate(1, new java.sql.Date(hoy.getTime()));
        st.setString(2, nombreCafe);
        st.setInt(3, cantidad);
        int insertados = st.executeUpdate();
        if (insertados == 0){
            throw new TiendaCafesException(("Tienda. No insertó la venta ."));
        }
        PoolConexiones.liberaConexion(con);
    }
    
    public static void insertInTo(String nombreCafe, int cantidad) throws SQLException{
        //Statement sentencia = con.createStatement();
        String query = "INSERT INTO VENTAS VALUES(null, '" + nombreCafe + "'," + cantidad + ")";
        Connection con = PoolConexiones.getConexionLibre();
        Statement s = con.createStatement();
        s.execute(query);
    }
}
