package data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

public class Task implements Serializable {
    private final int ID;
    private int ID_TYPE;

    private final List<String> type_name = List.of(
            "ramassage oeuf ",
            "Eau au poule",
            "nettoyage poulailler"
    );

    private long DATE;
    private String PLACE;

    private float TIME;
    private boolean VALID;

    /**
     *  Constructor
     **/

    public Task(int ID, int ID_TYPE, long DATE, String PLACE, float TIME, int VALID) {
        this.ID = ID;
        this.ID_TYPE = ID_TYPE;

        this.DATE = DATE;

        this.PLACE = PLACE;
        this.TIME = TIME;

        if (VALID == 1) this.VALID = true;
        else this.VALID = false;
    }
    public Task(int ID_TYPE, long DATE, String PLACE, float TIME) {
        this.ID = -1;
        this.ID_TYPE = ID_TYPE;

        this.DATE = DATE;

        this.PLACE = PLACE;
        this.TIME = TIME;

        this.VALID = false;
    }

    /**
     *  Getter and Setter
     **/

    public int getID() {
        return ID;
    }

    public int getID_TYPE() {
        return ID_TYPE;
    }

    public void setID_TYPE(int ID_TYPE) {
        this.ID_TYPE = ID_TYPE;
    }

    public String getName() {
        return type_name.get(ID_TYPE);
    }

    public long getDATE() {
        return DATE;
    }
    public Calendar getDATE_byCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(DATE);
        return calendar;
    }

    public void setDATE(long DATE) {
        this.DATE = DATE;
    }

    public String getPLACE() {
        return PLACE;
    }

    public void setPLACE(String PLACE) {
        this.PLACE = PLACE;
    }


    public float getTIME() {
        return TIME;
    }

    public void setTIME(float TIME) {
        this.TIME = TIME;
    }

    public int isVALID() {
        if (this.VALID) return 1;
        return 0;
    }

    public void setVALID(int VALID) {
        if (VALID == 1) this.VALID = true;
        else this.VALID = false;
    }
}
