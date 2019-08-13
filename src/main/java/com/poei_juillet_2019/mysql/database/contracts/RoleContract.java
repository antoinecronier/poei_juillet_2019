package com.poei_juillet_2019.mysql.database.contracts;

public class RoleContract {

    public final static String TABLE = "Role";

    public final static String COL_ID = "id";
    public final static String COL_NAME = "name";

    public final static String ALIASED_COL_ID = TABLE + "." + COL_ID;
    public final static String ALIASED_COL_NAME = TABLE + "." + COL_NAME;

    public final static String[] COLS = new String[] {
            COL_ID,
            COL_NAME
    };

    public final static String[] ALIASED_COLS = new String[] {
            ALIASED_COL_ID,
            ALIASED_COL_NAME
    };

    public final static String INSERT() {
        StringBuilder result = new StringBuilder();
        result.append("INSERT INTO " + TABLE + "(");

        int j = 0;
        for (; j < COLS.length - 1; j++) {
            result.append(COLS[j] +",");
        }
        result.append(COLS[j]);

        result.append(") VALUES(");

        for (int i = 0; i < COLS.length - 1; i++) {
            result.append("?,");
        }
        result.append("?");

        result.append(")");
        return result.toString();
    }

    public final static String UPDATE() {
        StringBuilder result = new StringBuilder();
        result.append("UPDATE " + TABLE + " SET ");

        int j = 1;
        for (; j < COLS.length - 1; j++) {
            result.append(COLS[j] + " = ?,");
        }
        result.append(COLS[j] + " = ? ");

        result.append(" WHERE " + COL_ID + " = ?");

        return result.toString();
    }

    public final static String DELETE() {
        StringBuilder result = new StringBuilder();

        result.append("DELETE FROM " + TABLE + " WHERE " + COL_ID + " = ?");

        return result.toString();
    }

    public final static String SELECTALL() {
        StringBuilder result = selectBase();

        return result.toString();
    }

    public final static String SELECT() {
        StringBuilder result = selectBase();
        result.append(" WHERE " + COL_ID + " = ?");

        return result.toString();
    }

    private static StringBuilder selectBase() {
        StringBuilder result = new StringBuilder();
        result.append("SELECT ");

        int j = 0;
        for (; j < COLS.length - 1; j++) {
            result.append(COLS[j] + ",");
        }
        result.append(COLS[j]);
        result.append(" FROM " + TABLE);
        return result;
    }

    public final static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+ TABLE +"(" +
            COL_ID + " int NOT NULL AUTO_INCREMENT PRIMARY KEY," +
            COL_NAME + " VARCHAR(255) NOT NULL" +
            ")";

    public final static String DROP_TABLE = "DROP TABLE " + TABLE;
}
