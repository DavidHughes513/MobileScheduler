package Hughes.termscheduler.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Notes {
    @PrimaryKey
    private int id;
    private int courseID;
    private String contents;

    public Notes(int id, int courseID, String contents) {
        this.id = id;
        this.courseID = courseID;
        this.contents = contents;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
