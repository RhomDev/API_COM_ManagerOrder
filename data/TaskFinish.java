package data;

import java.io.Serializable;

public class TaskFinish implements Serializable {
    private final int ID;
    private final int ID_TASK;

    private float TIME;
    private int ID_EMPLOYEE;
    private float DATE;

    /**
     *  Constructor
     **/

    public TaskFinish(int ID, int ID_TASK, float TIME, int ID_EMPLOYEE, float DATE) {
        this.ID = ID;
        this.ID_TASK = ID_TASK;
        this.TIME = TIME;
        this.ID_EMPLOYEE = ID_EMPLOYEE;
        this.DATE = DATE;
    }

    /**
     *  Getter and Setter
     **/

    public int getID() {
        return ID;
    }

    public int getID_TASK() {
        return ID_TASK;
    }

    public float getTIME() {
        return TIME;
    }

    public void setTIME(float TIME) {
        this.TIME = TIME;
    }

    public int getID_EMPLOYEE() {
        return ID_EMPLOYEE;
    }

    public void setID_EMPLOYEE(int ID_EMPLOYEE) {
        this.ID_EMPLOYEE = ID_EMPLOYEE;
    }

    public float getDATE() {
        return DATE;
    }

    public void setDATE(float DATE) {
        this.DATE = DATE;
    }

}
