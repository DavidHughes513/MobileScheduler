package Hughes.termscheduler.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Hughes.termscheduler.entities.Courses;
import Hughes.termscheduler.entities.Instructors;
import kotlinx.coroutines.flow.Flow;

@Dao
public interface InstructorsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Instructors instructors);

    @Update
    void update(Instructors instructors);

    @Delete
    void delete(Instructors instructors);

    @Query("SELECT * FROM Instructors ORDER BY ID ASC")
    Flow<List<Instructors>> getAllInstructors();
}
