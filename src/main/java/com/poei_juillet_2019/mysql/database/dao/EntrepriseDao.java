package com.poei_juillet_2019.mysql.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import com.poei_juillet_2019.mysql.database.contracts.EntrepriseContract;
import com.poei_juillet_2019.mysql.database.contracts.RoleContract;
import com.poei_juillet_2019.mysql.entities.Entreprise;
import com.poei_juillet_2019.mysql.entities.Role;

public class EntrepriseDao extends BaseDao<Entreprise> {

    public EntrepriseDao() {
        super(new EntrepriseContract());
    }

    @Override
    protected void javaToSqlInsert(Entreprise item, PreparedStatement ps) throws SQLException {
        ps.setString(2, item.getNom());
        ps.setString(3, item.getAdresse());
        ps.setString(4, item.getType());
    }

    @Override
    protected void javaToSqlUpdate(Entreprise item, PreparedStatement ps) throws SQLException {
        ps.setString(1, item.getNom());
        ps.setString(2, item.getAdresse());
        ps.setString(3, item.getType());
        ps.setInt(4, item.getId());
    }

    @Override
    protected Entreprise parseFromDbToJava(ResultSet rs) throws SQLException, ParseException {
        Entreprise item = new Entreprise();

        item.setId(rs.getInt(rs.findColumn(EntrepriseContract.COL_ID)));
        item.setNom(rs.getString(rs.findColumn(EntrepriseContract.COL_NOM)));
        item.setAdresse(rs.getString(rs.findColumn(EntrepriseContract.COL_ADRESSE)));
        item.setType(rs.getString(rs.findColumn(EntrepriseContract.COL_TYPE)));

        return item;
    }

}
