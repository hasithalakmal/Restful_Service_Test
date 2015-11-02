package com.servicelib;

import com.model.ProsedeurControls;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao {

    public List<User> getAllUsers() {
        List<User> userList = null;
        userList = new ArrayList<User>();
        try {

            ProsedeurControls pc = new ProsedeurControls();
            ResultSet res = pc.callProc("selectAllUsers");

            while (res.next()) {
                String userid = res.getString(1);
                String firstname = res.getString(2);
                String lastname = res.getString(3);
                String dateofbirth = res.getString(4);
                String address = res.getString(5);
                String contactno = res.getString(6);
                String email = res.getString(7);
                String bloodgroup = res.getString(8);
                User user = new User(userid, firstname, lastname, dateofbirth, address, contactno, email, bloodgroup);
                userList.add(user);
                saveUserList(userList);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public User getUser(String id) {
        ProsedeurControls pc = new ProsedeurControls();
        User user = null;
        String para = "('" + id + "')";
        ResultSet res = pc.callProc("select_user", para);
        try {
            while (res.next()) {
                String userid = res.getString(1);
                String firstname = res.getString(2);
                String lastname = res.getString(3);
                String dateofbirth = res.getString(4);
                String address = res.getString(5);
                String contactno = res.getString(6);
                String email = res.getString(7);
                String bloodgroup = res.getString(8);
                user = new User(userid, firstname, lastname, dateofbirth, address, contactno, email, bloodgroup);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public int addUser(String id, String name, String lname, String dob, String adress, String phone, String email, String bloodgroup) {

        ProsedeurControls pc = new ProsedeurControls();

        String para = "('" + id + "')";
        ResultSet res = pc.callProc("select_user", para);
        try {
            if (res.next()) {
                return 0;
            } else {
                para = "('" + id + "','" + name + "','" + lname + "','" + dob + "','" + adress + "','" + phone + "','" + email + "','" + bloodgroup + "')";
                res = pc.callProc("insert_user", para);
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public int updateUser(String id, String adress, String phone, String email) {
        ProsedeurControls pc = new ProsedeurControls();
        User user = null;
        String para = "('" + id + "')";
        ResultSet res = pc.callProc("select_user", para);
        try {
            if (res.next()) {
                para = "('" + id + "','" + adress + "','" + phone + "','" + email  + "')";
                res = pc.callProc("update_user", para);
                return 1;
            } else {

                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public int deleteUser(String id) {
        ProsedeurControls pc = new ProsedeurControls();
        User user = null;
        String para = "('" + id + "')";
        ResultSet res = pc.callProc("select_user", para);
        try {
            if (res.next()) {
                para = "('" + id + "')";
                res = pc.callProc("delete_user", para);
                return 1;
            } else {

                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    private void saveUserList(List<User> userList) {
        try {
            File file = new File("User.dat");
            FileOutputStream fos;

            fos = new FileOutputStream(file);

            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(userList);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
