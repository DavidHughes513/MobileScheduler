package Hughes.termscheduler.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Hughes.termscheduler.Activities.Terms.EditTerm;
import Hughes.termscheduler.Activities.Terms.TermDetails;
import Hughes.termscheduler.R;
import Hughes.termscheduler.entities.Term;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

     List<Term> mTerm;
     Context context;
    public TermAdapter (Context context) {
        this.context = context;
    }

    public class TermViewHolder extends RecyclerView.ViewHolder {

        private TextView termListName;
        private TextView termListStart;
        private TextView termListEnd;

        public TermViewHolder(@NonNull View itemView) {
            super(itemView);

            termListName = itemView.findViewById(R.id.termListItemName);
            termListStart = itemView.findViewById(R.id.termListItemStart);
            termListEnd = itemView.findViewById(R.id.termListItemEnd);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Term current = mTerm.get(position);
                    Intent intent = new Intent(context, EditTerm.class);
                    intent.putExtra("ID", current.getID());
                    intent.putExtra("title", current.getTitle());
                    intent.putExtra("Start", current.getStart());
                    intent.putExtra("End", current.getEnd());
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View layoutView = inflater.inflate(R.layout.term_list_item, parent, false);
        return new TermAdapter.TermViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
        if(mTerm != null){
            Term current = mTerm.get(position);
            String title = current.getTitle();
            holder.termListName.setText(title);
            holder.termListStart.setText(current.getStart());
            holder.termListEnd.setText(current.getEnd());

        }
        else{
            holder.termListName.setText("No Term Name");
        }
    }

    @Override
    public int getItemCount() {
        if(mTerm != null){
            return mTerm.size();
        }
        else return 0;
    }


    public void setTerms(List<Term> term){
        mTerm = term;
        notifyDataSetChanged();
    }

}
