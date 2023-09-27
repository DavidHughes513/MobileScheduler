package Hughes.termscheduler.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.ListResourceBundle;

import Hughes.termscheduler.Activities.Assessments.EditAssessment;
import Hughes.termscheduler.R;
import Hughes.termscheduler.entities.Assessments;
import Hughes.termscheduler.entities.Courses;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {
    List<Assessments> mAssessmnets;
    Context context;
    public AssessmentAdapter(Context context){
        this.context = context;
    }



public class AssessmentViewHolder extends RecyclerView.ViewHolder{

        private TextView assTitle;
        private TextView assStart;
        private TextView assEnd;
        private TextView assType;



    public AssessmentViewHolder(@NonNull View itemView) {
        super(itemView);

        assTitle = itemView.findViewById(R.id.assListItemTitle);
        assStart = itemView.findViewById(R.id.assListItemStart);
        assEnd = itemView.findViewById(R.id.assListItemEnd);
        assType = itemView.findViewById(R.id.assListItemType);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                Assessments current = mAssessmnets.get(position);
                Intent intent = new Intent(context, EditAssessment.class);
                intent.putExtra("assID", current.getAssessmentID());
                intent.putExtra("assCourseID", current.getCourseID());
                intent.putExtra("assTitle", current.getAssessmentName());
                intent.putExtra("assStart", current.getStart());
                intent.putExtra("assEnd", current.getEnd());
                intent.putExtra("assType", current.getType());
                context.startActivity(intent);
            }
        });


    }
}

    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View layoutView = inflater.inflate(R.layout.assessment_list_item, parent, false);
        return new AssessmentAdapter.AssessmentViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if(mAssessmnets != null){
            Assessments current = mAssessmnets.get(position);
            holder.assTitle.setText(current.getName());
            holder.assStart.setText(current.getStart());
            holder.assEnd.setText(current.getEnd());
            holder.assType.setText(current.getType());
        }

    }

    @Override
    public int getItemCount() {
        if(mAssessmnets != null){
            return mAssessmnets.size();
        }
        else return 0;
    }

    public void setAssessmnets(List<Assessments> assessments){
        mAssessmnets = assessments;
        notifyDataSetChanged();
    }

}
