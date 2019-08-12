package com.poei_juillet_2019.mysql.database;

import java.util.List;

public interface Dao {
    public void create();

    public void drop();

    public void insert(Object obj);

    public void update(Object obj);

    public void delete(Object obj);

    public List<Object> select();

    public Object select(int id);
}
