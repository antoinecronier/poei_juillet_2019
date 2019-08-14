package com.poei_juillet_2019.mysql.test.database.dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.poei_juillet_2019.mysql.database.DbManager;

public class RoleDaoUpdateTest {

    @BeforeClass
    public static void setupTests() {
        DbManager.getInstance().getRoleDao().create();
    }

    @Test
    public void test() {
        fail("Not yet implemented");
    }

}
