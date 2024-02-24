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

import Hughes.termscheduler.Activities.Courses.EditCourse;
import Hughes.termscheduler.R;
import Hughes.termscheduler.entities.Courses;
import Hughes.termscheduler.entities.Term;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    List<Courses> mCourses;
    Context context;

    public CourseAdapter (Context context) {
        this.context = context;
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder{

        private TextView courseTitle;
        private TextView courseStart;
        private TextView courseEnd;
        private TextView courseStatus;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            courseTitle = itemView.findViewById(R.id.courseListItemTitle);
            courseStart = itemView.findViewById(R.id.courseListItemStart);
            courseEnd = itemView.findViewById(R.id.courseListItemEnd);
            courseStatus = itemView.findViewById(R.id.courseListItemStatus);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Courses current = mCourses.get(position);
                    Intent intent = new Intent(context, EditCourse.class);
                    intent.putExtra("currentTermID", current.getTermID());
                    intent.putExtra("currentCourseID", current.getID());
                    intent.putExtra("title", current.getTitle());
                    intent.putExtra("start", current.getStart());
                    intent.putExtra("end", current.getEnd());
                    intent.putExtra("status", current.getStatus());
                    intent.putExtra("instructName", current.getInstructName());
                    intent.putExtra("instructPhone", current.getInstructPhone());
                    intent.putExtra("instructEmail", current.getInstuctorEmail());
                    context.startActivity(intent);

                }
            });

        }
    }


    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View layoutView = inflater.inflate(R.layout.couse_list_item, parent, false);
        return new CourseAdapter.CourseViewHolder(layoutView);
    }


    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {

        if(mCourses != null){
            Courses current = mCourses.get(position);
            holder.courseTitle.setText(current.getTitle());
            holder.courseStart.setText(current.getStart());
            holder.courseEnd.setText(current.getEnd());
            holder.courseStatus.setText(current.getStatus());
        }
        else {
            holder.courseTitle.setText("No Course Name");
        }
    }


    @Override
    public int getItemCount() {
        if(mCourses != null){
            return mCourses.size();
        }
       else return 0;
    }

    public void setCourses(List<Courses> courses){
        mCourses =  courses;
        notifyDataSetChanged();
    }


}
