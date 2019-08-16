package com.poei_juillet_2019.mysql.database.contracts;

public class EntrepriseContract extends BaseContract {

    public final static String TABLE = "Entreprise";

    public final static String COL_ID = "id";
    public final static String COL_NOM = "nom";
    public final static String COL_ADRESSE = "adresse";
    public final static String COL_TYPE = "type";

    public final static String[] COLS = new String[] {
            COL_ID,
            COL_NOM,
            COL_ADRESSE,
            COL_TYPE
    };

    public final static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+ TABLE +"(" +
            COL_ID + " int NOT NULL AUTO_INCREMENT PRIMARY KEY," +
            COL_NOM + " VARCHAR(255) NOT NULL," +
            COL_ADRESSE + " VARCHAR(255) NOT NULL," +
            COL_TYPE + " VARCHAR(255) NOT NULL" +
            ")";

    public EntrepriseContract() {
        super(TABLE, COL_ID, COLS, CREATE_TABLE);
    }
}
