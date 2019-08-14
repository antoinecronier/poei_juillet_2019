package com.poei_juillet_2019.mysql.test.database;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.poei_juillet_2019.mysql.database.DbManager;
import com.poei_juillet_2019.mysql.database.DbOpenHelper;
import com.poei_juillet_2019.mysql.database.contracts.UserContract;
import com.poei_juillet_2019.mysql.entities.User;
import com.poei_juillet_2019.mysql.test.database.utils.DescribeQuery;

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
    public void testGetRoleDao() {
        assertNotNull(DbManager.getInstance().getRoleDao());
    }

}
