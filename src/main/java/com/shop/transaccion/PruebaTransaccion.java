/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.transaccion;

import java.sql.SQLException;

public class PruebaTransaccion {
    public static void main(String[] args) {
        try{
            Mantenimiento_tienda mto = new Mantenimiento_tienda();
            mto.corregirFallo("Colombian", "Espresso", 25);
//            mto.Sumar("French_Roast", 10);
//            mto.Sumar("Colmbian_Decaf", 25);
//            mto.Sumar("Colombian", 34);
//            mto.Sumar("Espresso", 52);
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}
