package Hughes.termscheduler.Activities.Courses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Locale;

import Hughes.termscheduler.Activities.Assessments.EditAssessment;
import Hughes.termscheduler.Activities.Courses.Notes.EditNotes;
import Hughes.termscheduler.Activities.Home;
import Hughes.termscheduler.Activities.Terms.TermList;
import Hughes.termscheduler.Adapters.AssessmentAdapter;
import Hughes.termscheduler.Adapters.NotesAdapter;
import Hughes.termscheduler.R;
import Hughes.termscheduler.Recievers.CourseReceiver;
import Hughes.termscheduler.Recievers.EndCourseReceiver;
import Hughes.termscheduler.dataBase.Repository;
import Hughes.termscheduler.entities.Assessments;
import Hughes.termscheduler.entities.Courses;
import Hughes.termscheduler.entities.Notes;

public class EditCourse extends AppCompatActivity {

    List<Notes> courseNotes = new ArrayList<>();
    List<Assessments> courseAssessments = new ArrayList<>();
    List<Notes> allNotes = new ArrayList<>();
    List<Courses> allCourses = new ArrayList<>();
    List<Assessments> allAssessments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        //========== DECLARING BUNDLE AND LISTS ================

        Repository repository = new Repository(getApplication());
        Bundle extras = getIntent().getExtras();
        allCourses = repository.getmAllCourses();
        int currentTermID = extras.getInt("currentTermID");
        int newTermID = extras.getInt("newTermID");
        int currentCourseID = extras.getInt("currentCourseID");
        int newCourseID = extras.getInt("newCourseID");

        int termID;
        if(currentTermID > newTermID){
            termID = currentTermID;
        }else{
            termID = newTermID;
        }
        System.out.println("This is Term ID: " + termID);

        int courseID;
        if(currentCourseID > newCourseID){
            courseID = currentCourseID;
        }else{
            courseID = newCourseID;
        }



        //==================== DECLARING FIELDS =======================

        Button deleteCourseButton = findViewById(R.id.eCourseDeleteCourseButton);
        Button newAssButton = findViewById(R.id.eCourseNewAssButton);
        Button newNoteButton = findViewById(R.id.eCourseNewNoteButton);
        Button saveCourseButton = findViewById(R.id.eCourseSaveButton);
        Button newAlertButton = findViewById(R.id.eCourseNewAlert);
        Button newEndAlertButton = findViewById(R.id.eCourseEndAlert);
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
        eCourseInstuctPhone.setText(extras.getString("instructPhone"));
        eCourseInstuctEmail.setText(extras.getString("instructEmail"));
        eCourseStatus.setText(extras.getString("status"));


        //================== FUNCTIONALITY ==============================


        //Assessment Table and Buttons

        newAssButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allAssessments = repository.getmAllAssessments();
                int newAssID = 1;
                if(allAssessments.size() != 0){
                    newAssID = allAssessments.get(allAssessments.size() - 1).getAssessmentID() + 1;
                }
                Intent intent = new Intent(EditCourse.this, EditAssessment.class);
                intent.putExtra("assCurrentCourseID", courseID);
                intent.putExtra("newAssID", newAssID);
                intent.putExtra("assType", 0);
                startActivity(intent);
            }
        });


        deleteCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Courses courses : allCourses){
                    int listCourseID = courses.getID();
                    if(listCourseID == courseID){
                        repository.deleteCourse(courses);
                    }
                }
                finish();
            }
        });

        newNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allNotes = repository.getmAllNotes();
                Intent intent = new Intent(EditCourse.this, EditNotes.class);
                int newNoteID = 1;
                 if(allNotes.size() != 0){
                    newNoteID = allNotes.get(allNotes.size() - 1).getId() + 1;
                }
                intent.putExtra("noteCourseID", courseID);
                intent.putExtra("newNoteID", newNoteID);
                startActivity(intent);
            }
        });


        saveCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentCourseID > 0){
                    Courses course = new Courses(currentCourseID, termID, eCourseNameTXt.getText().toString(), eCourseStart.getText().toString(), eCourseEnd.getText().toString(),
                            eCourseStatus.getText().toString(), eCourseInstuctName.getText().toString(), eCourseInstuctPhone.getText().toString(), eCourseInstuctEmail.getText().toString());
                    repository.updateCourse(course);
                }
                else {
                    Courses course = new Courses(newCourseID, termID, eCourseNameTXt.getText().toString(), eCourseStart.getText().toString(), eCourseEnd.getText().toString(),
                            eCourseStatus.getText().toString(), eCourseInstuctName.getText().toString(), eCourseInstuctPhone.getText().toString(), eCourseInstuctEmail.getText().toString());
                    repository.insertCourse(course);
                }
                finish();
            }
        });


        newAlertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startDateFromScreen = eCourseStart.getText().toString();
                //String endDateFromScreen = eCourseEnd.getText().toString();
                String dateFormat = "MM-dd-yy";
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
                Date startDate = null;
                //Date endDate = null;

                try{
                    startDate =  sdf.parse(startDateFromScreen);
                    //endDate = sdf.parse(endDateFromScreen);

                }catch (ParseException | NullPointerException e){
                    e.printStackTrace();
                    Toast.makeText(EditCourse.this, "Please insert date in proper format!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(EditCourse.this, "Course Start Alert Made For: " + startDateFromScreen, Toast.LENGTH_SHORT).show();
                Long trigger = startDate.getTime();
                //Long trigger2 = endDate.getTime();
                Intent intent = new Intent(EditCourse.this, CourseReceiver.class);
                intent.putExtra("courseStartMSG", "Your Course: " + eCourseNameTXt.getText().toString() + "  Starts Today!");
                PendingIntent sendStart = PendingIntent.getBroadcast(EditCourse.this, ++Home.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sendStart);

                /*Toast.makeText(EditCourse.this, "Course End Alert Made For: " + endDateFromScreen, Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(EditCourse.this, EndCourseReceiver.class);
                intent2.putExtra("courseEndMSG", "Your Course: " + eCourseEnd.getText().toString() + "  Ends Today!");
                PendingIntent sendEnd = PendingIntent.getBroadcast(EditCourse.this, ++Home.numAlert, intent2, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager2.set(AlarmManager.RTC_WAKEUP, trigger2, sendEnd);*/



            }
        });

        newEndAlertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String endDateFromScreen = eCourseEnd.getText().toString();
                String dateFormat = "MM-dd-yy";
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
                Date endDate = null;

                try{
                    endDate = sdf.parse(endDateFromScreen);

                }catch (ParseException | NullPointerException e){
                    e.printStackTrace();
                    Toast.makeText(EditCourse.this, "Please insert date in proper format!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Long trigger2 = endDate.getTime();
                Toast.makeText(EditCourse.this, "Course End Alert Made For: " + endDateFromScreen, Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(EditCourse.this, EndCourseReceiver.class);
                intent2.putExtra("courseEndMSG", "Your Course: " + eCourseEnd.getText().toString() + "  Ends Today!");
                PendingIntent sendEnd = PendingIntent.getBroadcast(EditCourse.this, ++Home.numAlert, intent2, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager2.set(AlarmManager.RTC_WAKEUP, trigger2, sendEnd);
            }
        });


        /*//======================== RECYCLER FOR ASSESSMENTS ==================================
        RecyclerView recyclerView = findViewById(R.id.eCourseAssRecycler);
        List<Assessments> allAssessments = repository.getmAllAssessments();
        AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Assessments> courseAssessments = new ArrayList<>();
        for(Assessments assessments : allAssessments){
            if(assessments.getCourseID() == courseID){
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
            if(notes.getCourseID() == courseID);{
                courseNotes.add(notes);
            }
        }
        notesAdapter.setmNotes(courseNotes);*/




    }



   @Override
    protected void onResume() {
        super.onResume();

        Repository repository = new Repository(getApplication());
        Bundle extras = getIntent().getExtras();
       int currentCourseID = extras.getInt("currentCourseID");
       int newCourseID = extras.getInt("newCourseID");
       int courseID;
       if(currentCourseID > newCourseID){
           courseID = currentCourseID;
       }else{
           courseID = newCourseID;
       }

        //======================== RECYCLER FOR ASSESSMENTS ==================================
        RecyclerView recyclerView = findViewById(R.id.eCourseAssRecycler);
        List<Assessments> allAssessments = repository.getmAllAssessments();
        AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAssessments.clear();
        for(Assessments assessments : allAssessments){
            if(assessments.getCourseID() == courseID){
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
        courseNotes.clear();
        for(Notes notes : allNotes){
            if(notes.getCourseID() == courseID){
                courseNotes.add(notes);
            }
        }
        notesAdapter.setmNotes(courseNotes);

    }
}