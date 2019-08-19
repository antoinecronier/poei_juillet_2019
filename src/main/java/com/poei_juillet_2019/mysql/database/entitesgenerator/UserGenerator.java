package com.poei_juillet_2019.mysql.database.entitesgenerator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.github.javafaker.Faker;
import com.poei_juillet_2019.mysql.database.DbManager;
import com.poei_juillet_2019.mysql.entities.Entreprise;
import com.poei_juillet_2019.mysql.entities.Role;
import com.poei_juillet_2019.mysql.entities.User;

public class UserGenerator {

    private List<Entreprise> entreprises = new ArrayList<>();
    private List<Role> roles = new ArrayList<>();

    private UserGenerator() {
        entreprises = DbManager.getInstance().getEntrepriseDao().select();
        roles = DbManager.getInstance().getRoleDao().select();
    }

    private static UserGenerator INSTANCE = null;

    public static UserGenerator getInstance() {
        if (INSTANCE == null) {
            synchronized (UserGenerator.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserGenerator();
                }
            }
        }
        return INSTANCE;
    }

    Faker faker = new Faker(Locale.FRENCH);
    private List<User> datas = new ArrayList<User>();

    public void refresh() {
        entreprises = DbManager.getInstance().getEntrepriseDao().select();
        roles = DbManager.getInstance().getRoleDao().select();
    }

    public List<User> generateDatas() throws SQLException {
        return generateDatas(faker.random().nextInt(100));
    }

    public List<User> generateDatas(int nb) throws SQLException {
        List<User> result = new ArrayList<>();

        int i = 0;
        while (i < nb) {

            User user = new User(faker.name().firstName(), faker.name().lastName(), faker.date().birthday());

            if (entreprises.size() > 0 && faker.random().nextInt(100) != 99) {
                user.setEntreprise(entreprises.get(faker.random().nextInt(0, entreprises.size() - 1)));
            }

            if (roles.size() > 0 && faker.random().nextInt(100) != 99) {
                user.setRole(roles.get(faker.random().nextInt(0, roles.size() - 1)));
            }

            result.add(user);

            i++;
        }
        return result;
    }

    public void generateAndInsertDatas() throws SQLException {
        generateAndInsertDatas(faker.random().nextInt(100));
    }

    public void generateAndInsertDatas(int nb) throws SQLException {
        for (User user : generateDatas(nb)) {
            DbManager.getInstance().getUserDao().insert(user);
            datas.add(user);
        }
    }

    public void generateAndInsertDatasDroppingTable() throws SQLException {
        generateAndInsertDatasDroppingTable(faker.random().nextInt(100));
    }

    public void generateAndInsertDatasDroppingTable(int nb) throws SQLException {
        DbManager.getInstance().getUserDao().drop();
        DbManager.getInstance().getUserDao().create();

        generateAndInsertDatas(nb);
    }

    public void deleteDatas() {
        for (User user : datas) {
            DbManager.getInstance().getUserDao().delete(user);
        }
    }
}
