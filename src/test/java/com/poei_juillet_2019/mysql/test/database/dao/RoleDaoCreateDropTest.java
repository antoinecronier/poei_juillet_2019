package com.poei_juillet_2019.mysql.test.database.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import com.poei_juillet_2019.mysql.database.DbManager;
import com.poei_juillet_2019.mysql.database.DbOpenHelper;
import com.poei_juillet_2019.mysql.database.contracts.RoleContract;
import com.poei_juillet_2019.mysql.entities.Role;
import com.poei_juillet_2019.mysql.test.database.utils.DescribeQuery;

public class RoleDaoCreateDropTest {

    @Test
    public void testGetRoleDaoCreateTableMatchingFields() throws SQLException {
        DbManager.getInstance().getRoleDao().drop();
        DbManager.getInstance().getRoleDao().create();

        ResultSet rs = DbOpenHelper.getInstance().getConn().createStatement()
                .executeQuery("DESCRIBE " + RoleContract.TABLE);
        List<DescribeQuery> describeQuery = new ArrayList<DescribeQuery>();
        while (rs.next()) {
            DescribeQuery desc = new DescribeQuery();
            desc.setField(rs.getString(1));
            desc.setType(rs.getString(2));
            desc.setNullable(rs.getString(3));
            desc.setKeyType(rs.getString(4));
            desc.setDefaultValue(rs.getString(5));
            desc.setExtra(rs.getString(6));
            describeQuery.add(desc);
        }

        for (int i = 0; i < describeQuery.size(); i++) {
            if (!describeQuery.get(i).getField().equals(RoleContract.COLS[i])) {
                fail("Column name do not match");
            }
        }

        assertTrue(true);
    }

    @Test
    public void testGetRoleDaoCreateTableInsertWorking() throws SQLException {
        DbManager.getInstance().getRoleDao().drop();
        DbManager.getInstance().getRoleDao().create();
        try {
            DbManager.getInstance().getRoleDao()
                    .insert(new Role("roleTest"));
        } catch (Exception e) {
            fail("Insertion failure");
        }

        assertTrue(true);
    }

    @Test
    public void testGetRoleDaoDropTableRemoved() throws SQLException {
        DbManager.getInstance().getRoleDao().drop();
        ResultSet rs = DbOpenHelper.getInstance().getConn().createStatement().executeQuery("SHOW TABLES");
        while (rs.next()) {
            if (rs.getString(1).equals(RoleContract.TABLE)) {
                fail("Table already exists");
            }
        }
    }

    @Test(expected = MySQLSyntaxErrorException.class)
    public void testGetRoleDaoDropCannotInsert() throws SQLException, ParseException {
        DbManager.getInstance().getRoleDao().drop();
        DbManager.getInstance().getRoleDao()
                .insert(new Role("roleTest"));
    }

    @Test
    public void testGetRoleDaoDropCannotInsertGoodMessage() {
        DbManager.getInstance().getRoleDao().drop();
        try {
            DbManager.getInstance().getRoleDao()
                    .insert(new Role("roleTest"));
        } catch (SQLException e) {
            if (e.getMessage().equals("Unknown table 'disco." + RoleContract.TABLE + "'")) {
                assertTrue(true);
            }
        }
    }
}
