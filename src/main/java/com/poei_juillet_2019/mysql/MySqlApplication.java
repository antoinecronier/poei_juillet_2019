package com.poei_juillet_2019.mysql;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.poei_juillet_2019.mysql.database.DbManager;
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


        roleTests();
        entrepriseTests();
        userTests();
        /*
        try {
            User.playDrop();
            User.playCreate();
            User u1 = new User("antoine","cronier", new SimpleDateFormat("yyyy/mm/dd").parse("1990/04/24"));
            u1.playInsert();
            u1.playInsert();
            u1.playInsert();
            u1.select();
            System.out.println("---------------");
            u1.setId(1);
            u1.playDelete();
            u1.select();
            System.out.println("---------------");
            u1.setId(2);
            u1.playUpdate("jean", "michel", new SimpleDateFormat("yyyy/mm/dd").parse("1999/04/24"));
            u1.select();
            System.out.println("---------------");
        } catch (ParseException e1) {
            e1.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.println("Hello World");
        try {
            ResultSet rs = DbOpenHelper.getInstance().getConn().createStatement().executeQuery("SHOW TABLES;");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/


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

    private static void entrepriseTests() throws SQLException {
        DbManager.getInstance().getEntrepriseDao().drop();
        DbManager.getInstance().getEntrepriseDao().create();
        Entreprise entreprise = new Entreprise("test1","test11","test111");
        DbManager.getInstance().getEntrepriseDao().insert(entreprise);
        //DbManager.getInstance().getEntrepriseDao().insert(entreprise);
        Entreprise entreprise1 = new Entreprise("test1","test11","test111");
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
        //DbManager.getInstance().getRoleDao().insert(role);
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

    private static void userTests() throws ParseException, SQLException {
        DbManager.getInstance().getUserDao().drop();
        DbManager.getInstance().getUserDao().create();
        User user = new User("raoul", "michel", new SimpleDateFormat("yyyy/mm/dd").parse("1990/04/24"));
        DbManager.getInstance().getUserDao().insert(user);
        //DbManager.getInstance().getUserDao().insert(user);
        User user1 = new User("rjsdfojdsojf", "sdfsdfsdf", new SimpleDateFormat("yyyy/mm/dd").parse("1980/04/24"));
        DbManager.getInstance().getUserDao().insert(user1);
        //user.setId(1);
        DbManager.getInstance().getUserDao().delete(user);
        user.setId(2);
        user.setFirstname("jean");
        user.setEntreprise(new Entreprise());
        user.getEntreprise().setId(2);
        user.setRole(new Role());
        user.getRole().setId(2);
        DbManager.getInstance().getUserDao().update(user);

        for (Object obj : DbManager.getInstance().getUserDao().select()) {
            System.out.println(obj.toString());
        }

        System.out.println(DbManager.getInstance().getUserDao().select(2));

        System.out.println("-----------------------------------");
        User userTest = new User("hervé", "yoyo", new SimpleDateFormat("yyyy/mm/dd").parse("1980/04/24"), new Role("role1"), new Entreprise("entrepriseTest", "default adresse", "type1"));
        DbManager.getInstance().getUserDao().insert(userTest);

        for (Object obj : DbManager.getInstance().getUserDao().select()) {
            System.out.println(obj.toString());
        }
    }

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
            return Arrays
                    .asList(Introspector.getBeanInfo(bean.getClass(),
                            Object.class).getPropertyDescriptors())
                    .stream()
                    // filter out properties with setters only
                    .filter(pd -> Objects.nonNull(pd.getReadMethod()))
                    .collect(
                            Collectors.toMap(
                            // bean property name
                                    PropertyDescriptor::getName, pd -> { // invoke
                                                                            // method
                                                                            // to
                                                                            // get
                                                                            // value
                                        try {
                                            Object test = pd.getReadMethod()
                                                    .invoke(bean);
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
