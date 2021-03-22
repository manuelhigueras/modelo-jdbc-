/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.transaccion;

import com.db.PruebaPool.PoolConexiones;
import java.sql.*;

public class Mantenimiento_tienda {
    public void corregirFallo(String nombreCafeOrigen, String nombreCafeDestino,
                              int cantidadACorregir) throws SQLException{
        //Obtener conexion y libera
        Connection con = PoolConexiones.getConexionLibre();
       
        try{
            //UPDATE DE CONSULTA
            String updateQuery = "UPDATE COFFEES SET SALES = SALES + ? " +
                    "WHERE COF_NAME LIKE ?";
            con.setAutoCommit(false); //desactivo la autoconfirmacion
            PreparedStatement pst = con.prepareStatement(updateQuery);
            //MODIFICA VENTAS DE CAFE DE ORIGEN (+ cantidadACorregir)
            pst.setInt(1, cantidadACorregir);
            pst.setString(2, nombreCafeOrigen);
            pst.executeUpdate();
            //VENTAS DEL CAFE DE DESTINO (- cantidadACorregir)
            pst.setInt(1, -cantidadACorregir);
            pst.setString(2, nombreCafeDestino);
            pst.executeUpdate();
            con.commit();
            
        }
        catch(SQLException e){
            System.out.println("... no se pudo hacer la correcion");
            try{
                con.rollback();
            }
            catch(SQLException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
        finally{
            PoolConexiones.liberaConexion(con);
        }
    }
    public void Sumar(String nombreCafeOrigen, int cantidadACorregir) throws SQLException{
        //Obtener conexion y libera
        Connection con = PoolConexiones.getConexionLibre();
        try{
            //UPDATE DE CONSULTA
            String updateQuery = "UPDATE COFFEES SET SALES = SALES + ? " +
                    "WHERE COF_NAME LIKE ?";
            con.setAutoCommit(false); //desactivo la autoconfirmacion
            PreparedStatement pst = con.prepareStatement(updateQuery);
            //MODIFICA VENTAS DE CAFE DE ORIGEN (+ cantidadACorregir)
            pst.setInt(1, cantidadACorregir);
            pst.setString(2, nombreCafeOrigen);
            pst.executeUpdate();
            con.commit();
        }
        catch(SQLException e){
            System.out.println("... no se pudo hacer la correcion");
            try{
                con.rollback();
            }
            catch(SQLException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
        finally{
            PoolConexiones.liberaConexion(con);
        }
    }
}
/**
 * https://docs.oracle.com/javase/tutorial/jdbc/basics/transactions.html
De Begoña Olea para todos:  12:30 PM
//2 UPDATE
            String updateQuery = "UPDATE COFFEES "
                    + "SET SALES = SALES + ? "
                    + "WHERE COF_NAME LIKE ?";
            
            PreparedStatement pst = con.prepareStatement(updateQuery);
            
            // modificar ventas del cafe de origen   (+ cantidadACorregir)
            pst.setInt(1, cantidadACorregir);
            pst.setString(2, nombreCafeOrigen);
            pst.executeUpdate();
            
            // modificar vents del cafe de destino (- cantidadACorregir)
            pst.setInt(1, - cantidadACorregir);
            pst.setString(2, nombreCafeDestino);
            pst.executeUpdate();
De Begoña Olea para todos:  01:00 PM
-----------
public void corregirFallo(String nombreCafeOrigen,
                              String nombreCafeDestino,
                              int cantidadACorregir) {
        
        //obtener conexión  a la bd CofeeShop
        Connection con = PoolConexiones.getConexionLibre();
        
        try{
            con.setAutoCommit(false);  //desactivo la autoconfirmacion 
            //2 UPDATE
            String updateQuery = "UPDATE COFFEES "
                    + "SET SALES = SALES + ? "
                    + "WHERE COF_NAME LIKE ?";
            
            PreparedStatement pst = con.prepareStatement(updateQuery);
            
            // modificar ventas del cafe de origen   (+ cantidadACorregir)
            pst.setInt(1, - cantidadACorregir);
            pst.setString(2, nombreCafeOrigen);
            pst.executeUpdate();
// modificar vents del cafe de destino (- cantidadACorregir)
            pst.setInt(1, + cantidadACorregir);
            pst.setString(2, nombreCafeDestino);
            pst.executeUpdate();
            
            con.commit();
        }catch(SQLException e){
            System.out.println("... no se pudo hacerla correccion");
            try {
                con.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(MantenimientoTienda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            PoolConexiones.liberaConexion(con);
        }
 
    }

 */