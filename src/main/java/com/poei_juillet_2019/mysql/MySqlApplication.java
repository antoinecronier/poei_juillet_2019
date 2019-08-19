package com.poei_juillet_2019.mysql;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import com.github.javafaker.Faker;
import com.poei_juillet_2019.mysql.database.DbManager;
import com.poei_juillet_2019.mysql.database.entitesgenerator.EntrepriseGenerator;
import com.poei_juillet_2019.mysql.database.entitesgenerator.RoleGenerator;
import com.poei_juillet_2019.mysql.database.entitesgenerator.UserGenerator;
import com.poei_juillet_2019.mysql.entities.Entreprise;
import com.poei_juillet_2019.mysql.entities.Role;
import com.poei_juillet_2019.mysql.entities.User;

/**
 *
 * @author antoine.cronier
 *
 */
public final class MySqlApplication {

    private MySqlApplication() {

    }

    /**
     *
     * @param args
     * @throws ParseException
     * @throws SQLException
     */
    public static void main(String[] args) throws ParseException, SQLException {

        testGenerate();

//        Faker faker = new Faker(Locale.FRENCH);
//
//        List<String> professions = new ArrayList<String>();
//        int i = 0;
//        while (i < 10) {
//            String prof = faker.company().profession();
//            if (!professions.contains(prof)) {
//                professions.add(prof);
//                i++;
//            }
//        }
//
//        DbManager.getInstance().getRoleDao().drop();
//        DbManager.getInstance().getRoleDao().create();
//
//        List<Role> roles = new ArrayList<>();
//        for (String prof : professions) {
//            Role role = new Role(prof);
//            roles.add(DbManager.getInstance().getRoleDao().insert(role));
//        }
//
//        for (Role role : DbManager.getInstance().getRoleDao().select()) {
//            System.out.println(role);
//        }
//
//        DbManager.getInstance().getEntrepriseDao().drop();
//        DbManager.getInstance().getEntrepriseDao().create();
//
//        List<String> companies = new ArrayList<String>();
//        List<Entreprise> entreprises = new ArrayList<Entreprise>();
//
//        i = 0;
//        while (i < 10) {
//            String comp = faker.company().name();
//            if (!companies.contains(comp)) {
//                companies.add(comp);
//
//                Entreprise entreprise = new Entreprise(comp, faker.address().streetAddress(),
//                        faker.company().industry());
//                entreprises.add(DbManager.getInstance().getEntrepriseDao().insert(entreprise));
//
//                i++;
//            }
//        }
//
//        for (Entreprise entreprise : DbManager.getInstance().getEntrepriseDao().select()) {
//            System.out.println(entreprise);
//        }
//
//
//        DbManager.getInstance().getUserDao().drop();
//        DbManager.getInstance().getUserDao().create();
//
//        i = 0;
//        while (i < 10000) {
//            User user = new User(faker.name().firstName(), faker.name().lastName(), faker.date().birthday());
//            user.setEntreprise(entreprises.get(faker.random().nextInt(0, entreprises.size()-1)));
//            user.setRole(roles.get(faker.random().nextInt(0, roles.size()-1)));
//            DbManager.getInstance().getUserDao().insert(user);
//
//            i++;
//        }
//
//        for (User user : DbManager.getInstance().getUserDao().select()) {
//            System.out.println(user);
//        }

//        DbManager.getInstance().getRoleDao().drop();
//        DbManager.getInstance().getRoleDao().create();
//        for (int i = 0; i < 10; i++) {
//            Role role = new Role("role"+i);
//            DbManager.getInstance().getRoleDao().insert(role);
//        }
//
//        for (Role role : DbManager.getInstance().getRoleDao().select()) {
//            System.out.println(role);
//        }

//        roleTests();
//        entrepriseTests();
//        userTests();
        /*
         * try { User.playDrop(); User.playCreate(); User u1 = new
         * User("antoine","cronier", new
         * SimpleDateFormat("yyyy/mm/dd").parse("1990/04/24")); u1.playInsert();
         * u1.playInsert(); u1.playInsert(); u1.select();
         * System.out.println("---------------"); u1.setId(1); u1.playDelete();
         * u1.select(); System.out.println("---------------"); u1.setId(2);
         * u1.playUpdate("jean", "michel", new
         * SimpleDateFormat("yyyy/mm/dd").parse("1999/04/24")); u1.select();
         * System.out.println("---------------"); } catch (ParseException e1) {
         * e1.printStackTrace(); } catch (SQLException e) { e.printStackTrace(); }
         *
         *
         * System.out.println("Hello World"); try { ResultSet rs =
         * DbOpenHelper.getInstance().getConn().createStatement().
         * executeQuery("SHOW TABLES;"); while (rs.next()) {
         * System.out.println(rs.getString(1)); } } catch (SQLException e) { // TODO
         * Auto-generated catch block e.printStackTrace(); }
         */

//        Class<User> klazz = User.class;
//        for (Field field : klazz.getFields()) {
//            System.out.println(field);
//        }
//
//        System.out.println(klazz.getSuperclass().getName());
//        System.out.println(klazz.getClass().getName());
//
//        for (Method method : klazz.getMethods()) {
//            System.out.println(method.getName());
//        }
//
//        System.out.println(fielder(new User("raoul", "michel", new SimpleDateFormat("yyyy/mm/dd").parse("1990/04/24"))));
    }

    private static void testGenerate() throws SQLException, ParseException {
        DbManager.getInstance().getUserDao().drop();

        EntrepriseGenerator.getInstance().generateAndInsertDatasDroppingTable(10);
        RoleGenerator.getInstance().generateAndInsertDatasDroppingTable(10);
        UserGenerator.getInstance().generateAndInsertDatasDroppingTable(1000);

        for (Entreprise entreprise : DbManager.getInstance().getEntrepriseDao().select()) {
            System.out.println(entreprise);
        }

        System.out.println("--------------------------");

        for (Role role : DbManager.getInstance().getRoleDao().select()) {
            System.out.println(role);
        }

        System.out.println("--------------------------");

        for (User user : DbManager.getInstance().getUserDao().select()) {
            System.out.println(user);
        }

        System.out.println("--------------------------");

        UserGenerator.getInstance().generateAndInsertDatasDroppingTable(100);
        DbManager.getInstance().getUserDao()
                .insert(new User("test", "test1", new SimpleDateFormat("yyyy/mm/dd").parse("1990/04/24")));
        for (User user : DbManager.getInstance().getUserDao().select()) {
            System.out.println(user);
        }

        UserGenerator.getInstance().deleteDatas();
        EntrepriseGenerator.getInstance().deleteDatas();
        RoleGenerator.getInstance().deleteDatas();
    }

    private static void entrepriseTests() throws SQLException {
        DbManager.getInstance().getEntrepriseDao().drop();
        DbManager.getInstance().getEntrepriseDao().create();
        Entreprise entreprise = new Entreprise("test1", "test11", "test111");
        DbManager.getInstance().getEntrepriseDao().insert(entreprise);
        // DbManager.getInstance().getEntrepriseDao().insert(entreprise);
        Entreprise entreprise1 = new Entreprise("test1", "test11", "test111");
        DbManager.getInstance().getEntrepriseDao().insert(entreprise1);
        entreprise.setId(1);
        DbManager.getInstance().getEntrepriseDao().delete(entreprise);
        entreprise.setId(2);
        entreprise.setNom("jean");
        DbManager.getInstance().getEntrepriseDao().update(entreprise);

        for (Object obj : DbManager.getInstance().getEntrepriseDao().select()) {
            System.out.println(obj.toString());
        }

        System.out.println(DbManager.getInstance().getEntrepriseDao().select(2));
    }

    private static void roleTests() throws SQLException {
        DbManager.getInstance().getRoleDao().drop();
        DbManager.getInstance().getRoleDao().create();
        Role role = new Role("role1");
        DbManager.getInstance().getRoleDao().insert(role);
        // DbManager.getInstance().getRoleDao().insert(role);
        Role role1 = new Role("Role2");
        DbManager.getInstance().getRoleDao().insert(role1);
        role.setId(1);
        DbManager.getInstance().getRoleDao().delete(role);
        role.setId(2);
        role.setName("jean");
        DbManager.getInstance().getRoleDao().update(role);

        for (Object obj : DbManager.getInstance().getRoleDao().select()) {
            System.out.println(obj.toString());
        }

        System.out.println(DbManager.getInstance().getRoleDao().select(2));
    }

//    private static void userTests() throws ParseException, SQLException {
//        DbManager.getInstance().getUserDao().drop();
//        DbManager.getInstance().getUserDao().create();
//        User user = new User("raoul", "michel", new SimpleDateFormat("yyyy/mm/dd").parse("1990/04/24"));
//        DbManager.getInstance().getUserDao().insert(user);
//        // DbManager.getInstance().getUserDao().insert(user);
//        User user1 = new User("rjsdfojdsojf", "sdfsdfsdf", new SimpleDateFormat("yyyy/mm/dd").parse("1980/04/24"));
//        DbManager.getInstance().getUserDao().insert(user1);
//        // user.setId(1);
//        DbManager.getInstance().getUserDao().delete(user);
//        user.setId(2);
//        user.setFirstname("jean");
//        user.setEntreprise(new Entreprise());
//        user.getEntreprise().setId(2);
//        user.setRole(new Role());
//        user.getRole().setId(2);
//        DbManager.getInstance().getUserDao().update(user);
//
//        for (Object obj : DbManager.getInstance().getUserDao().select()) {
//            System.out.println(obj.toString());
//        }
//
//        System.out.println(DbManager.getInstance().getUserDao().select(2));
//
//        System.out.println("-----------------------------------");
//        User userTest = new User("hervé", "yoyo", new SimpleDateFormat("yyyy/mm/dd").parse("1980/04/24"),
//                new Role("role1"), new Entreprise("entrepriseTest", "default adresse", "type1"));
//        DbManager.getInstance().getUserDao().insert(userTest);
//
//        for (Object obj : DbManager.getInstance().getUserDao().select()) {
//            System.out.println(obj.toString());
//        }
//    }

    public static <T> T createContentsWithId(Long id, Class<T> clazz) {
        try {
            return clazz.getConstructor(Long.class).newInstance(id);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, Object> fielder(Object bean) {
        try {
            return Arrays.asList(Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors())
                    .stream()
                    // filter out properties with setters only
                    .filter(pd -> Objects.nonNull(pd.getReadMethod())).collect(Collectors.toMap(
                            // bean property name
                            PropertyDescriptor::getName, pd -> { // invoke
                                                                    // method
                                                                    // to
                                                                    // get
                                                                    // value
                                try {
                                    Object test = pd.getReadMethod().invoke(bean);
                                    if (test == null) {
                                        test = "";
                                    }
                                    return test;
                                } catch (Exception e) {
                                    // replace this with better error
                                    // handling
                                    return e;
                                }
                            }));
        } catch (IntrospectionException e) {
            // and this, too
            return Collections.emptyMap();
        }
    }
}
