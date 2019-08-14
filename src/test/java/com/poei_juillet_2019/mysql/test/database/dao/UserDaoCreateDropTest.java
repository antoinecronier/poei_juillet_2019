package com.poei_juillet_2019.mysql.test.database.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import com.poei_juillet_2019.mysql.database.DbManager;
import com.poei_juillet_2019.mysql.database.DbOpenHelper;
import com.poei_juillet_2019.mysql.database.contracts.UserContract;
import com.poei_juillet_2019.mysql.entities.User;
import com.poei_juillet_2019.mysql.test.database.utils.DescribeQuery;

public class UserDaoCreateDropTest {

    @Test
    public void testGetUserDaoCreateTableMatchingFields() throws SQLException {
        DbManager.getInstance().getUserDao().drop();
        DbManager.getInstance().getUserDao().create();

        ResultSet rs = DbOpenHelper.getInstance().getConn().createStatement()
                .executeQuery("DESCRIBE " + UserContract.TABLE);
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

        if (UserContract.COLS.length != describeQuery.size()) {
            fail("not same number of lines");
        }

        for (int i = 0; i < describeQuery.size(); i++) {
            if (!describeQuery.get(i).getField().equals(UserContract.COLS[i])) {
                fail("Column name do not match");
            }
        }
    }

    @Test
    public void testGetUserDaoCreateTableInsertWorking() {
        DbManager.getInstance().getUserDao().drop();
        DbManager.getInstance().getUserDao().create();
        try {
            DbManager.getInstance().getUserDao()
                    .insert(new User("jonny", "jonny", new SimpleDateFormat("yyyy/mm/dd").parse("1990/04/24")));
        } catch (Exception e) {
            fail("Insertion failure");
        }
    }

    @Test
    public void testGetUserDaoDropTableRemoved() throws SQLException {
        DbManager.getInstance().getUserDao().drop();
        ResultSet rs = DbOpenHelper.getInstance().getConn().createStatement().executeQuery("SHOW TABLES");
        while (rs.next()) {
            if (rs.getString(1).equals(UserContract.TABLE)) {
                fail("Table already exists");
            }
        }
    }

    @Test(expected = MySQLSyntaxErrorException.class)
    public void testGetUserDaoDropCannotInsert() throws SQLException, ParseException {
        DbManager.getInstance().getUserDao().drop();
        DbManager.getInstance().getUserDao()
                .insert(new User("jonny", "jonny", new SimpleDateFormat("yyyy/mm/dd").parse("1990/04/24")));
    }

    @Test
    public void testGetUserDaoDropCannotInsertGoodMessage() {
        DbManager.getInstance().getUserDao().drop();
        try {
            DbManager.getInstance().getUserDao()
                    .insert(new User("jonny", "jonny", new SimpleDateFormat("yyyy/mm/dd").parse("1990/04/24")));
        } catch (SQLException | ParseException e) {
            assertTrue(e.getMessage().equals("Table 'disco." + UserContract.TABLE + "' doesn't exist"));
        }
    }
}
