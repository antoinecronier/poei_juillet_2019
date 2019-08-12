package com.poei_juillet_2019.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.fabric.xmlrpc.base.Data;
import com.poei_juillet_2019.mysql.database.DbOpenHelper;
import com.poei_juillet_2019.mysql.database.UserDao;
import com.poei_juillet_2019.mysql.entities.User;

/**
 *
 * @author antoine.cronier
 *
 */
public final class MySqlApplication {

    private MySqlApplication() {

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        UserDao dao = new UserDao();
        dao.drop();
        dao.create();
/*
        try {
            User.playDrop();
            User.playCreate();
            User u1 = new User("antoine","cronier", new SimpleDateFormat("yyyy/mm/dd").parse("1990/04/24"));
            u1.playInsert();
            u1.playInsert();
            u1.playInsert();
            u1.select();
            System.out.println("---------------");
            u1.setId(1);
            u1.playDelete();
            u1.select();
            System.out.println("---------------");
            u1.setId(2);
            u1.playUpdate("jean", "michel", new SimpleDateFormat("yyyy/mm/dd").parse("1999/04/24"));
            u1.select();
            System.out.println("---------------");
        } catch (ParseException e1) {
            e1.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.println("Hello World");
        try {
            ResultSet rs = DbOpenHelper.getInstance().getConn().createStatement().executeQuery("SHOW TABLES;");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    }
}
