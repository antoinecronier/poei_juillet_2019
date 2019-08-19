package com.poei_juillet_2019.mysql.database.entitesgenerator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.github.javafaker.Faker;
import com.poei_juillet_2019.mysql.database.DbManager;
import com.poei_juillet_2019.mysql.entities.Entreprise;

public class EntrepriseGenerator {

    private EntrepriseGenerator()
    {}

    private static EntrepriseGenerator INSTANCE = null;

    public static EntrepriseGenerator getInstance()
    {
        if (INSTANCE == null)
        {
            synchronized(EntrepriseGenerator.class)
            {
                if (INSTANCE == null)
                {   INSTANCE = new EntrepriseGenerator();
                }
            }
        }
        return INSTANCE;
    }

    Faker faker = new Faker(Locale.FRENCH);
    private List<Entreprise> datas = new ArrayList<Entreprise>();

    public List<Entreprise> generateDatas() throws SQLException
    {
        return generateDatas(faker.random().nextInt(100));
    }

    public List<Entreprise> generateDatas(int nb) throws SQLException
    {
        List<Entreprise> result = new ArrayList<>();
        List<String> companies = new ArrayList<String>();

        int i = 0;
        while (i < nb) {
            String comp = faker.company().name();
            if (!companies.contains(comp)) {
                companies.add(comp);

                Entreprise entreprise = new Entreprise(comp, faker.address().streetAddress(),
                        faker.company().industry());
                result.add(entreprise);

                i++;
            }
        }
        return result;
    }

    public void generateAndInsertDatas() throws SQLException {
        generateAndInsertDatas(faker.random().nextInt(100));
    }

    public void generateAndInsertDatas(int nb) throws SQLException {
        for (Entreprise entreprise : generateDatas(nb)) {
            DbManager.getInstance().getEntrepriseDao().insert(entreprise);
            datas.add(entreprise);
        }
    }

    public void generateAndInsertDatasDroppingTable() throws SQLException {
        generateAndInsertDatasDroppingTable(faker.random().nextInt(100));
    }

    public void generateAndInsertDatasDroppingTable(int nb) throws SQLException {
        DbManager.getInstance().getEntrepriseDao().drop();
        DbManager.getInstance().getEntrepriseDao().create();

        generateAndInsertDatas(nb);
    }

    public void deleteDatas() {
        for (Entreprise entreprise : datas) {
            DbManager.getInstance().getEntrepriseDao().delete(entreprise);
        }
    }
}
