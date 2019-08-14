package com.poei_juillet_2019.mysql.test.database;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.poei_juillet_2019.mysql.database.DbManager;
import com.poei_juillet_2019.mysql.database.DbOpenHelper;
import com.poei_juillet_2019.mysql.database.contracts.UserContract;

public class DbManagerTest {

    @Test
    public void testGetInstance() {
        assertNotNull(DbManager.getInstance());
    }

    @Test
    public void testGetUserDao() {
        assertNotNull(DbManager.getInstance().getUserDao());
    }

    @Test
    public void testGetUserDaoCreateTable() throws SQLException {
        DbManager.getInstance().getUserDao().drop();
        DbManager.getInstance().getUserDao().create();

        ResultSet rs = DbOpenHelper.getInstance().getConn().createStatement().executeQuery("DESCRIBE " + UserContract.TABLE);
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
            if (!describeQuery.get(i).getField().equals(UserContract.COLS[i])) {
                fail("Column name do not match");
            }
        }

        assertTrue(true);
    }

    @Test
    public void testGetRoleDao() {
        assertNotNull(DbManager.getInstance().getRoleDao());
    }

}
