package com.poei_juillet_2019.mysql.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

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
    public void insert(Object obj) {
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
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(Object obj) {
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
    }

    @Override
    public void delete(Object obj) {
        if (obj instanceof User) {
            User item = (User) obj;

            String request = UserContract.DELETE();
            PreparedStatement ps = null;
            try {
                ps = DbOpenHelper.getInstance().getConn()
                    .prepareStatement(request);
                ps.setInt(1, item.getId());
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
    }

    @Override
    public List<Object> select() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object select(int id) {
        // TODO Auto-generated method stub
        return null;
    }

}
