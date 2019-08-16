package com.poei_juillet_2019.mysql.database.contracts;

public class RoleContract extends BaseContract {

    public final static String TABLE = "Role";

    public final static String COL_ID = "id";
    public final static String COL_NAME = "name";

    public final static String[] COLS = new String[] {
            COL_ID,
            COL_NAME
    };

    public final static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+ TABLE +"(" +
            COL_ID + " int NOT NULL AUTO_INCREMENT PRIMARY KEY," +
            COL_NAME + " VARCHAR(255) NOT NULL" +
            ")";

    public RoleContract() {
        super(TABLE, COL_ID, COLS, CREATE_TABLE);
    }
}
