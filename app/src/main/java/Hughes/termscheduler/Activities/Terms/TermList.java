package Hughes.termscheduler.Activities.Terms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;

import Hughes.termscheduler.Activities.Courses.CourseList;
import Hughes.termscheduler.Activities.Home;
import Hughes.termscheduler.Adapters.TermAdapter;
import Hughes.termscheduler.R;
import Hughes.termscheduler.dataBase.Repository;
import Hughes.termscheduler.entities.Term;

public class TermList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_list);

        Button newTermButton = findViewById(R.id.TermListNewButton);

//===================== RECYCLER VIEW =================================

        RecyclerView recyclerView = findViewById(R.id.TermListRecycler);
        Repository repository = new Repository(getApplication());
        List<Term> allTerms = repository.getmAllTerms();
        TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(allTerms);


        newTermButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TermList.this, EditTerm.class);
                intent.putExtra("ID", 0);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.TermListRecycler);
        Repository repository = new Repository(getApplication());
        List<Term> allTerms = repository.getmAllTerms();
        TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(allTerms);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.termListToHome){
            Intent intent = new Intent(TermList.this, Home.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId() == R.id.termListToCourses){
            Intent intent = new Intent(TermList.this, CourseList.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId() == android.R.id.home){
            this.finish();
            return true;
        }
        return true;
    }




}