package src.main.java.com.rhomdev.api.data;

import java.io.Serializable;
import java.util.Map;

public class TaskFinish implements Serializable {
    private final int ID;
    private final Task TASK;

    private float TIME;
    private Worker EMPLOYEE;
    private Map<Produce, Integer> PRODUCTION;
    private float DATE;

    /**
     *  Constructor
     **/

    public TaskFinish(int ID, Task TASK, float TIME, Worker EMPLOYEE, float DATE) {
        this.ID = ID;
        this.TASK = TASK;
        this.TIME = TIME;
        this.EMPLOYEE = EMPLOYEE;
        this.DATE = DATE;
    }

    /**
     *  Getter and Setter
     **/

    public int getID() {
        return ID;
    }

    public Task getTASK() {
        return TASK;
    }

    public float getTIME() {
        return TIME;
    }

    public void setTIME(float TIME) {
        this.TIME = TIME;
    }

    public Worker getEMPLOYEE() {
        return EMPLOYEE;
    }

    public void setEMPLOYEE(Worker EMPLOYEE) {
        this.EMPLOYEE = EMPLOYEE;
    }

    public float getDATE() {
        return DATE;
    }

    public void setDATE(float DATE) {
        this.DATE = DATE;
    }

    public Map<Produce, Integer> getPRODUCTION() {
        return PRODUCTION;
    }

    public void setPRODUCTION(Map<Produce, Integer> PRODUCTION) {
        this.PRODUCTION = PRODUCTION;
    }
}
