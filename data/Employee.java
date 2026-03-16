package data;

import java.io.Serializable;

public class Employee implements Serializable {
    private final int ID;

    private String NAME;
    private String FIRSTNAME;

    private float HOURS;

    /**
     *  Constructor
     **/
    public Employee(int ID, String NAME, String FIRSTNAME, float HOURS) {
        this.ID = ID;
        this.NAME = NAME;
        this.FIRSTNAME = FIRSTNAME;
        this.HOURS = HOURS;
    }

    /**
     *  Getter and Setter
     **/

    public int getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getFIRSTNAME() {
        return FIRSTNAME;
    }

    public void setFIRSTNAME(String FIRSTNAME) {
        this.FIRSTNAME = FIRSTNAME;
    }

    public float getHOURS() {
        return HOURS;
    }

    public void setHOURS(float HOURS) {
        this.HOURS = HOURS;
    }
}
