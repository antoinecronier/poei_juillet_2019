package com.poei_juillet_2019.mysql.test.database.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.poei_juillet_2019.mysql.database.DbManager;
import com.poei_juillet_2019.mysql.entities.User;

public class UserDaoUpdateTest {

    private static final String CHANGED_DATA_FIRSTNAME = "toto1";
    private static final String CHANGED_DATA_LASTNAME = "toto2";
    private static final String CHANGED_DATA_DATE_OF_BIRTH = "2001-06-06";
    private List<User> users = new ArrayList<User>();

//    @Before
//    public void setupTests() throws SQLException {
//        DbManager.getInstance().getUserDao().drop();
//        DbManager.getInstance().getUserDao().create();
//
//        users.clear();
//        users.add(new User(1,"user1"));
//        users.add(new User(2,"user2"));
//        users.add(new User(3,"user3"));
//
//        for (User user : users) {
//            DbManager.getInstance().getUserDao().insert(user);
//        }
//    }
//
//    @Test
//    public void simpleUpdateCompare1() throws SQLException {
//        User user = users.get(0);
//        user.setName(CHANGED_DATA);
//
//        User dbUser = (User)DbManager.getInstance().getUserDao().select(1);
//        DbManager.getInstance().getUserDao().update(user);
//        User dbUserUpdated = (User)DbManager.getInstance().getUserDao().select(1);
//
//        assertTrue(dbUser.getId() == dbUserUpdated.getId() && !dbUser.getName().equals(dbUserUpdated.getName()) && dbUserUpdated.getName().equals(CHANGED_DATA));
//    }
//
//    @Test
//    public void simpleUpdateCompare2() throws SQLException {
//        User user = users.get(0);
//        user.setName(CHANGED_DATA);
//
//        DbManager.getInstance().getUserDao().update(user);
//        User dbUserUpdated = (User)DbManager.getInstance().getUserDao().select(1);
//
//        assertTrue(user.getId() == dbUserUpdated.getId() && user.getName().equals(dbUserUpdated.getName()));
//    }
//
//    @Test
//    public void simpleUpdateCompare3() {
//        User user = users.get(0);
//        User dbUser = (User)DbManager.getInstance().getUserDao().select(1);
//
//        assertTrue(user.getId() == dbUser.getId() && user.getName().equals(dbUser.getName()));
//    }
//
//    @Test(expected = MysqlDataTruncation.class)
//    public void updateMaxValExtended() throws SQLException {
//        User user = users.get(0);
//
//        StringBuilder data = new StringBuilder();
//        for (int i = 0; i < 256; i++) {
//            data.append("x");
//        }
//        user.setName(data.toString());
//
//        DbManager.getInstance().getUserDao().update(user);
//    }
//
//    @Test
//    public void updateMaxValOK() throws SQLException {
//        User user = users.get(0);
//
//        StringBuilder data = new StringBuilder();
//        for (int i = 0; i < 255; i++) {
//            data.append("x");
//        }
//        user.setName(data.toString());
//
//        DbManager.getInstance().getUserDao().update(user);
//    }
//
//    @Test
//    public void updateMinValOK() throws SQLException {
//        User user = users.get(0);
//        user.setName("");
//
//        DbManager.getInstance().getUserDao().update(user);
//    }
//
//    @Test(expected = MySQLIntegrityConstraintViolationException.class)
//    public void updateNullValKO() throws SQLException {
//        User user = users.get(0);
//        user.setName(null);
//
//        DbManager.getInstance().getUserDao().update(user);
//    }
//
//    @Test
//    public void updateWrongId() throws SQLException {
//        User user = users.get(0);
//        user.setId(4);
//        user.setName(CHANGED_DATA);
//
//        assertEquals(new Integer(0), DbManager.getInstance().getUserDao().update(user));
//    }
//
//    @Test
//    public void updateGoodId() throws SQLException {
//        User user = users.get(0);
//        user.setName(CHANGED_DATA);
//
//        assertEquals(new Integer(1), DbManager.getInstance().getUserDao().update(user));
//    }
}
