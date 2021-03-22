/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com_db_config_coffee;

import com_db_config_coffee.Coffee;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Coffee service = new Coffee(); 
        try{
//            service.modificarVentas("Colombian", 75);
//            service.modificarVentasPrepared("Expresso",10);
            //service.modificarVentasPrepared("a' OR '1' = '1", 200);
            //service.insertInTo("EXPRESSOOO2", 20);
            service.vender("AAAAAAAAA", 150);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
}
