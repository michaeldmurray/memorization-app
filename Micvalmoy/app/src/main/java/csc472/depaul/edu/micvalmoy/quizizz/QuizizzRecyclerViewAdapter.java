package csc472.depaul.edu.micvalmoy.quizizz;


import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import csc472.depaul.edu.micvalmoy.R;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.QuizInfo;

public class QuizizzRecyclerViewAdapter extends RecyclerView.Adapter<QuizizzRecyclerViewAdapter.QuizViwHolder> {

    private ArrayList<QuizInfo> quizzes;
    private Context context;

    private final ActionCallback callback = null;

    public QuizizzRecyclerViewAdapter(ArrayList<QuizInfo> quizzes) {
        this.quizzes = quizzes;
    }

    @Override
    public QuizViwHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_quizizz, viewGroup, false);

        final QuizViwHolder viewHolder = new QuizViwHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onEdit(quizzes.get(viewHolder.getAdapterPosition()));
            }
        });
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(QuizViwHolder quizViwHolder, int position) {

        QuizInfo quizInfo = quizzes.get(position);

        quizViwHolder.tvQuizId.setText("" + quizInfo.getId());
        quizViwHolder.tvQuizName.setText(quizInfo.getName());
    }

    public void setQuizzes(ArrayList<QuizInfo> newQuiz) {
        QuizDiffUtil quizDiffUtil =new QuizDiffUtil(quizzes,newQuiz);
        DiffUtil.DiffResult diffResult=DiffUtil.calculateDiff(quizDiffUtil);
        quizzes.clear();
        quizzes.addAll(newQuiz);
        diffResult.dispatchUpdatesTo(this);
    }


    @Override
    public int getItemCount() {
        return quizzes.size();
    }

    public static class QuizViwHolder extends RecyclerView.ViewHolder {
        // Lookup view to populate data
        TextView tvQuizName;

        TextView tvQuizId;


        public QuizViwHolder(View itemView) {
            super(itemView);
            tvQuizName   = (TextView) itemView.findViewById(R.id.tvQuizName);
            tvQuizId = (TextView) itemView.findViewById(R.id.tvQuizId);
        }
    }

    public class QuizDiffUtil extends DiffUtil.Callback {

        private ArrayList<QuizInfo> oldList, newList;

        public QuizDiffUtil(ArrayList<QuizInfo> oldList, ArrayList<QuizInfo> newList) {
            this.oldList = oldList;
            this.newList = newList;
        }

        @Override
        public int getOldListSize() {
            return oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int i, int i1) {
            if (oldList.get(i).getId() == newList.get(i1).getId())
                return true;
            else
                return false;
        }

        @Override
        public boolean areContentsTheSame(int i, int i1) {
            if (oldList.get(i).equals(newList.get(i1))) {
                return true;
            } else {
                return false;
            }
        }
    }

    public interface ActionCallback {
        void onEdit(QuizInfo quizInfo);
    }

}
