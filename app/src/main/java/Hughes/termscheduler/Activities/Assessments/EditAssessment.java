package Hughes.termscheduler.Activities.Assessments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import Hughes.termscheduler.R;
import Hughes.termscheduler.dataBase.Repository;

public class EditAssessment extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assessment);
        Repository repository = new Repository(getApplication());
        Bundle extras = getIntent().getExtras();
        int currentCourseID = extras.getInt("courseID");

        //=========== DECLAIRING FIELDS ====================
        EditText eAssName = findViewById(R.id.eAssName);
        EditText eAssStart = findViewById(R.id.eAssStartDate);
        EditText eAssEnd = findViewById(R.id.eAssEndDate);
        RadioButton objButton = findViewById(R.id.eAssObjButton);
        RadioButton perfButton = findViewById(R.id.eAssPerfButton);
        Button deleteAssButton = findViewById(R.id.eAssDelete);
        Button saveAssButton = findViewById(R.id.eAssSaveButton);

        //============= POPULATING FIELDS ==================
        eAssName.setText(extras.getString("assTitle"));
        eAssStart.setText(extras.getString("assStart"));
        eAssEnd.setText(extras.getString("assEnd"));

        //=============== FUNCTIONALITY ====================

        deleteAssButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        saveAssButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }
}