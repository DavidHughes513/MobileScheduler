package Hughes.termscheduler.Activities.Courses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import Hughes.termscheduler.Adapters.AssessmentAdapter;
import Hughes.termscheduler.Adapters.NotesAdapter;
import Hughes.termscheduler.R;
import Hughes.termscheduler.dataBase.Repository;
import Hughes.termscheduler.entities.Assessments;
import Hughes.termscheduler.entities.Notes;

public class CourseDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);



//======================== RECYCLER FOR ASSESSMENTS ==================================
        RecyclerView recyclerView = findViewById(R.id.courseDetailsAssRecycler);
        Repository repository = new Repository(getApplication());
        List<Assessments> allAssessments = repository.getmAllAssessments();
        AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdapter.setAssessmnets(allAssessments);


        //======================== RECYCLER FOR NOTES ==================================
        RecyclerView recyclerView2 = findViewById(R.id.courseDetailsNotesRecycler);
        Repository repository2 = new Repository(getApplication());
        List<Notes> allNotes = repository2.getmAllNotes();
        NotesAdapter notesAdapter = new NotesAdapter(this);
        recyclerView2.setAdapter(notesAdapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        notesAdapter.setmNotes(allNotes);
    }
}