package com.poei_juillet_2019.mysql.database.entitesgenerator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.github.javafaker.Faker;
import com.poei_juillet_2019.mysql.database.DbManager;
import com.poei_juillet_2019.mysql.entities.Role;

public class RoleGenerator {

    private RoleGenerator()
    {}

    private static RoleGenerator INSTANCE = null;

    public static RoleGenerator getInstance()
    {
        if (INSTANCE == null)
        {
            synchronized(RoleGenerator.class)
            {
                if (INSTANCE == null)
                {   INSTANCE = new RoleGenerator();
                }
            }
        }
        return INSTANCE;
    }

    private Faker faker = new Faker(Locale.FRENCH);
    private List<Role> datas = new ArrayList<Role>();

    public List<Role> generateDatas() throws SQLException
    {
        return generateDatas(faker.random().nextInt(100));
    }

    public List<Role> generateDatas(int nb) throws SQLException
    {
        List<Role> result = new ArrayList<>();
        List<String> profession = new ArrayList<String>();

        int i = 0;
        while (i < nb) {
            String prof = faker.company().profession();
            if (!profession.contains(prof)) {
                profession.add(prof);

                Role role = new Role(prof);
                result.add(role);

                i++;
            }
        }
        return result;
    }

    public void generateAndInsertDatas() throws SQLException {
        generateAndInsertDatas(faker.random().nextInt(100));
    }

    public void generateAndInsertDatas(int nb) throws SQLException {
        for (Role role : generateDatas(nb)) {
            DbManager.getInstance().getRoleDao().insert(role);
            datas.add(role);
        }
    }

    public void generateAndInsertDatasDroppingTable() throws SQLException {
        generateAndInsertDatasDroppingTable(faker.random().nextInt(100));
    }

    public void generateAndInsertDatasDroppingTable(int nb) throws SQLException {
        DbManager.getInstance().getRoleDao().drop();
        DbManager.getInstance().getRoleDao().create();

        generateAndInsertDatas(nb);
    }

    public void deleteDatas() {
        for (Role role : datas) {
            DbManager.getInstance().getRoleDao().delete(role);
        }
    }
}
