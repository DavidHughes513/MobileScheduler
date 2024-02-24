package Hughes.termscheduler.Activities.Assessments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Hughes.termscheduler.Activities.Home;
import Hughes.termscheduler.R;
import Hughes.termscheduler.Recievers.AssessmentEndReceiver;
import Hughes.termscheduler.Recievers.AssessmentStartReceiver;
import Hughes.termscheduler.dataBase.Repository;
import Hughes.termscheduler.entities.Assessments;

public class EditAssessment extends AppCompatActivity {

    List<Assessments> allAssessments = new ArrayList<>();
    RadioButton checkedButton;
    RadioGroup eAssRadioGroup;

    String assessmentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assessment);
        Repository repository = new Repository(getApplication());
        Bundle extras = getIntent().getExtras();
        int currentCourseID = extras.getInt("assCurrentCourseID");
        int currentAssID = extras.getInt("currentAssID");
        int newAssID = extras.getInt("newAssID");
        allAssessments = repository.getmAllAssessments();
        int assID;
        if(currentAssID > newAssID){
            assID = currentAssID;
        }else {
            assID = newAssID;
        }

        //=========== DECLAIRING FIELDS ====================
        EditText eAssName = findViewById(R.id.eAssName);
        EditText eAssStart = findViewById(R.id.eAssStartDate);
        EditText eAssEnd = findViewById(R.id.eAssEndDate);
        Button deleteAssButton = findViewById(R.id.eAssDelete);
        Button saveAssButton = findViewById(R.id.eAssSaveButton);
        Button newAlertButton = findViewById(R.id.eAssSetAlert);
        Button newEndAlertButton = findViewById(R.id.eAssSetEndAlert);
        eAssRadioGroup = findViewById(R.id.eAssRadioGroup);


        assessmentType = extras.getString("assType");
        RadioButton objButton = findViewById(R.id.eAssObjButton);
        RadioButton perfButton = findViewById(R.id.eAssPerfButton);
        if(assessmentType != null) {
            if (assessmentType.equals("Performance")) {
                perfButton.setChecked(true);
            } else if (assessmentType.equals("Objective")) {
                objButton.setChecked(true);
            }
        }
        //============= POPULATING FIELDS ==================
        eAssName.setText(extras.getString("assTitle"));
        eAssStart.setText(extras.getString("assStart"));
        eAssEnd.setText(extras.getString("assEnd"));


        //=============== FUNCTIONALITY ====================


        newAlertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startDateFromScreen = eAssStart.getText().toString();
                //String endDateFromScreen = eAssEnd.getText().toString();
                String dateFormat = "MM-dd-yy";
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
                Date startDate = null;
                //Date endDate = null;

                try{
                    startDate =  sdf.parse(startDateFromScreen);
                    //endDate = sdf.parse(endDateFromScreen);

                }catch ( NullPointerException | ParseException e){
                    e.printStackTrace();
                    Toast.makeText(EditAssessment.this, "Please insert date in proper format!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(EditAssessment.this, "Assessment Start Alert Made For: " + startDateFromScreen, Toast.LENGTH_SHORT).show();
                Long trigger = startDate.getTime();
                //Long trigger2 = endDate.getTime();
                Intent intent = new Intent(EditAssessment.this, AssessmentStartReceiver.class);
                intent.putExtra("assessmentStartMSG", "Your Assessment: " + eAssName.getText().toString() + "  Starts Today!");
                PendingIntent sendStart = PendingIntent.getBroadcast(EditAssessment.this, ++Home.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sendStart);

                /*Toast.makeText(EditAssessment.this, "Assessment End Alert Made For: " + endDateFromScreen, Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(EditAssessment.this, AssessmentEndReceiver.class);
                intent2.putExtra("assessmentEndMSG", "Your Course: " + eAssEnd.getText().toString() + "  Ends Today!");
                PendingIntent sendEnd = PendingIntent.getBroadcast(EditAssessment.this, ++Home.numAlert, intent2, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager2.set(AlarmManager.RTC_WAKEUP, trigger2, sendEnd);*/
            }
        });




        newEndAlertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String endDateFromScreen = eAssEnd.getText().toString();
                String dateFormat = "MM-dd-yy";
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
                Date endDate = null;

                try{
                    endDate = sdf.parse(endDateFromScreen);

                }catch ( NullPointerException | ParseException e){
                    e.printStackTrace();
                    Toast.makeText(EditAssessment.this, "Please insert date in proper format!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Long trigger2 = endDate.getTime();

                Toast.makeText(EditAssessment.this, "Assessment End Alert Made For: " + endDateFromScreen, Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(EditAssessment.this, AssessmentEndReceiver.class);
                intent2.putExtra("assessmentEndMSG", "Your Assessment: " + eAssEnd.getText().toString() + "  Ends Today!");
                PendingIntent sendEnd = PendingIntent.getBroadcast(EditAssessment.this, ++Home.numAlert, intent2, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager2.set(AlarmManager.RTC_WAKEUP, trigger2, sendEnd);
            }
        });





       /* eAssRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                if(checkedID == R.id.eAssObjButton){
                    assessmentType = 1;
                }else if(checkedID == R.id.eAssPerfButton){
                    assessmentType = 0;
                }
            }
        })*/;



        deleteAssButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Assessments assessments : allAssessments){
                    int assListID = assessments.getAssessmentID();
                    if(assListID == assID){
                        repository.deleteAssessments(assessments);
                    }
                }
                finish();
            }
        });


        saveAssButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentAssID > 0){
                    Assessments assessments = new Assessments(currentAssID, currentCourseID, eAssName.getText().toString(), eAssStart.getText().toString(), eAssEnd.getText().toString(), assessmentType);
                    repository.updateAssessments(assessments);
                }
                else {
                    Assessments assessments = new Assessments(newAssID, currentCourseID, eAssName.getText().toString(), eAssStart.getText().toString(), eAssEnd.getText().toString(), assessmentType);
                    repository.insertAssessments(assessments);
                }
                finish();
            }
        });

    }

    public void checkButton(View v){
        int radioId = eAssRadioGroup.getCheckedRadioButtonId();
        checkedButton = findViewById(radioId);
        assessmentType = checkedButton.getText().toString();
        Toast.makeText(this, "Type changed to: " + checkedButton.getText(), Toast.LENGTH_SHORT).show();
    }

}