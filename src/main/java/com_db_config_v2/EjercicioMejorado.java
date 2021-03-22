/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com_db_config_v2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EjercicioMejorado {
    
    /**
     * REQUERIMIENTOS: Muestra Iban, saldo, nombre_banco, nombre+apellido del clientes
     * de todos los clientes.
     */
    
    public static Connection con;
    public static Statement sentencia;
    
    static{
        //Conexion con el driver
        //y con la conexion de la bbdd
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //conectar con la bd CoffeeShop con usuario y clave
            //String url = "jdbc:derby://localhost:1527/Bank";
            String url = "jdbc:derby://localhost:1527/Bank2";
            con = DriverManager.getConnection(url);
        }
        catch(ClassNotFoundException e){
            System.out.println("Error carga driver: " + e.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }    
    }
    
    public static void informeCuentasBancariasPorConsola() throws SQLException{
        String consulta = "SELECT CB.IBAN, CB.SALDO, B.NOMBRE as NOMBRE_BANCO, C.NOMBRE, C.APELLIDOS " +
                          "FROM BANCOS B, CLIENTES C, CUENTAS_BANCARIAS CB " +
                          "WHERE B.ID_BANCO = C.ID_BANCO AND C.ID_CLIENTE = CB.ID_CLIENTE";
        sentencia = con.createStatement();
        ResultSet resultado = sentencia.executeQuery(consulta);
        while(resultado.next()){
                    System.out.printf("Iban = %-30s, Saldo = %10.2f, Nombre de banco = %-30s, Nombre = %-30s, Apellido = %-30s \n",
                        resultado.getString(1),
                        resultado.getDouble(2),
                        resultado.getString(3),
                        resultado.getString(4),
                        resultado.getString(5)
                    );
        }
        cerrarConexion();
    }
    
    public static void main(String[] args) throws SQLException {
//        informeCuentasBancariasPorConsola();
//        informeBancoPorConsola("SELECT * FROM BANCOS");
    }
    
    public static void insertInTo(String query) throws SQLException{
        sentencia = con.createStatement();
        sentencia.execute(query);
    }
    
    public static void informeBancoPorConsola(String query) throws SQLException{
        sentencia = con.createStatement();
        ResultSet resultado = sentencia.executeQuery(query);
        while(resultado.next()){
                    System.out.printf("Id_bancos = %d, Nombre de banco = %-30s \n",
                        resultado.getInt(1),
                        resultado.getString(2)
                    );
        }
        cerrarConexion();
    }
    
    public static void cerrarConexion(){
        if(con != null) 
            try {
                con.close();
            } catch (SQLException ex) {
                    System.out.println("ERROR = " + ex.getMessage());
            }
    }
    
}
