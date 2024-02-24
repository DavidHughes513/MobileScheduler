package Hughes.termscheduler.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Hughes.termscheduler.Activities.Assessments.AssessmentList;
import Hughes.termscheduler.Activities.Courses.CourseList;
import Hughes.termscheduler.Activities.Terms.TermList;
import Hughes.termscheduler.R;
import Hughes.termscheduler.dataBase.Repository;
import Hughes.termscheduler.entities.Assessments;
import Hughes.termscheduler.entities.Courses;
import Hughes.termscheduler.entities.Notes;
import Hughes.termscheduler.entities.Term;

public class Home extends AppCompatActivity {

    public static int numAlert = 57561345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button termListButton = findViewById(R.id.viewTermsButton);
        Button courseListButton = findViewById(R.id.viewCoursesButton);
        Button assessmentListButton = findViewById(R.id.viewAssessmentsButton);
        Repository repository = new Repository(getApplication());

        termListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, TermList.class);
                startActivity(intent);
            }
        });


        courseListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Home.this, CourseList.class);
                startActivity(intent2);
            }
        });

        assessmentListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Home.this, AssessmentList.class);
                startActivity(intent2);
            }
        });

       // repository.deleteAllTerms();
        //repository.deleteAllCourses();
        /*repository.insertTerm(new Term(1, "test", "test", "test"));
        repository.insertTerm(new Term(2, "test2", "test2", "test2"));
        repository.insertCourse(new Courses(0, 1,"Ctest", "now", "now", "Testing", "Me", "444555666", "no@no.com"));
        repository.insertCourse(new Courses(0, 2,"Ctest2", "later", "later", "Still Testing", "Me", "66", "no@nggggg.com"));*/

        System.out.println("testing order 2");
    }





}