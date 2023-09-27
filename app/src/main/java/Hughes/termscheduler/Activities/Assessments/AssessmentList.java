package Hughes.termscheduler.Activities.Assessments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import Hughes.termscheduler.Adapters.AssessmentAdapter;
import Hughes.termscheduler.Adapters.CourseAdapter;
import Hughes.termscheduler.R;
import Hughes.termscheduler.dataBase.Repository;
import Hughes.termscheduler.entities.Assessments;
import Hughes.termscheduler.entities.Courses;

public class AssessmentList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_list);


        RecyclerView recyclerView = findViewById(R.id.assessmentListRecyclerView);
        Repository repository = new Repository(getApplication());
        List<Assessments> allAssessments = repository.getmAllAssessments();
        AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdapter.setAssessmnets(allAssessments);





    }



}