package com.poei_juillet_2019.mysql.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.poei_juillet_2019.mysql.database.DbOpenHelper;
import com.poei_juillet_2019.mysql.database.contracts.RoleContract;
import com.poei_juillet_2019.mysql.entities.Role;

public class RoleDao implements Dao {

    @Override
    public void create() {
        PreparedStatement ps = null;
        try {
            ps = DbOpenHelper.getInstance().getConn()
                .prepareStatement(RoleContract.CREATE_TABLE);
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
                .prepareStatement(RoleContract.DROP_TABLE);
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
    public void insert(Object obj) throws SQLException {
        if (obj instanceof Role) {
            Role item = (Role) obj;

            String request = RoleContract.INSERT();
            PreparedStatement ps = null;
            try {
                ps = DbOpenHelper.getInstance().getConn()
                    .prepareStatement(request);
                ps.setInt(1, item.getId());
                ps.setString(2, item.getName());
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
        if (obj instanceof Role) {
            Role item = (Role) obj;

            String request = RoleContract.UPDATE();
            PreparedStatement ps = null;
            try {
                ps = DbOpenHelper.getInstance().getConn()
                    .prepareStatement(request);
                ps.setString(1, item.getName());
                ps.setInt(2, item.getId());
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
        if (obj instanceof Role) {
            Role item = (Role) obj;

            String request = RoleContract.DELETE();
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

        String request = RoleContract.SELECTALL();
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
        Role result = null;

        String request = RoleContract.SELECT();
        PreparedStatement ps = null;
        try {
            ps = DbOpenHelper.getInstance().getConn()
                .prepareStatement(request);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role item = parseFromDbToJava(rs);
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

    private Role parseFromDbToJava(ResultSet rs) throws SQLException, ParseException {
        Role item = new Role();
        item.setId(rs.getInt(rs.findColumn(RoleContract.COL_ID)));
        item.setName(rs.getString(rs.findColumn(RoleContract.COL_NAME)));
        return item;
    }

}
