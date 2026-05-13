package src.main.java.com.rhomdev.api.data;

import java.io.Serial;
import java.io.Serializable;

public class Worker implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    private final int ID;

    private String NAME;
    private String FIRSTNAME;

    private float HOURS;

    /**
     *  Constructor
     **/
    public Worker(int ID, String NAME, String FIRSTNAME, float HOURS) {
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

    @Override
    public String toString() {
        return String.format("{\"ID\": %d , \"name\": %s,\"firstname\": %s ,\"hours\": %f}", ID, NAME, FIRSTNAME, HOURS);
    }
}
