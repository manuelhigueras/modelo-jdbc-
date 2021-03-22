/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

buscar el significado de cola y pila
 */
package com.db.PruebaPool;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PoolConexiones {
    
    private static DriverManager dm;
    private static int numeroMaxConexiones;
    private static final int NUM_MIN_CONEXIONES = 3;
    private static String driverClass;
    private static String url;
    private static String usuario;
    private static String clave;
    
    private static LinkedList<Connection> conexionesLibres;
    private static LinkedList<Connection> conexionesOcupadas;
      
    static {
        /*Map de conexiones que lee en un fichero de propiedades*/
       Properties prop = null;
        try{
            prop = new Properties();
            prop.load(new FileReader("db/db.properties"));
            numeroMaxConexiones = Integer.parseInt(prop.getProperty("numero_max_conexiones","" + NUM_MIN_CONEXIONES));
            driverClass = prop.getProperty("driver_class");
            url = prop.getProperty("url_conexion");
//            usuario = prop.getProperty("usuario");
//            clave = prop.getProperty("clave");
            //Carga driver
            Class.forName(driverClass);
            //carga pool
            inicializarPool();
       }
       catch(ClassNotFoundException ef){
           System.out.println("No pudo cargar el driver " + ef.getMessage());
       }
       catch(Exception ex){
           System.out.println("ERROR leer parametro crear Pool" + ex.getMessage());
       }
    }
    
    private PoolConexiones(){
        
    }
    
    public static int getNumeroMaxConexiones(){
        return numeroMaxConexiones;
    }

    public static Connection getConnection() throws SQLException{
        Connection con = null;
        if(usuario != null && usuario.length() > 0){
            con = DriverManager.getConnection(url, usuario, clave);
        }else{
            con = DriverManager.getConnection(url);
        }
        return con;
    }
    
    private static void inicializarPool() throws SQLException{
        //Crear coleccion conexiones disponibles
        conexionesLibres = new LinkedList<Connection>();
        for(int i = 0; i < numeroMaxConexiones; i++){
            Connection con = getConnection();
            conexionesLibres.add(con);
        }
        conexionesOcupadas = new LinkedList<Connection>();
    }
    
    public static Connection getConexionLibre(){
        Connection con = null;
        if(conexionesLibres.size() == 0){
            System.out.println("... No hay conexiones disponibles");
        }
        else{
            con = conexionesLibres.getLast();
            conexionesLibres.remove(con);
            conexionesOcupadas.addLast(con);
        }
        return con;
    }
    
    public static void liberaConexion(Connection con){
        conexionesOcupadas.remove(con);
        conexionesLibres.addFirst(con);
    }
    
    public static void cerrarPool(){
        if(conexionesOcupadas.size() > 0){
            System.out.println("No se puede cerrar Pool. Hay conexiones ocupadas");
        }
        else{
            for(Connection c: conexionesLibres){
                try{
                    c.close();
                }
                catch(SQLException ex){
                    System.out.println("Error:"+ex.getMessage());
                }
            }
        }
    }
    
    public static int getTotalConexionesLibres(){
        return conexionesLibres.size();
    }
    
    public static int getTotalConexionesOcupadas(){
        return conexionesOcupadas.size();
    }
    
}

/**
 * 1. leer un fichero de propiedades .properties los datos para crear el pool:
 *    . numero_max_conexiones
 *    . url_conexion
 *    . usuario
 *    . clave
 * 
 */
