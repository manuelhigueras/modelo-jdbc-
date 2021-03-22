/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.PruebaPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestPool {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            //Solicito una conexion que me da de Pool de conexiones libres
            int n = PoolConexiones.getNumeroMaxConexiones();
            System.out.printf("Pool con un maximo de %d conexiones \n", n);
            System.out.printf("Total libre %d y total ocupadas %d \n", PoolConexiones.getTotalConexionesLibres(), PoolConexiones.getTotalConexionesOcupadas());
            //Solicito una conexion que me da de Pool de conexiones libres
            Connection con = PoolConexiones.getConexionLibre();
            System.out.printf("Total libre %d y total ocupadas %d \n", PoolConexiones.getTotalConexionesLibres(), PoolConexiones.getTotalConexionesOcupadas());
            Connection con2 = PoolConexiones.getConexionLibre();
            System.out.printf("Total libre %d y total ocupadas %d \n", PoolConexiones.getTotalConexionesLibres(), PoolConexiones.getTotalConexionesOcupadas());
            System.out.println("Libero conexion (con)");
            PoolConexiones.liberaConexion(con);
            PoolConexiones.liberaConexion(con2);
            System.out.printf("Total libre %d y total ocupadas %d \n", PoolConexiones.getTotalConexionesLibres(), PoolConexiones.getTotalConexionesOcupadas());
        } catch (Exception ex) {
            Logger.getLogger(TestPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
