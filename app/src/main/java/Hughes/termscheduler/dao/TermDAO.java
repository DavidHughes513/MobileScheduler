package Hughes.termscheduler.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Upsert;

import java.util.List;

import Hughes.termscheduler.entities.Term;

@Dao
public interface TermDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Term term);

    @Update
    void update(Term term);

    @Upsert
    void upsert(Term term);
    @Delete
    void delete(Term term);

    @Query("SELECT * FROM Terms ORDER BY ID ASC")
    List<Term> getAllTerms();

    @Query("DELETE FROM Terms WHERE ID < 1000")
    void deleteAllTerms();


}
