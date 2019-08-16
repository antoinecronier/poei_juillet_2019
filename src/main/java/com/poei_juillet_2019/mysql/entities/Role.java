package com.poei_juillet_2019.mysql.entities;

public class Role extends EntityDb{

    private String name;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public Role(String name) {
        super();
        this.name = name;
    }

    public Role(int id, String name) {
        super();
        this.setId(id);
        this.name = name;
    }

    public Role() {
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Role [id=" + this.getId() + ", name=" + name + "]";
    }

}
