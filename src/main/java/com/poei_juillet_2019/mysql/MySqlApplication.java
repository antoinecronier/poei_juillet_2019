package com.poei_juillet_2019.mysql;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.mysql.fabric.xmlrpc.base.Data;
import com.poei_juillet_2019.mysql.database.DbOpenHelper;
import com.poei_juillet_2019.mysql.database.UserDao;
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
     */
    public static void main(String[] args) throws ParseException {

        UserDao dao = new UserDao();
        dao.drop();
        dao.create();
        User user = new User("raoul", "michel", new SimpleDateFormat("yyyy/mm/dd").parse("1990/04/24"));
        dao.insert(user);

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
