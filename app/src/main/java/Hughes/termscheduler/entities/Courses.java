package Hughes.termscheduler.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.util.List;

@Entity(tableName = "Courses")
public class Courses {
    @PrimaryKey(autoGenerate = true)
    private int ID;

    private int termID;
    private String title;
    private String start;
    private String end;
    private String status;
    private String instructName;
    private String instructPhone;
    private String instuctorEmail;


    public Courses(int ID, int termID, String title, String start, String end, String status, String instructName, String instructPhone, String instuctorEmail) {
        this.ID = ID;
        this.termID = termID;
        this.title = title;
        this.start = start;
        this.end = end;
        this.status = status;
        this.instructName = instructName;
        this.instructPhone = instructPhone;
        this.instuctorEmail = instuctorEmail;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getInstructName() {
        return instructName;
    }

    public void setInstructName(String instructName) {
        this.instructName = instructName;
    }

    public String getInstructPhone() {
        return instructPhone;
    }

    public void setInstructPhone(String instructPhone) {
        this.instructPhone = instructPhone;
    }

    public String getInstuctorEmail() {
        return instuctorEmail;
    }

    public void setInstuctorEmail(String instuctorEmail) {
        this.instuctorEmail = instuctorEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

}
