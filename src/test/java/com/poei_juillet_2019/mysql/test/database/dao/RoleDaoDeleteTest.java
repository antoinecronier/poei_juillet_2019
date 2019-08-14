package com.poei_juillet_2019.mysql.test.database.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.poei_juillet_2019.mysql.database.DbManager;
import com.poei_juillet_2019.mysql.entities.Role;

public class RoleDaoDeleteTest {

    @Before
    public void dropCreate() {
        DbManager.getInstance().getRoleDao().drop();
        DbManager.getInstance().getRoleDao().create();
    }

    @Test
    public void testRoleDaoDeleteSimple() throws SQLException, ParseException {
        Role role = new Role("Role1");
        DbManager.getInstance().getRoleDao().insert(role);

        role.setId(1);
        DbManager.getInstance().getRoleDao().delete(role);

        assertNull(DbManager.getInstance().getRoleDao().select(1));
    }

//    @Test
//    public void testRoleDaoDeleteMultiple() throws SQLException, ParseException {
//        boolean statut = false;
//
//        for (int i = 1; i <= 10; i++) {
//            Role role = new Role("Role" + i);
//            role.setId(i);
//            DbManager.getInstance().getRoleDao().insert(role);
//
//            if (i == 5) {
//                DbManager.getInstance().getRoleDao().delete(role);
//            }
//        }
//
//        for (int i = 1; i <= 10; i++) {
//            if (i == 5 && DbManager.getInstance().getRoleDao().select(i) == null) {
//                statut = true;
//            }
//        }
//
//        assertTrue(statut);
//    }

    @Test
    public void testRoleDaoDeleteMultiple2() throws SQLException, ParseException {
        boolean statut = false;

        for (int i = 17; i <= 102; i += 17) {
            Role role = new Role("Role" + i);
            role.setId(i);
            DbManager.getInstance().getRoleDao().insert(role);

            if (i == 51) {
                DbManager.getInstance().getRoleDao().delete(role);
            }
        }

        for (int i = 17; i <= 102; i += 17) {
            if (i == 51 && DbManager.getInstance().getRoleDao().select(i) == null) {
                statut = true;
            }
        }

        assertTrue(statut);
    }

    @Test
    public void testRoleDaoDeleteNullMultiple() throws SQLException, ParseException {
        int statut = 0;

        for (int i = 17; i <= 102; i += 17) {
            Role role = new Role("Role" + i);
            role.setId(i);
            DbManager.getInstance().getRoleDao().insert(role);

            if (i == 102) {
              DbManager.getInstance().getRoleDao().delete(role);
              statut = DbManager.getInstance().getRoleDao().delete(role);
            }
        }

        assertEquals(0, statut);
    }

    @AfterClass
    public static void dropAfter() {
        DbManager.getInstance().getRoleDao().drop();
        DbManager.getInstance().getRoleDao().create();
    }
}
