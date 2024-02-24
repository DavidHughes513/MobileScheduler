package Hughes.termscheduler.Activities.Terms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Hughes.termscheduler.Activities.Courses.EditCourse;
import Hughes.termscheduler.Adapters.CourseAdapter;
import Hughes.termscheduler.Adapters.TermAdapter;
import Hughes.termscheduler.R;
import Hughes.termscheduler.dataBase.Repository;
import Hughes.termscheduler.entities.Courses;
import Hughes.termscheduler.entities.Term;

public class EditTerm extends AppCompatActivity {

    //Initializing Tools

    Repository repository = new Repository(getApplication());
    List<Term> allTerms = new ArrayList<>();
    List<Courses> allCourses = new ArrayList<>();
    List<Courses> termCourses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_term);


        //DECLARE BUNDLE AND EXTRA VALUES
        Bundle extras = getIntent().getExtras();
        int currentTermID = extras.getInt("currentTermID");
        int newTermID = extras.getInt("newTermID");
        int termID;
        if(currentTermID > newTermID){
            termID = currentTermID;
        }else{
            termID = newTermID;
        }
        allTerms = repository.getmAllTerms();
        allCourses = repository.getmAllCourses();
        int newCourseID;
        if(allCourses.size() == 0){
            newCourseID = 1;
        }else{
            newCourseID = allCourses.get(allCourses.size() - 1).getID() +1;
        }


        //DECLARE BUTTONS AND FIELDS
        Button newCourseButton = findViewById(R.id.eTermNewCourseButton);
        Button saveTermButton = findViewById(R.id.eTermSaveButton);
        Button deleteTermButton = findViewById(R.id.eTermDeleteButton);
        EditText termName = findViewById(R.id.eTermName);
        EditText termStart = findViewById(R.id.eTermStartDate);
        EditText termEnd = findViewById(R.id.eTermEndDate);



        //======================== RECYCLER VIEW FOR COURSES ========================
        /*RecyclerView recyclerView = findViewById(R.id.eTermCoursesRecycler);
        CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for (Courses courses : allCourses) {
            int listCourseID = courses.getTermID();
            if (listCourseID == termID) {
                termCourses.add(courses);
            }
        }
            courseAdapter.setCourses(termCourses);*/

            //SETTING FIELD VALUES
            termName.setText(extras.getString("title"));
            termStart.setText(extras.getString("Start"));
            termEnd.setText(extras.getString("End"));


            //=============== FUNCTIONALITY =====================

            //New Course
            newCourseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("The new course button was pushed");
                    Intent intent = new Intent(EditTerm.this, EditCourse.class);
                    intent.putExtra("currentTermID", currentTermID);
                    intent.putExtra("newTermID", newTermID);
                    intent.putExtra("newCourseID", newCourseID);
                    startActivity(intent);
                }
            });

            //Save Term
            saveTermButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(currentTermID > 0){
                      Term newTerm = new Term(currentTermID, termName.getText().toString(), termStart.getText().toString(), termEnd.getText().toString());
                            repository.updateTerm(newTerm);
                        }
                        else {
                        Term newTerm = new Term(newTermID, termName.getText().toString(), termStart.getText().toString(), termEnd.getText().toString());
                            repository.insertTerm(newTerm);
                        }
                    finish();
                }
            });

            //Delete Term
            deleteTermButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(Courses courses : allCourses){
                        if(courses.getTermID() == termID){
                            Toast.makeText(EditTerm.this, "This Term has courses and can't be deleted", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    for(Term term : allTerms){
                        int existingID = term.getID();
                        if(existingID == termID){
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


    @Override
    protected void onResume() {
        super.onResume();
        int currentTermID = getIntent().getExtras().getInt("currentTermID");
        int newTermID = getIntent().getExtras().getInt("newTermID");
        int termID;
        if(currentTermID > newTermID){
            termID = currentTermID;
        }else{
            termID = newTermID;
        }
        RecyclerView recyclerView = findViewById(R.id.eTermCoursesRecycler);
        CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        allCourses.clear();
        allCourses = repository.getmAllCourses();
        termCourses.clear();
        for (Courses courses : allCourses) {
            if (courses.getTermID() == termID) {
                termCourses.add(courses);
            }
        }
        courseAdapter.setCourses(termCourses);
    }
}