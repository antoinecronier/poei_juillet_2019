package com.poei_juillet_2019.mysql.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.poei_juillet_2019.mysql.database.DbOpenHelper;
import com.poei_juillet_2019.mysql.database.contracts.UserContract;
import com.poei_juillet_2019.mysql.entities.User;

public class UserDao implements Dao {

    @Override
    public void create() {
        PreparedStatement ps = null;
        try {
            ps = DbOpenHelper.getInstance().getConn()
                .prepareStatement(UserContract.CREATE_TABLE);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void drop() {
        PreparedStatement ps = null;
        try {
            ps = DbOpenHelper.getInstance().getConn()
                .prepareStatement(UserContract.DROP_TABLE);
            ps.execute();
        } catch (SQLException e) {
            //e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void insert(Object obj) throws SQLException {
        if (obj instanceof User) {
            User item = (User) obj;

            SimpleDateFormat sdf = new java.text.SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
            String mySqlDateOfBirth = sdf.format(item.getDateOfBirth());

            String request = UserContract.INSERT();
            PreparedStatement ps = null;
            try {
                ps = DbOpenHelper.getInstance().getConn()
                    .prepareStatement(request);
                ps.setInt(1, item.getId());
                ps.setString(2, item.getFirstname());
                ps.setString(3, item.getLastname());
                ps.setString(4, mySqlDateOfBirth);
                ps.execute();
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw e;
                }
            }
        }
    }

    @Override
    public Integer update(Object obj) throws SQLException {
        Integer nbTupleChanged = null;
        if (obj instanceof User) {
            User item = (User) obj;

            SimpleDateFormat sdf = new java.text.SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
            String mySqlDateOfBirth = sdf.format(item.getDateOfBirth());

            String request = UserContract.UPDATE();
            PreparedStatement ps = null;
            try {
                ps = DbOpenHelper.getInstance().getConn()
                    .prepareStatement(request);
                ps.setString(1, item.getFirstname());
                ps.setString(2, item.getLastname());
                ps.setString(3, mySqlDateOfBirth);
                ps.setInt(4, item.getId());
                nbTupleChanged = ps.executeUpdate();
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw e;
                }
            }
        }
        return nbTupleChanged;
    }

    @Override
    public Integer delete(Object obj) {
        Integer nbTupleChanged = null;
        if (obj instanceof User) {
            User item = (User) obj;

            String request = UserContract.DELETE();
            PreparedStatement ps = null;
            try {
                ps = DbOpenHelper.getInstance().getConn()
                    .prepareStatement(request);
                ps.setInt(1, item.getId());
                nbTupleChanged = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return nbTupleChanged;
    }

    @Override
    public List<Object> select() {
        List<Object> result = new ArrayList<Object>();

        String request = UserContract.SELECTALL();
        PreparedStatement ps = null;
        try {
            ps = DbOpenHelper.getInstance().getConn()
                .prepareStatement(request);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(parseFromDbToJava(rs));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public Object select(int id) {
        User result = null;

        String request = UserContract.SELECT();
        PreparedStatement ps = null;
        try {
            ps = DbOpenHelper.getInstance().getConn()
                .prepareStatement(request);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User item = parseFromDbToJava(rs);
                result = item;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    private User parseFromDbToJava(ResultSet rs) throws SQLException, ParseException {
        User item = new User();
        item.setId(rs.getInt(rs.findColumn(UserContract.COL_ID)));
        item.setFirstname(rs.getString(rs.findColumn(UserContract.COL_FIRSTNAME)));
        item.setLastname(rs.getString(rs.findColumn(UserContract.COL_LASTNAME)));

//                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
//                String sqlDate = rs.getString(rs.findColumn(UserContract.COL_DATE_OF_BIRTH));
//                Date javaDate = sdf.parse(sqlDate);
//                item.setDateOfBirth( javaDate);

        item.setDateOfBirth(
                new SimpleDateFormat("YYYY-MM-DD hh:mm:ss")
                    .parse(rs.getString(rs.findColumn(UserContract.COL_DATE_OF_BIRTH))));
        return item;
    }

}
