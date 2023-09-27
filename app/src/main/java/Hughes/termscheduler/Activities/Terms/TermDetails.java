package Hughes.termscheduler.Activities.Terms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import Hughes.termscheduler.Adapters.CourseAdapter;
import Hughes.termscheduler.R;
import Hughes.termscheduler.dataBase.Repository;
import Hughes.termscheduler.entities.Courses;

public class TermDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);


        //======================== RECYCLER VIEW FOR COURSES ========================
        RecyclerView recyclerView = findViewById(R.id.termDetailsRecycler);
        Repository repository = new Repository(getApplication());
        List<Courses> allCourses = repository.getmAllCourses();
        CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter.setCourses(allCourses);

    }
}