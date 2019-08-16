package com.poei_juillet_2019.mysql.database.contracts;

public class UserContract extends BaseContract {

    public final static String TABLE = "User";

    public final static String COL_ID = "id";
    public final static String COL_FIRSTNAME = "firstname";
    public final static String COL_LASTNAME = "lastname";
    public final static String COL_DATE_OF_BIRTH = "dateOfBirth";
    public final static String COL_FK_ID_ROLE = "role_id";
    public final static String COL_FK_ID_ENTREPRISE = "entreprise_id";

    public final static String[] COLS = new String[] { COL_ID, COL_FIRSTNAME, COL_LASTNAME, COL_DATE_OF_BIRTH,
            COL_FK_ID_ROLE, COL_FK_ID_ENTREPRISE };

    public final static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE + "(" + COL_ID
            + " int NOT NULL AUTO_INCREMENT PRIMARY KEY," + COL_FIRSTNAME + " VARCHAR(255) NOT NULL," + COL_LASTNAME
            + " VARCHAR(255) NOT NULL," + COL_DATE_OF_BIRTH + " DATETIME NOT NULL," + COL_FK_ID_ROLE + " int,"
            + COL_FK_ID_ENTREPRISE + " int," +
            "CONSTRAINT FK_user_id_role FOREIGN KEY (" + COL_FK_ID_ROLE
            + ") REFERENCES " + RoleContract.TABLE + " (" + RoleContract.COL_ID + "),"
            + "CONSTRAINT FK_user_id_entreprise FOREIGN KEY (" + COL_FK_ID_ENTREPRISE
            + ") REFERENCES " + EntrepriseContract.TABLE + " (" + EntrepriseContract.COL_ID + ")" + ")";

    public UserContract() {
        super(TABLE, COL_ID, COLS, CREATE_TABLE);
    }
}
