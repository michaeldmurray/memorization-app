package csc472.depaul.edu.micvalmoy;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.Quiz;

import static android.text.Html.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH;

public class QuizListRecyclerViewAdapter extends RecyclerView.Adapter<QuizListRecyclerViewAdapter.QuizViewHolder> {
    private static final String TAG = QuizListRecyclerViewAdapter.class.getSimpleName();

    private Context context;
    private QuizAdapterListener listener;

    private final LayoutInflater mInflater;
    private List<Quiz> mQuizzes = Collections.emptyList(); // Cached copy of quizzes


    /*    class QuizViewHolder extends RecyclerView.ViewHolder {
        private final TextView quizItemView;

        private QuizViewHolder(View itemView) {
            super(itemView);
            quizItemView = itemView.findViewById(R.id.textView);
        }
    }*/

    public class QuizViewHolder extends RecyclerView.ViewHolder {



        // Lookup view to populate data
        TextView dot;

        TextView quizUpdatedAt;
        TextView quizId;
        TextView quizName;
        TextView quizDescription;

        public QuizViewHolder(View view) {
            super(view);

            quizName           = (TextView) itemView.findViewById(R.id.tvQuizName);
            //quizId             = (TextView) itemView.findViewById(R.id.tvQuizId);
            quizDescription    = (TextView) itemView.findViewById(R.id.tvQuizDescription);

            dot                = (TextView) itemView.findViewById(R.id.dot);
            quizUpdatedAt      = (TextView) itemView.findViewById(R.id.tvQuizUpdatedAt);



            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(getQuizAtPosition(getLayoutPosition()).getId(), getLayoutPosition());
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onLongClick(getQuizAtPosition(getLayoutPosition()).getId(), getLayoutPosition());
                    return true;
                }
            });
        }
    }


    QuizListRecyclerViewAdapter(Context context, QuizAdapterListener listener) {
       // super(DIFF_CALLBACK);
        this.context  = context;
        this.listener = listener;

        mInflater = LayoutInflater.from(context);
    }


    @Override
    public QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.quiz_list_row, parent, false);
        return new QuizViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(QuizViewHolder holder, int position) {
        Quiz current = mQuizzes.get(position);


        if (current != null && current.getId() != null) {
            //holder.quizId.setText(""+current.getId());
            holder.quizName.setText(current.getName());
            holder.quizDescription.setText(current.getDescription());

            // Formatting and displaying timestamp
            holder.quizUpdatedAt.setText(formatDate(current.getUpdatedAt()));

            // Displaying dot from HTML character code
            holder.dot.setText(Html.fromHtml("&#8226;",FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));

            // Changing dot color to random color
            holder.dot.setTextColor(getRandomMaterialColor("400"));
        }
    }

    void submitList(List<Quiz> quizzes) {
        mQuizzes = quizzes;
        notifyDataSetChanged();
    }
    public Quiz getQuizAtPosition(int position) {
        return mQuizzes.get(position);
    }


    @Override
    public int getItemCount() {
        return mQuizzes.size();
    }

    //--------------------------------------------------------------------
    /**
     * Chooses random color defined in res/array.xml
     */
    private int getRandomMaterialColor(String typeColor) {
        int returnColor = Color.GRAY;
        int arrayId = context.getResources().getIdentifier("mdcolor_" + typeColor, "array", context.getPackageName());

        if (arrayId != 0) {
            TypedArray colors = context.getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.GRAY);
            colors.recycle();
        }
        return returnColor;
    }

    /**
     * Formatting timestamp to `MMM d` format
     * Input: 2018-02-21 00:15:42
     * Output: Feb 21
     */
    private String formatDate(OffsetDateTime timestamp) {
        try {
            if(timestamp !=null){
                return timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a X"));
            }

        } catch (Exception e) {
            // TODO - handle exception
            e.printStackTrace();
        }
        return "";
    }

    public interface QuizAdapterListener {
        void onClick(Long quizId, int position);

        void onLongClick(Long quizId, int position);
    }

}