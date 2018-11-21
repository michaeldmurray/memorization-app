package csc472.depaul.edu.micvalmoy;

/**
 * @author mrichards
 */
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.util.DiffUtil;
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
import csc472.depaul.edu.micvalmoy.stub.StubQuiz;

import static android.text.Html.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH;

public class QuizListRecyclerViewAdapter extends RecyclerView.Adapter<QuizListRecyclerViewAdapter.ItemViewHolder> {
    private static final String TAG = QuizListRecyclerViewAdapter.class.getSimpleName();

    private Context context;
    private OnClickItemAdapterListener listener;

    private final LayoutInflater mInflater;
    private List<Quiz> listItems = StubQuiz.getAllQuizzes();
//    private List<Quiz> listItems = Collections.emptyList(); // Cached copy of quizzes



    //-----------------------------------------------------------------------------------------
    QuizListRecyclerViewAdapter(Context context, OnClickItemAdapterListener listener) {
        this.context  = context;
        this.listener = listener;

        mInflater = LayoutInflater.from(context);
    }

    //-----------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------
    //-------  Setup view holder  -------------------------------------------------------------


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        // Lookup view to populate data
        TextView dot;

        TextView quizUpdatedAt;
        TextView quizId;
        TextView quizName;
        TextView quizDescription;

        public ItemViewHolder(View view) {
            super(view);

            quizName           = (TextView) itemView.findViewById(R.id.tvQuizName);
            quizDescription    = (TextView) itemView.findViewById(R.id.tvQuizDescription);
            dot                = (TextView) itemView.findViewById(R.id.dot);
            quizUpdatedAt      = (TextView) itemView.findViewById(R.id.tvQuizUpdatedAt);
            //quizId           = (TextView) itemView.findViewById(R.id.tvQuizId);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(getListItemAtPosition(getLayoutPosition()).getId(), getLayoutPosition());
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onLongClick(getListItemAtPosition(getLayoutPosition()).getId(), getLayoutPosition());
                    return true;
                }
            });
        }

        //-----------------------------------------------------------------------------------------
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


        //-----------------------------------------------------------------------------------------
        /**
         * Formatting timestamp
         * Input: 2018-02-21 00:15:42
         *
         */
        private String formatDate(OffsetDateTime timestamp) {
            try {
                if(timestamp !=null){
                    return timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
                }

            } catch (Exception e) {
                // TODO - handle exception
                e.printStackTrace();
            }
            return "";
        }


    }




    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.quiz_list_row, parent, false);
        return new ItemViewHolder(itemView);
    }

    //-----------------------------------------------------------------------------------------
    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int position) {
        Quiz current = listItems.get(position);


        if (current != null && current.getId() != null) {
            //itemViewHolder.quizId.setText(""+current.getId());


            itemViewHolder.quizName.setText(current.getName());
            itemViewHolder.quizDescription.setText(current.getDescription());

            // Formatting and displaying timestamp
            itemViewHolder.quizUpdatedAt.setText(itemViewHolder.formatDate(current.getUpdatedAt()));

            // Displaying dot from HTML character code
            itemViewHolder.dot.setText(Html.fromHtml("&#8226;",FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));

            // Changing dot color to random color
            itemViewHolder.dot.setTextColor(itemViewHolder.getRandomMaterialColor("400"));
        }
    }


    //-----------------------------------------------------------------------------------------
    @Override
    public int getItemCount() {
        return listItems.size();
    }

    //-----------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------


    public Quiz getListItemAtPosition(int position) {
        return listItems.get(position);
    }




    //-----------------------------------------------------------------------------------------
    //Load data into the recycle view
/*    void submitList(List<Quiz> quizzes) {
        listItems = quizzes;
        notifyDataSetChanged();
    }*/


//    public void submitList(List<Quiz> newQuiz) {
//        ItemDiffUtil itemDiffUtil =new ItemDiffUtil(listItems,newQuiz);
//        DiffUtil.DiffResult diffResult=DiffUtil.calculateDiff(itemDiffUtil);
//        listItems.clear();
//        listItems.addAll(newQuiz);
//        diffResult.dispatchUpdatesTo(this);
//    }

    public class ItemDiffUtil extends DiffUtil.Callback {

        private List<Quiz> oldList, newList;

        public ItemDiffUtil(List<Quiz> oldList, List<Quiz> newList) {
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
    //-----------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------

    public interface OnClickItemAdapterListener {
        void onClick(Long quizId, int position);

        void onLongClick(Long quizId, int position);
    }
    //-----------------------------------------------------------------------------------------
}