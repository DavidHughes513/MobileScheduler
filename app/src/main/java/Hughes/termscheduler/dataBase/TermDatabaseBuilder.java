package Hughes.termscheduler.dataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import Hughes.termscheduler.dao.AssessmentsDAO;
import Hughes.termscheduler.dao.CoursesDAO;
import Hughes.termscheduler.dao.InstructorsDAO;
import Hughes.termscheduler.dao.TermDAO;
import Hughes.termscheduler.entities.Assessments;
import Hughes.termscheduler.entities.Courses;
import Hughes.termscheduler.entities.Instructors;
import Hughes.termscheduler.entities.Term;


@Database(entities = {Term.class, Courses.class, Instructors.class, Assessments.class}, version = 1, exportSchema = false)
public abstract class TermDatabaseBuilder extends RoomDatabase {

    public abstract TermDAO termDAO();

    public abstract CoursesDAO coursesDAO();

    public abstract AssessmentsDAO assessmentDAO();

    public abstract InstructorsDAO instructorsDAO();

    private static volatile TermDatabaseBuilder INSTANCE;

    static TermDatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TermDatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TermDatabaseBuilder.class, "TermsDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

