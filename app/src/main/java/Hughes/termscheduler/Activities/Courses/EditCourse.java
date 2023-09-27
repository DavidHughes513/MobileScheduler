package Hughes.termscheduler.Activities.Courses;

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

import Hughes.termscheduler.Activities.Assessments.EditAssessment;
import Hughes.termscheduler.Activities.Courses.Notes.EditNotes;
import Hughes.termscheduler.Adapters.AssessmentAdapter;
import Hughes.termscheduler.Adapters.NotesAdapter;
import Hughes.termscheduler.R;
import Hughes.termscheduler.dataBase.Repository;
import Hughes.termscheduler.entities.Assessments;
import Hughes.termscheduler.entities.Courses;
import Hughes.termscheduler.entities.Notes;

public class EditCourse extends AppCompatActivity {


    List<Courses> allCourses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        //========== DECLARING BUNDLE AND LISTS ================

        Repository repository = new Repository(getApplication());
        Bundle extras = getIntent().getExtras();
        allCourses = repository.getmAllCourses();
        int termID = extras.getInt("termID");
        int currentCourseID = extras.getInt("courseID");




        //==================== DECLARING FIELDS =======================

        Button newAssButton = findViewById(R.id.eCourseNewAssButton);
        Button deleteCourseAssButton = findViewById(R.id.eCourseDeleteCourseButton);
        Button newNoteButton = findViewById(R.id.eCourseNewNoteButton);
        Button saveCourseButton = findViewById(R.id.eCourseSaveButton);
        Button newAlertButton = findViewById(R.id.eCourseNewAlert);
        EditText eCourseNameTXt = findViewById(R.id.eCourseNameTXT);
        EditText eCourseStart = findViewById(R.id.eCourseStartDate);
        EditText eCourseEnd = findViewById(R.id.eCourseEndDate);
        EditText eCourseInstuctName = findViewById(R.id.eCourseInstructName);
        EditText eCourseInstuctPhone = findViewById(R.id.eCourseInstructPhone);
        EditText eCourseInstuctEmail = findViewById(R.id.eCousreInstructEmail);
        EditText eCourseStatus = findViewById(R.id.eCourseStatus);


        //=============== SETTING FIELD VALUES =========================

        eCourseNameTXt.setText(extras.getString("title"));
        eCourseStart.setText(extras.getString("start"));
        eCourseEnd.setText(extras.getString("end"));
        eCourseInstuctName.setText(extras.getString("instructName"));
        eCourseInstuctPhone.setText(extras.getString("instuctPhone"));
        eCourseInstuctEmail.setText(extras.getString("instructEmail"));
        eCourseStatus.setText(extras.getString("status"));


        //================== FUNCTIONALITY ==============================


        //Assessment Table and Buttons

        newAssButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditCourse.this, EditAssessment.class);
                intent.putExtra("assCourseID", currentCourseID);
                startActivity(intent);
            }
        });


        deleteCourseAssButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Courses courses : allCourses){
                    int courseID = courses.getID();
                    if(courseID == extras.getInt("ID")){
                        repository.deleteCourse(courses);
                    }
                }
            }
        });

        newNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditCourse.this, EditNotes.class);
                intent.putExtra("courseID", currentCourseID);
                startActivity(intent);
            }
        });


        saveCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Courses course = new Courses(currentCourseID, termID, eCourseNameTXt.getText().toString(), eCourseStart.getText().toString(), eCourseEnd.getText().toString(),
                        eCourseStatus.getText().toString(), eCourseInstuctName.getText().toString(), eCourseInstuctPhone.getText().toString(), eCourseInstuctEmail.getText().toString());
                if(currentCourseID > 0){
                    repository.updateCourse(course);
                }
                else{
                    repository.insertCourse(course);
                }

            }
        });


        newAlertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        //======================== RECYCLER FOR ASSESSMENTS ==================================
        RecyclerView recyclerView = findViewById(R.id.eCourseAssRecycler);
        List<Assessments> allAssessments = repository.getmAllAssessments();
        AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Assessments> courseAssessments = new ArrayList<>();
        for(Assessments assessments : allAssessments){
            if(assessments.getCourseID() == extras.getInt("courseID")){
                courseAssessments.add(assessments);
            }
        }
        assessmentAdapter.setAssessmnets(courseAssessments);

        //======================== RECYCLER FOR NOTES ==================================
        RecyclerView recyclerView2 = findViewById(R.id.eCourseNotesRecycler);
        List<Notes> allNotes = repository.getmAllNotes();
        NotesAdapter notesAdapter = new NotesAdapter(this);
        recyclerView2.setAdapter(notesAdapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        List<Notes> courseNotes = new ArrayList<>();
        for(Notes notes : allNotes){
            if(notes.getCourseID() == extras.getInt("courseID"));
        }
        notesAdapter.setmNotes(courseNotes);




    }
}