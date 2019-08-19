//package com.poei_juillet_2019.mysql.test.database.dao;
//
//import static org.junit.Assert.assertNotNull;
//
//import java.sql.SQLException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import com.poei_juillet_2019.mysql.database.DbManager;
//import com.poei_juillet_2019.mysql.entities.User;
//
//public class UserDaoTestBySelectId {
//
//    @Test
//    public void testGetSelectId() {
//        assertNotNull(DbManager.getInstance().getUserDao().select(1));
//    }
//
//    @BeforeClass
//    public static void testSelectIdMultiple() throws SQLException, ParseException {
//
//        DbManager.getInstance().getUserDao().drop();
//        DbManager.getInstance().getUserDao().create();
//
//        for (int i = 1; i <= 10; i++) {
//            User user = new User("raoul" + i, "michel" + i, new SimpleDateFormat("yyyy/mm/dd").parse("1990/04/24"));
//            user.setId(i);
//            DbManager.getInstance().getUserDao().insert(user);
//        }
//    }
//
//    @Test
//    public void testSelectIdTrue() {
//        boolean isOk = false;
//        User user = (User) DbManager.getInstance().getUserDao().select(1);
//        isOk = user.getFirstname().equals("raoul1");
//        isOk = isOk && user.getLastname().equals("michel1");
//        System.out.println(isOk);
//        System.out.println(user.getFirstname());
//    }
//
//
//    @Test
//    public void  testSelectIdFalse() {
//        boolean isNotOk = true;
//        User user = (User) DbManager.getInstance().getUserDao().select(1);
//        isNotOk = user.getFirstname().equals("raoul2");
//        isNotOk = isNotOk && user.getLastname().equals("michel2");
//        System.out.println(isNotOk);
//    }
//
//}
