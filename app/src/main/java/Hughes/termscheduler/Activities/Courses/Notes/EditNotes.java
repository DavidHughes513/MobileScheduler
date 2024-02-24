package Hughes.termscheduler.Activities.Courses.Notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.health.SystemHealthManager;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import Hughes.termscheduler.R;
import Hughes.termscheduler.dataBase.Repository;
import Hughes.termscheduler.entities.Notes;

public class EditNotes extends AppCompatActivity {

    List<Notes> allNotes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);
        Repository repository = new Repository(getApplication());
        Bundle extras = getIntent().getExtras();
        int currentCourseID = extras.getInt("noteCourseID");
        int currentNoteID = extras.getInt("currentNoteID");
        int newNoteID = extras.getInt("newNoteID");
        int noteID;
        if(currentNoteID > newNoteID){
            noteID = currentNoteID;
        }else{
            noteID = newNoteID;
        }


        EditText editNote = findViewById(R.id.eNotesTxT);
        Button saveNoteButton = findViewById(R.id.eNotesSave);
        Button deleteNoteButton = findViewById(R.id.eNoteDelete);


        editNote.setText(extras.getString("noteContents"));


        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentNoteID > 0){
                        Notes note = new Notes(currentNoteID, currentCourseID, editNote.getText().toString());
                        repository.updateNotes(note);
                    }
                    else{
                    Notes note = new Notes(newNoteID, currentCourseID, editNote.getText().toString());
                        repository.insertNotes(note);
                    }
                finish();
            }
        });


        deleteNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allNotes = repository.getmAllNotes();
                System.out.println("Button was pushed. This is Note ID: " + noteID);
                for(Notes notes : allNotes) {
                    int listNoteID = notes.getId();
                    System.out.println("this is current IDs in List: " + listNoteID);
                    if (noteID == listNoteID) {
                        repository.deleteNotes(notes);
                    }
                }
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_edit_note, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.eNoteShareNote){
            EditText noteToShare = findViewById(R.id.eNotesTxT);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TITLE, noteToShare.getText().toString());
            intent.putExtra(Intent.EXTRA_TEXT, noteToShare.getText().toString());
            intent.setType("text/plain");

            if (intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }

        }
        return true;
    }
}