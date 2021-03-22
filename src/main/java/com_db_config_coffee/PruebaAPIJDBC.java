/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com_db_config_coffee;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PruebaAPIJDBC {
    public static void main(String[] args) {
        /*
        1.Cargar Driver base de datos JavaDB (Derby)
            DriverManager
        2.Obtener una conexion
            Connection
        3.Obtener una sentencia para lanzar una instruccion
            Statement
        4.Ejecutar sentencia generalista - execute
            Ejecutar sentencia cambia algo - executeUpdate
            que retorna  el numero de reg afectados
        5.Consultamos todos los cafe
            Ejecutamos la sentencia selec - executeQuery
            que retorna un resulset (resultado en forma de tabla)
        */
        Connection con = null;
        try{
            System.out.println("Intentando cargar driver");
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            System.out.println("Cargo ok el driver");
            //conectar con la bd CoffeeShop con usuario y clave
            String url = "jdbc:derby://localhost:1527/CoffeeShop";
            System.out.println("... Obtener conexion con la bd");
            con = DriverManager.getConnection(url);
            System.out.println("... OK ESTAMOS CONECTADOS");
            String query = "CREATE TABLE COFFEES(COF_NAME VARCHAR(32), SUP_ID INTEGER, PRICE FLOAT, SALES INTEGER,TOTAL INTEGER)";
            Statement sentencia = con.createStatement();
//            sentencia.execute(query);
            String insertInTo;
//            insertInTo = "INSERT INTO COFFEES " +
//            "VALUES ('Colombian', 101, 7.99, 0, 0),"
//                + "('French_Roast', 49, 8.99, 0, 0), "
//                + "('EspressoGH', 150, 9.99, 0, 0), "
//                + "('Colombian_ALDll_Decaf', 101, 8.99, 0, 0), "
//                + "('French_Roast_Decaf', 49, 9.99, 0, 0)";
            //Consulta insertada
//            int filaInsertada = sentencia.executeUpdate(insertInTo);
//            System.out.printf("Ha insertado %d cafes.", filaInsertada);
            //Consulta de cafes
            insertInTo = "Select * from Coffees";
            ResultSet resultado = sentencia.executeQuery(insertInTo);
            boolean hay = resultado.next();
            //1 CASO DE BUSQUEDA o VARIOS CASOS DE BUSQUEDA
            //REC: SE CHEQUEA COMO UN ARRAY
           //if(hay){
            while(resultado.next()){
                System.out.printf("café %-30s del proveedor id %6d y su precio es de %10.2f \n",
                         resultado.getString(1), // columan 1
                         resultado.getInt(2),     //columna 2
                         resultado.getDouble(3)    //columna 3
                        );
            }//fin recorrer resultados

            
        }
        catch(ClassNotFoundException e){
            System.out.println("Error carga driver: " + e.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        finally{
            if(con != null) 
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("ERROR = " + ex.getMessage());
                }
        }
    }
}
