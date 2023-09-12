package Hughes.termscheduler.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "Courses")
public class Courses {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    private String title;
    private String start;
    private String end;
    //private enum status {notStarted, inProgress, finished}
    private int instructorID;
    private int assessmentID;

    public Courses(int ID, String title, String start, String end, int instructorID, int assessmentID) {
        this.ID = ID;
        this.title = title;
        this.start = start;
        this.end = end;
        this.instructorID = instructorID;
        this.assessmentID = assessmentID;
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

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }
}
