package Hughes.termscheduler.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Index;
import androidx.room.Insert;

import java.util.List;

import Hughes.termscheduler.Activities.Courses.Notes.EditNotes;
import Hughes.termscheduler.R;
import Hughes.termscheduler.entities.Assessments;
import Hughes.termscheduler.entities.Notes;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    List<Notes> mNotes;
    Context context;

    public NotesAdapter(Context context){this.context = context;}


    public class NotesViewHolder extends RecyclerView.ViewHolder{


        private TextView noteContent;


        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            noteContent = itemView.findViewById(R.id.notesListItem);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Notes current = mNotes.get(position);
                    Intent intent = new Intent(context, EditNotes.class);
                    intent.putExtra("noteCourseID", current.getCourseID());
                    intent.putExtra("currentNoteID", current.getId());
                    intent.putExtra("noteContents", current.getContents());
                    context.startActivity(intent);

                }
            });

        }
    }



    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View layoutView = inflater.inflate(R.layout.notes_list_item,parent,false);
        return new NotesAdapter.NotesViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        if(mNotes != null){
            Notes current = mNotes.get(position);
            holder.noteContent.setText(current.getContents());
        }
        else{
            holder.noteContent.setText("No Contents");
        }


    }

    @Override
    public int getItemCount() {
        if(mNotes != null){
            return mNotes.size();
        }
        else return 0;
    }

    public void setmNotes(List<Notes> notes){
        mNotes = notes;
        notifyDataSetChanged();
    }

}
