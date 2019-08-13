package com.poei_juillet_2019.mysql.database;

import com.poei_juillet_2019.mysql.entities.User;

public class DbManager {

    private UserDao userDao = new UserDao();

    /** Constructeur privé */
    private DbManager()
    {}

    /** Instance unique non préinitialisée */
    private static DbManager INSTANCE = null;

    /** Point d'accès pour l'instance unique du singleton */
    public static DbManager getInstance()
    {
        if (INSTANCE == null)
        {
            synchronized(DbManager.class)
            {
                if (INSTANCE == null)
                {   INSTANCE = new DbManager();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * @return the userDao
     */
    public UserDao getUserDao() {
        return userDao;
    }
}
