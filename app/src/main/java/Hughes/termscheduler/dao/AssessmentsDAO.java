package Hughes.termscheduler.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Upsert;

import java.util.List;

import Hughes.termscheduler.entities.Assessments;
import kotlinx.coroutines.flow.Flow;

@Dao
public interface AssessmentsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Assessments assessments);

    @Update
    void update(Assessments assessments);

    @Upsert
    void upsert(Assessments assessments);
    @Delete
    void delete(Assessments assessments);

    @Query("SELECT * FROM Assessments ORDER BY assessmentID ASC")
    Flow<List<Assessments>> getAllAssessments();
}
