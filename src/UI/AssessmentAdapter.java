package com.example.mobileappdevpa.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappdevpa.Entity.AssessmentEntity;
import com.example.mobileappdevpa.R;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentItemView;



        private AssessmentViewHolder(View itemView) {
            super(itemView);
            assessmentItemView = itemView.findViewById(R.id.assessmentTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final AssessmentEntity current = mAssessments.get(position);
                    Intent intent = new Intent(context, AssessmentActivity.class);
                    intent.putExtra("assessmentTitle", current.getAssessmentTitle());
                    intent.putExtra("dateOfAssessment", current.getDateOfAssessment());
                    intent.putExtra("assessmentType",current.getAssessmentType());
                    intent.putExtra("id",current.getId());
                    intent.putExtra("courseId", current.getCourseId());
                    context.startActivity(intent);
                }
            });
        }

    }

    private final LayoutInflater mInflater;
    private final Context context;
    private List<AssessmentEntity> mAssessments; // Cached copy of words

    public AssessmentAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context=context;
    }

    @Override
    public AssessmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.assessment_list_item, parent, false);

        return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AssessmentViewHolder holder, int position) {
        if (mAssessments != null) {
            AssessmentEntity current = mAssessments.get(position);
            holder.assessmentItemView.setText(current.getAssessmentTitle());

        } else {
            // Covers the case of data not being ready yet.
            holder.assessmentItemView.setText("No Word");

        }

    }

    public void setWords(List<AssessmentEntity> words) {
        mAssessments = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mAssessments != null)
            return mAssessments.size();
        else return 0;
    }
}

