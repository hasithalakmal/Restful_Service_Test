/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;


import java.sql.*;

    
/**
 *
 * @author Mr.Mic
 */
public class DataBaseManagement {
    public Statement state;
    public ResultSet result;
    public Connection con;
   

    public Connection setConnetction() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdms","root","massa@123");
        } catch (Exception e) {
            
        } 
        return con;
    }
    
    public ResultSet getResult(String query,Connection con){
        try{
            state = con.createStatement();
            result = state.executeQuery(query);
        
        }catch(Exception e){
        
        }
        return result;
    }
}

