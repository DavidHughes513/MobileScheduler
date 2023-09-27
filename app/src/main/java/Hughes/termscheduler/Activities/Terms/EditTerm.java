package Hughes.termscheduler.Activities.Terms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import Hughes.termscheduler.Activities.Courses.EditCourse;
import Hughes.termscheduler.Adapters.CourseAdapter;
import Hughes.termscheduler.R;
import Hughes.termscheduler.dataBase.Repository;
import Hughes.termscheduler.entities.Courses;
import Hughes.termscheduler.entities.Term;

public class EditTerm extends AppCompatActivity {

    Term eTerm;

    List<Term> allTerms = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_term);
        Repository repository = new Repository(getApplication());

        //DECLARE BUNDLE AND EXTRA VALUES
        Bundle extras = getIntent().getExtras();
        int currentTermID = extras.getInt("ID");
        allTerms = repository.getmAllTerms();

        //DECLARE BUTTONS AND FIELDS
        Button newCourseButton = findViewById(R.id.eTermNewCourseButton);
        Button saveTermButton = findViewById(R.id.eTermSaveButton);
        Button deleteTermButton = findViewById(R.id.eTermDeleteButton);
        EditText termName = findViewById(R.id.eTermName);
        EditText termStart = findViewById(R.id.eTermStartDate);
        EditText termEnd = findViewById(R.id.eTermEndDate);



        //======================== RECYCLER VIEW FOR COURSES ========================
        RecyclerView recyclerView = findViewById(R.id.eTermCoursesRecycler);
        List<Courses> allCourses = repository.getmAllCourses();
        CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Courses> termCourses = new ArrayList<>();
        for (Courses courses : allCourses) {
            if (courses.getTermID() == currentTermID) {
                termCourses.add(courses);
            }
            courseAdapter.setCourses(termCourses);

            //SETTING FIELD VALUES
            termName.setText(extras.getString("title"));
            termStart.setText(extras.getString("Start"));
            termEnd.setText(extras.getString("End"));


            //=============== FUNCTIONALITY =====================

            //New Course
            newCourseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(EditTerm.this, EditCourse.class);
                    intent.putExtra("ID", currentTermID);
                    startActivity(intent);
                }
            });

            //Save Term
            saveTermButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                      Term newTerm = new Term(currentTermID, termName.getText().toString(), termStart.getText().toString(), termEnd.getText().toString());
                        if(currentTermID > 0) {
                            repository.updateTerm(newTerm);
                        }
                        else {
                            repository.insertTerm(newTerm);
                        }
                    finish();
                }
            });

            //Delete Term
            deleteTermButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(Term term : allTerms){
                        int existingID = term.getID();
                        if(existingID == currentTermID){
                            repository.deleteTerm(term);
                        }
                        else {
                            System.out.println("Term Not Found");
                        }

                    }
                    finish();
                }
            });









        }

    }

}