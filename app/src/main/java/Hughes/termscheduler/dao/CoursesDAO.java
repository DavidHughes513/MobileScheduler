package Hughes.termscheduler.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Hughes.termscheduler.entities.Courses;
import kotlinx.coroutines.flow.Flow;

@Dao
public interface CoursesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Courses courses);

    @Update
    void update(Courses courses);

    @Delete
    void delete(Courses courses);

    @Query("SELECT * FROM Courses ORDER BY ID ASC")
    List<Courses> getAllCourses();

    @Query("DELETE FROM Courses WHERE ID < 1000")
    void deleteAllCourses();

}
