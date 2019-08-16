package com.poei_juillet_2019.mysql.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.poei_juillet_2019.mysql.database.DbOpenHelper;
import com.poei_juillet_2019.mysql.database.contracts.RoleContract;
import com.poei_juillet_2019.mysql.database.contracts.UserContract;
import com.poei_juillet_2019.mysql.entities.Role;
import com.poei_juillet_2019.mysql.entities.User;

public class RoleDao extends BaseDao<Role> {

    public RoleDao() {
        super(new RoleContract());
    }

    @Override
    protected void javaToSqlInsert(Role item, PreparedStatement ps) throws SQLException {
        ps.setString(2, item.getName());
    }

    @Override
    protected void javaToSqlUpdate(Role item, PreparedStatement ps) throws SQLException {
        ps.setString(1, item.getName());
        ps.setInt(2, item.getId());
    }

    @Override
    protected Role parseFromDbToJava(ResultSet rs) throws SQLException, ParseException {
        Role item = new Role();
        item.setId(rs.getInt(rs.findColumn(RoleContract.COL_ID)));
        item.setName(rs.getString(rs.findColumn(RoleContract.COL_NAME)));
        return item;
    }
}
