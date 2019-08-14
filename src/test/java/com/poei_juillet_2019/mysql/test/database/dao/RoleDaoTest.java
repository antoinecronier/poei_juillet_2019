package com.poei_juillet_2019.mysql.test.database.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.poei_juillet_2019.mysql.database.DbManager;
import com.poei_juillet_2019.mysql.database.dao.RoleDao;
import com.poei_juillet_2019.mysql.entities.Role;

public class RoleDaoTest {

    static List<Role> roles = new ArrayList<Role>();

    @BeforeClass
    public static void config() {
        Role role1 = new Role("role1");
        Role role2 = new Role("role2");
        Role role3 = new Role("role3");
        role1.setId(1);
        role2.setId(2);
        role3.setId(3);
        roles.add(role1);
        roles.add(role2);
        roles.add(role3);
    }

    @Before
    public void drop() throws Exception{
        DbManager.getInstance().getRoleDao().drop();
        DbManager.getInstance().getRoleDao().create();
        DbManager.getInstance().getRoleDao().insert(roles.get(0));
        DbManager.getInstance().getRoleDao().insert(roles.get(1));
        DbManager.getInstance().getRoleDao().insert(roles.get(2));
    }

    @Test
    public void selectAll() throws Exception {
        assertNotNull(DbManager.getInstance().getRoleDao().select());
    }

    @Test
    public void selectAllCount() throws Exception {
        List<Object> listObjects = DbManager.getInstance().getRoleDao().select();
        DbManager.getInstance().getRoleDao().select();
        assertEquals(3, listObjects.size());
    }

    @Test
    public void dataCompare() throws Exception {
        List<Object> listObjects = DbManager.getInstance().getRoleDao().select();

        assertTrue((roles.get(0).getId() == ((Role)listObjects.get(0)).getId()) && (roles.get(0).getName().equals(((Role)listObjects.get(0)).getName())));
    }















}
