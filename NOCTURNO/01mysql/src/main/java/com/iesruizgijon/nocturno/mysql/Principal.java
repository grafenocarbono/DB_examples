/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.iesruizgijon.nocturno.mysql;

import java.util.ArrayList;

/**
 *
 * @author grafeno30
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        final String USER = "root";
        final String PASS="123qweASD_";
        final String nameDB = "northwind";
        //String[] resultado;
        
        BaseDatos bd = new BaseDatos(nameDB, USER, PASS);
        
        bd.conecta();
        
        //bd.consultaPrueba2();
        
       // resultado = bd.describe("customers");
        
       // for (String linea : resultado) {
       //     System.out.println(linea);
            
       // }
       
       bd.getDataBaseNames();
        
        bd.desconecta();    
        
    }
    
}