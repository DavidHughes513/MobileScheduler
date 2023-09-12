package Hughes.termscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.sql.Date;
import java.time.Instant;

import Hughes.termscheduler.R;
import Hughes.termscheduler.dataBase.Repository;
import Hughes.termscheduler.entities.Courses;
import Hughes.termscheduler.entities.Term;

public class TermsList extends AppCompatActivity {

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_list);

        repository = new Repository(getApplication());
        Term term = new Term(0, "Term 1", "(Date) Date.from(Instant.now())", "(Date) Date.from(Instant.now())", 0);
        repository.insertTerm(term);
        Courses course = new Courses(0, "Test", "now", "now", 1,1);
        repository.insertCourse(course);

        System.out.println(getIntent().getStringExtra("test"));
    }



}