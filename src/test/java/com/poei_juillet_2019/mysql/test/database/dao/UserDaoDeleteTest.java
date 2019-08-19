//package com.poei_juillet_2019.mysql.test.database.dao;
//
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertTrue;
//
//import java.sql.SQLException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import com.poei_juillet_2019.mysql.database.DbManager;
//import com.poei_juillet_2019.mysql.entities.User;
//
//public class UserDaoDeleteTest {
//
//    @Before
//    public void dropCreate() {
//        DbManager.getInstance().getUserDao().drop();
//        DbManager.getInstance().getUserDao().create();
//    }
//
//    @Test
//    public void testUserDaoDeleteSimple() throws SQLException, ParseException {
//        User user = new User("Jean","Michel", new SimpleDateFormat("yyyy/mm/dd").parse("1990/04/24"));
//        DbManager.getInstance().getUserDao().insert(user);
//
//        user.setId(1);
//        DbManager.getInstance().getUserDao().delete(user);
//
//        assertNull(DbManager.getInstance().getUserDao().select(1));
//    }
//
////    @Test
////    public void testUserDaoDeleteMultiple() throws SQLException, ParseException {
////        boolean statut = false;
////
////        for (int i = 1; i <= 10; i++) {
////            User user = new User("Jean","Michel", new SimpleDateFormat("yyyy/mm/dd").parse("1990/04/24"));
////            user.setId(i);
////            DbManager.getInstance().getUserDao().insert(user);
////
////            if (i == 5) {
////                DbManager.getInstance().getUserDao().delete(user);
////            }
////        }
////
////        for (int i = 1; i <= 10; i++) {
////            if (i == 5 && DbManager.getInstance().getUserDao().select(i) == null) {
////                statut = true;
////            }
////        }
////
////        assertTrue(statut);
////    }
//
//    @Test
//    public void testUserDaoDeleteMultiple2() throws SQLException, ParseException {
//        boolean statut = false;
//
//        for (int i = 17; i <= 102; i += 17) {
//            User user = new User("Jean","Michel", new SimpleDateFormat("yyyy/mm/dd").parse("1990/04/24"));
//            user.setId(i);
//            DbManager.getInstance().getUserDao().insert(user);
//
//            if (i == 51) {
//                DbManager.getInstance().getUserDao().delete(user);
//            }
//        }
//
//        for (int i = 17; i <= 102; i += 17) {
//            if (i == 51 && DbManager.getInstance().getUserDao().select(i) == null) {
//                statut = true;
//            }
//        }
//
//        assertTrue(statut);
//    }
//
//    @Test
//    public void testUserDaoDeleteNullMultiple() throws SQLException, ParseException {
//        int statut = 0;
//
//        for (int i = 17; i <= 102; i += 17) {
//            User user = new User("Jean","Michel", new SimpleDateFormat("yyyy/mm/dd").parse("1990/04/24"));
//            user.setId(i);
//            DbManager.getInstance().getUserDao().insert(user);
//
//            if (i == 102) {
//              DbManager.getInstance().getUserDao().delete(user);
//              statut = DbManager.getInstance().getUserDao().delete(user);
//            }
//        }
//
//        assertEquals(0, statut);
//    }
//
//    @AfterClass
//    public static void dropAfter() {
//        DbManager.getInstance().getUserDao().drop();
//        DbManager.getInstance().getUserDao().create();
//    }
//}
