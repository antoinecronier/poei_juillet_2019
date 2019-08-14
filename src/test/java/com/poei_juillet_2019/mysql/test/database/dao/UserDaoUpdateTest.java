package com.poei_juillet_2019.mysql.test.database.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.poei_juillet_2019.mysql.database.DbManager;
import com.poei_juillet_2019.mysql.entities.Role;
import com.poei_juillet_2019.mysql.entities.User;

public class UserDaoUpdateTest {

    @Test
    public void test() throws ParseException, SQLException {
        DbManager.getInstance().getRoleDao().drop();
        DbManager.getInstance().getRoleDao().create();

        List<Role> roles = new ArrayList<Role>();
        for (int i = 1; i <= 3; i++) {
            Role role = new Role("role"+i);
            role.setId(i);
            roles.add(role);
            DbManager.getInstance().getRoleDao().insert(role);
        }

        List<Object> datas = DbManager.getInstance().getRoleDao().select();

        System.out.println(roles.get(0).getId() == ((Role)datas.get(0)).getId());
        System.out.println(roles.get(0).getName().equals(((Role)datas.get(0)).getName()));
        System.out.println(roles.get(0).equals(((Role)datas.get(0))));
    }
}
