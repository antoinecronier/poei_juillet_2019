package com.poei_juillet_2019.mysql.test.database.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;

import com.poei_juillet_2019.mysql.database.DbManager;
import com.poei_juillet_2019.mysql.database.DbOpenHelper;
import com.poei_juillet_2019.mysql.database.contracts.UserContract;
import com.poei_juillet_2019.mysql.entities.User;

public class UserDaoInsertTest {

    @Test
     public void testInsertUser() throws ParseException, SQLException {
        User monUser = new User("Sylvain", "MIROUF", new SimpleDateFormat("yyyy/mm/dd").parse("1980/04/24" ));
        DbManager.getInstance().getUserDao().insert(monUser);

        assertFalse("Insert ok", false);
     }

    @Test
    public void testInsertJapaneseLetter() throws ParseException, SQLException {
        User monUser2 = new User("亜紀", "聡寛", new SimpleDateFormat("yyyy/mm/dd").parse("1980/04/24" ));
        DbManager.getInstance().getUserDao().insert(monUser2);

        assertTrue("Insert ok", true);
     }

    @Test
    public void testInsertFirstNameVar256() throws ParseException {

        String data = "";
        for (int i = 0; i < 256; i++) {
        data+= "x";
        }
        User monUser2 = new User(data, "聡寛", new SimpleDateFormat("yyyy/mm/dd").parse("1980/04/24" ));
        try {
            DbManager.getInstance().getUserDao().insert(monUser2);
        } catch (SQLException e) {
            e.getMessage();
        assertEquals(e.getMessage(), "Data truncation: Data too long for column 'firstname' at row 1");
        }
     }
    @Test
    public void testInsertLastNameVar256() throws ParseException {

        String data = "";
        for (int i = 0; i < 256; i++) {
        data+= "x";
        }
        User monUser2 = new User("聡寛", data , new SimpleDateFormat("yyyy/mm/dd").parse("1980/04/24" ));
        try {
            DbManager.getInstance().getUserDao().insert(monUser2);
        } catch (SQLException e) {
            e.getMessage();
        assertEquals(e.getMessage(), "Data truncation: Data too long for column 'lastname' at row 1");
        }
     }

    @Test
    public void testInsertWrongDate() throws SQLException {
        try {
            User monUser2 = new User("Sylvain", "MIROUF", new SimpleDateFormat("yyyy/mm/dd").parse("1980/24/32" ));
        } catch (ParseException e1) {
            e1.getMessage();
        assertEquals(e1.getMessage(), "Data truncation: Incorrect datetime value: " + e1.getMessage() + " for column 'dateOfBirth' at row 1 ");
        }
    }
}

