/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iesruizgijon.nocturno.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author grafeno30
 */
public class BaseDatos {

    private final String URL = "jdbc:mysql://127.0.0.1:3306/";
    private final String nameDB;
    private final String user;
    private final String pass;

    Connection conexion;

    public BaseDatos(String nameDB, String user, String pass) {
        this.nameDB = nameDB;
        this.user = user;
        this.pass = pass;
    }

    public void conecta() {
        try {
            conexion = DriverManager.getConnection(URL + nameDB, user, pass);
            System.out.println("Conexión establecida con éxito...");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void desconecta() {

        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void consultaPrueba() {
        String company;
        String apellidos;
        String nombre;

        String consulta
                = """
                select company, last_name, first_name from 
                customers limit 10
                """;
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(consulta);
            while (resultado.next()) {
                company = resultado.getString("company");
                apellidos = resultado.getString("last_name");
                nombre = resultado.getString("first_name");

                System.out.println("Company " + company + " Apellidos " + apellidos + " Nombre " + nombre);
            }
            resultado.close();
            sentencia.close();

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<String> consultaPrueba2() {

        ArrayList<String> resultados = new ArrayList<>();
        String nombre;
        String pedidos;

        String consulta
                = """
                select concat_ws("-",contactFirstName, contactLastName)
                as name, COUNT(OrderNumber) as numOrders  
                FROM customers inner join orders  
                on customers.customerNumber = orders.customerNumber  
                group by customers.customerNumber order by name; 
                """;

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(consulta);
            while (resultado.next()) {
                nombre = resultado.getString("name");
                pedidos = Integer.toString(resultado.getInt("numOrders"));
                resultados.add(nombre + " - " + pedidos);
            }
            resultado.close();
            sentencia.close();

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultados;

    }

    public String[] describe(String nombre) {

        String[] columnas = null;
        int n_columnas = 0;
        String consulta = "SELECT * FROM " + nombre;

        try {

            Statement statement = conexion.createStatement();
            ResultSet resultset = statement.executeQuery(consulta);
            ResultSetMetaData metadatos = resultset.getMetaData();

            n_columnas = metadatos.getColumnCount();
            columnas = new String[n_columnas];

            for (int i = 1; i <= n_columnas; i++) {
                columnas[i - 1] = metadatos.getColumnName(i);
            }
        } catch (SQLException ex) {

            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return columnas;
    }

    public void getDataBaseNames() {

        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("Show Databases");

            System.out.println("List of databases: ");

            while (rs.next())
                System.out.println(rs.getString(1));

        } catch (SQLException ex) {

            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
