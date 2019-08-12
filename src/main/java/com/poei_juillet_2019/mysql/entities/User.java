package com.poei_juillet_2019.mysql.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.poei_juillet_2019.mysql.database.DbOpenHelper;

public class User {

    private int id;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String insert() {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
        String mySqlDateOfBirth = sdf.format(this.dateOfBirth);
        return "INSERT INTO User VALUES (null, '" + this.firstname + "','" + this.lastname + "','" + mySqlDateOfBirth + "')";
    }

    public void playInsert() throws SQLException {
        DbOpenHelper.getInstance().getConn().createStatement().execute(this.insert());
    }

    public String delete() {
        return "DELETE FROM User WHERE id = " + this.id + ";";
    }

    public void playDelete() throws SQLException {
        DbOpenHelper.getInstance().getConn().createStatement().execute(this.delete());
    }

    public String update(String firstname, String lastname, Date dateOfBirth) {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mySqlDateOfBirth = sdf.format(dateOfBirth);
        return "UPDATE User SET firstname = '" + firstname + "', lastname = '" + lastname + "', dateOfBirth = '"
                + mySqlDateOfBirth + "' WHERE id = " + this.id + ";";
    }

    public void playUpdate(String firstname, String lastname, Date dateOfBirth) throws SQLException {
        DbOpenHelper.getInstance().getConn().createStatement().execute(this.update(firstname, lastname, dateOfBirth));
    }

    public static String create() {
        return "CREATE TABLE IF NOT EXISTS User(" +
                "id int NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "firstname VARCHAR(255) NOT NULL," +
                "lastname VARCHAR(255) NOT NULL," +
                "dateOfBirth DATETIME NOT NULL" +
                ")";
    }

    public static void playCreate() throws SQLException {
        DbOpenHelper.getInstance().getConn().createStatement().execute(create());
    }

    public static String drop() {
        return "DROP IF EXISTS TABLE User;";
    }

    public static void playDrop() throws SQLException {
        DbOpenHelper.getInstance().getConn().createStatement().execute(drop());
    }

    public void select() throws SQLException {
        ResultSet rs = DbOpenHelper.getInstance().getConn().createStatement().executeQuery("SELECT * FROM User;");
        while (rs.next()) {
            StringBuilder builder = new StringBuilder();
            builder.append(rs.getInt(1));
            builder.append(rs.getString(2));
            builder.append(rs.getString(3));
            builder.append(rs.getString(4));
            System.out.println(builder.toString());
        }
    }

    public void select(int id) throws SQLException {
        ResultSet rs = DbOpenHelper.getInstance().getConn().createStatement()
                .executeQuery("SELECT * FROM User WHERE id =" + id + " ;");
        while (rs.next()) {
            StringBuilder builder = new StringBuilder();
            builder.append(rs.getInt(1));
            builder.append(rs.getString(2));
            builder.append(rs.getString(3));
            builder.append(rs.getString(4));
            System.out.println(builder.toString());
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", dateOfBirth=" + dateOfBirth
                + "]";
    }

    public User() {
    }

    public User(String firstname, String lastname, Date dateOfBirth) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
    }
}
