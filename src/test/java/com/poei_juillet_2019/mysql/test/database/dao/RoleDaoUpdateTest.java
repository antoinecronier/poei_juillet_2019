package com.poei_juillet_2019.mysql.test.database.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.poei_juillet_2019.mysql.database.DbManager;
import com.poei_juillet_2019.mysql.entities.Role;

public class RoleDaoUpdateTest {

    private static final String CHANGED_DATA = "toto";
    private List<Role> roles = new ArrayList<Role>();

    @Before
    public void setupTests() throws SQLException {
        DbManager.getInstance().getRoleDao().drop();
        DbManager.getInstance().getRoleDao().create();

        roles.clear();
        roles.add(new Role(1,"role1"));
        roles.add(new Role(2,"role2"));
        roles.add(new Role(3,"role3"));

        for (Role role : roles) {
            DbManager.getInstance().getRoleDao().insert(role);
        }
    }

    @Test
    public void simpleUpdateCompare1() throws SQLException {
        Role role = roles.get(0);
        role.setName(CHANGED_DATA);

        Role dbRole = (Role)DbManager.getInstance().getRoleDao().select(1);
        DbManager.getInstance().getRoleDao().update(role);
        Role dbRoleUpdated = (Role)DbManager.getInstance().getRoleDao().select(1);

        assertTrue(dbRole.getId() == dbRoleUpdated.getId() && !dbRole.getName().equals(dbRoleUpdated.getName()) && dbRoleUpdated.getName().equals(CHANGED_DATA));
    }

    @Test
    public void simpleUpdateCompare2() throws SQLException {
        Role role = roles.get(0);
        role.setName(CHANGED_DATA);

        DbManager.getInstance().getRoleDao().update(role);
        Role dbRoleUpdated = (Role)DbManager.getInstance().getRoleDao().select(1);

        assertTrue(role.getId() == dbRoleUpdated.getId() && role.getName().equals(dbRoleUpdated.getName()));
    }

    @Test
    public void simpleUpdateCompare3() {
        Role role = roles.get(0);
        Role dbRole = (Role)DbManager.getInstance().getRoleDao().select(1);

        assertTrue(role.getId() == dbRole.getId() && role.getName().equals(dbRole.getName()));
    }

    @Test(expected = MysqlDataTruncation.class)
    public void updateMaxValExtended() throws SQLException {
        Role role = roles.get(0);

        StringBuilder data = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            data.append("x");
        }
        role.setName(data.toString());

        DbManager.getInstance().getRoleDao().update(role);
    }

    @Test
    public void updateMaxValOK() throws SQLException {
        Role role = roles.get(0);

        StringBuilder data = new StringBuilder();
        for (int i = 0; i < 255; i++) {
            data.append("x");
        }
        role.setName(data.toString());

        DbManager.getInstance().getRoleDao().update(role);
    }

    @Test
    public void updateMinValOK() throws SQLException {
        Role role = roles.get(0);
        role.setName("");

        DbManager.getInstance().getRoleDao().update(role);
    }

    @Test(expected = MySQLIntegrityConstraintViolationException.class)
    public void updateNullValKO() throws SQLException {
        Role role = roles.get(0);
        role.setName(null);

        DbManager.getInstance().getRoleDao().update(role);
    }

    @Test
    public void updateWrongId() throws SQLException {
        Role role = roles.get(0);
        role.setId(4);
        role.setName(CHANGED_DATA);

        assertEquals(new Integer(0), DbManager.getInstance().getRoleDao().update(role));
    }

    @Test
    public void updateGoodId() throws SQLException {
        Role role = roles.get(0);
        role.setName(CHANGED_DATA);

        assertEquals(new Integer(1), DbManager.getInstance().getRoleDao().update(role));
    }
}
