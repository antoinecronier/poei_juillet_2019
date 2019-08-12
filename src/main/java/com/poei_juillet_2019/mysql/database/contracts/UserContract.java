package com.poei_juillet_2019.mysql.database.contracts;

public class UserContract {

    public final static String TABLE = "User";

    public final static String COL_ID = "id";
    public final static String COL_FIRSTNAME = "firstname";
    public final static String COL_LASTNAME = "lastname";
    public final static String COL_DATE_OF_BIRTH = "dateOfBirth";

    public final static String ALIASED_COL_ID = TABLE + "." + COL_ID;
    public final static String ALIASED_COL_FIRSTNAME = TABLE + "." + COL_FIRSTNAME;
    public final static String ALIASED_COL_LASTNAME = TABLE + "." + COL_LASTNAME;
    public final static String ALIASED_COL_DATE_OF_BIRTH = TABLE + "." + COL_DATE_OF_BIRTH;

    public final static String[] COLS = new String[] {
            COL_ID,
            COL_FIRSTNAME,
            COL_LASTNAME,
            COL_DATE_OF_BIRTH
    };

    public final static String[] ALIASED_COLS = new String[] {
            ALIASED_COL_ID,
            ALIASED_COL_FIRSTNAME,
            ALIASED_COL_LASTNAME,
            ALIASED_COL_DATE_OF_BIRTH
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
            result.append("%s,");
        }
        result.append("%s");

        result.append(")");
        return result.toString();
    }

    public final static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+ TABLE +"(" +
            COL_ID + " int NOT NULL AUTO_INCREMENT PRIMARY KEY," +
            COL_FIRSTNAME + " VARCHAR(255) NOT NULL," +
            COL_LASTNAME + " VARCHAR(255) NOT NULL," +
            COL_DATE_OF_BIRTH + " DATETIME NOT NULL" +
            ")";

    public final static String DROP_TABLE = "DROP TABLE " + TABLE;
}
