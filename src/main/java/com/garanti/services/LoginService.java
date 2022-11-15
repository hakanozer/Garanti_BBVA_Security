package com.garanti.services;

import com.garanti.utils.DB;
import com.garanti.utils.Util;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class LoginService {

    /*
    public boolean login( String email, String password ) {
        boolean status = false;
        DB db = new DB();
        try {
            String sql = "select * from admin where email = '"+email+"' and password = '"+password+"'";
            Statement st = db.connect().createStatement();
            ResultSet rs = st.executeQuery(sql);
            status = rs.next();
        }catch (Exception ex) {
            System.err.println("login Error :" + ex);
        }finally {
            db.close();
        }
        return status;
    }
     */

    public boolean login( String email, String password ) {
        boolean status = false;
        DB db = new DB();
        try {
            String sql = "select * from admin where email = ? and password = ?";
            PreparedStatement st = db.connect().prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, Util.sha1(password));
            ResultSet rs = st.executeQuery();
            status = rs.next();
        }catch (Exception ex) {
            System.err.println("login Error :" + ex);
        }finally {
            db.close();
        }
        return status;
    }



}
