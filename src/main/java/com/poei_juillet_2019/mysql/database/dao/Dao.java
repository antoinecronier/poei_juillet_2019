package com.poei_juillet_2019.mysql.database.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao {
    public void create();

    public void drop();

    public void insert(Object obj) throws SQLException;

    public Integer update(Object obj) throws SQLException;

    public Integer delete(Object obj);

    public List<Object> select();

    public Object select(int id);
}
