package Hughes.termscheduler.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "Instructors")
public class Instructors {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    private String name;
    private String phone;
    private String email;

    public Instructors(int ID, String name, String phone, String email) {
        this.ID = ID;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
