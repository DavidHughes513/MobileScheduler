package Hughes.termscheduler.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.sql.Date;

@Entity(tableName = "Terms")
public class Term {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    private String title;
    private String start;
    private String end;
    private Integer courseID;

    public Term(int ID, String title, String start, String end, Integer courseID) {
        this.ID = ID;
        this.title = title;
        this.start = start;
        this.end = end;
        this.courseID = courseID;
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

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }
}
