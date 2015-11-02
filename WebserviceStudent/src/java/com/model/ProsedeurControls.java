/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.mysql.jdbc.Connection;
import com.servicelib.UserDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mr.Mic
 */
public class ProsedeurControls {

    DataBaseManagement dbmc;
    Connection con;
    String query;
    ResultSet rs;

    public ResultSet callProc(String procedure, String parameterSet) {
        dbmc = new DataBaseManagement();
        con = (Connection) dbmc.setConnetction();
        query = "call bdms." + procedure + parameterSet + ";";
        rs = dbmc.getResult(query, con);
        return rs;

    }

    public ResultSet callProc(String procedure) {
        dbmc = new DataBaseManagement();
        con = (Connection) dbmc.setConnetction();
        query = "call bdms." + procedure + "();";
        rs = dbmc.getResult(query, con);
        return rs;

    }

    public static void main(String[] args) {
        ProsedeurControls pc = new ProsedeurControls();
        ResultSet res = pc.callProc("selectAllUsers");
        try {
            while (res.next()) {
                String userid = res.getString(1);
                String firstname = res.getString(2);
                String lastname = res.getString(3);
                String   dateofbirth = res.getString(4);
                String address = res.getString(5);
                String contactno = res.getString(6);
                String email = res.getString(7);
                String bloodgroup = res.getString(8);
                System.out.println(userid);
                System.out.println(firstname);
                System.out.println(address);
                System.out.println(dateofbirth);
                System.out.println(lastname);
                System.out.println(contactno);
                System.out.println(email);
                System.out.println(bloodgroup);
                System.out.println(">>>>>>>>>>>>>>>>>>");

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
