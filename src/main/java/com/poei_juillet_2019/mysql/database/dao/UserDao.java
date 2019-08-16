package com.poei_juillet_2019.mysql.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.poei_juillet_2019.mysql.database.DbManager;
import com.poei_juillet_2019.mysql.database.DbOpenHelper;
import com.poei_juillet_2019.mysql.database.contracts.BaseContract;
import com.poei_juillet_2019.mysql.database.contracts.UserContract;
import com.poei_juillet_2019.mysql.entities.EntityDb;
import com.poei_juillet_2019.mysql.entities.Entreprise;
import com.poei_juillet_2019.mysql.entities.Role;
import com.poei_juillet_2019.mysql.entities.User;

public class UserDao extends BaseDao<User> {

    public UserDao() {
        super(new UserContract());
    }

    @Override
    protected void javaToSqlInsert(User item, PreparedStatement ps) throws SQLException {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
        String mySqlDateOfBirth = sdf.format(item.getDateOfBirth());

        ps.setString(2, item.getFirstname());
        ps.setString(3, item.getLastname());
        ps.setString(4, mySqlDateOfBirth);
        if (item.getRole() != null) {
            item.setRole(DbManager.getInstance().getRoleDao().insert(item.getRole()));
            ps.setInt(5, item.getRole().getId());
        } else {
            ps.setNull(5, java.sql.Types.INTEGER);
        }
        if (item.getEntreprise() != null) {
            item.setEntreprise(DbManager.getInstance().getEntrepriseDao().insert(item.getEntreprise()));
            ps.setInt(6, item.getEntreprise().getId());
        } else {
            ps.setNull(6, java.sql.Types.INTEGER);
        }
    }

    @Override
    protected void javaToSqlUpdate(User item, PreparedStatement ps) throws SQLException {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
        String mySqlDateOfBirth = sdf.format(item.getDateOfBirth());

        ps.setString(1, item.getFirstname());
        ps.setString(2, item.getLastname());
        ps.setString(3, mySqlDateOfBirth);
        if (item.getRole() != null && item.getRole().getId() != null) {
            ps.setInt(4, item.getRole().getId());
        } else {
            ps.setNull(4, java.sql.Types.INTEGER);
        }
        if (item.getEntreprise() != null && item.getEntreprise().getId() != null) {
            ps.setInt(5, item.getEntreprise().getId());
        } else {
            ps.setNull(5, java.sql.Types.INTEGER);
        }
        ps.setInt(6, item.getId());
    }

    @Override
    protected User parseFromDbToJava(ResultSet rs) throws SQLException, ParseException {
        User item = new User();
        item.setId(rs.getInt(rs.findColumn(UserContract.COL_ID)));
        item.setFirstname(rs.getString(rs.findColumn(UserContract.COL_FIRSTNAME)));
        item.setLastname(rs.getString(rs.findColumn(UserContract.COL_LASTNAME)));

        item.setDateOfBirth(new SimpleDateFormat("YYYY-MM-DD hh:mm:ss")
                .parse(rs.getString(rs.findColumn(UserContract.COL_DATE_OF_BIRTH))));

        Integer roleId = rs.getInt(rs.findColumn(UserContract.COL_FK_ID_ROLE));
        Integer entrepriseId = rs.getInt(rs.findColumn(UserContract.COL_FK_ID_ENTREPRISE));
        if (roleId != null) {
            item.setRole(DbManager.getInstance().getRoleDao().select(roleId));
        }
        if (entrepriseId != null) {
            item.setEntreprise(DbManager.getInstance().getEntrepriseDao().select(entrepriseId));
        }

        return item;
    }
}
