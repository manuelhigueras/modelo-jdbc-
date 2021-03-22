/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com_db_config_v2;

import com.db.PruebaPool.PoolConexiones;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InformesTiendaCafes {
    public static void cafesPorProvedor() {
        Connection con = PoolConexiones.getConexionLibre();
        if(con != null){
            try {
                //Consulta
                String query = "SELECT SUP_ID, COUNT(COF_NAME) AS NUMERO_CAFES FROM COFFEES group by sup_id";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                    System.out.printf("Proveedor %d cafes %d", rs.getInt(1), rs.getInt(2));
                }
                PoolConexiones.liberaConexion(con);
            } //Hay Conexion
            catch (SQLException ex) {
                Logger.getLogger(InformesTiendaCafes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            System.out.println("No se puede generar informe");
        }

    }
    
    public static void main(String[] args) {
         cafesPorProvedor();
    }
    
}
