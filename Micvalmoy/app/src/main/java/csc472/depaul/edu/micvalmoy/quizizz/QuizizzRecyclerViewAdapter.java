package csc472.depaul.edu.micvalmoy.quizizz;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import csc472.depaul.edu.micvalmoy.R;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.QuizInfo;

import static android.text.Html.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH;



public class QuizizzRecyclerViewAdapter extends RecyclerView.Adapter<QuizizzRecyclerViewAdapter.ItemViewHolder> {
    private static final String TAG = QuizizzRecyclerViewAdapter.class.getSimpleName();

    private Context context;
    private OnClickItemAdapterListener listener;

    private final LayoutInflater mInflater;
    private List<QuizInfo> listItems = new ArrayList<>(); // Cached copy of quizzes


    //-----------------------------------------------------------------------------------------
    QuizizzRecyclerViewAdapter(Context context, OnClickItemAdapterListener listener) {
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
        TextView tvQuizName;
        TextView tvQuizId;


        public ItemViewHolder(View view) {
            super(view);

            tvQuizName   = (TextView) itemView.findViewById(R.id.tvQuizName);
            tvQuizId     = (TextView) itemView.findViewById(R.id.tvQuizId);

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
        View itemView = mInflater.inflate(R.layout.item_quizizz, parent, false);
        return new ItemViewHolder(itemView);
    }

    //-----------------------------------------------------------------------------------------
    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int position) {
        QuizInfo current = listItems.get(position);


        if (current != null && current.getId() != null) {

            itemViewHolder.tvQuizId.setText("" + current.getId());
            itemViewHolder.tvQuizName.setText(current.getName());
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


    public QuizInfo getListItemAtPosition(int position) {
        return listItems.get(position);
    }




    //-----------------------------------------------------------------------------------------
    //Load data into the recycle view
/*    void submitList(List<QuizInfo> quizzes) {
        listItems = quizzes;
        notifyDataSetChanged();
    }*/


    public void submitList(List<QuizInfo> newQuiz) {
        ItemDiffUtil itemDiffUtil =new ItemDiffUtil(listItems,newQuiz);
        DiffUtil.DiffResult diffResult=DiffUtil.calculateDiff(itemDiffUtil);
        listItems.clear();
        listItems.addAll(newQuiz);
        diffResult.dispatchUpdatesTo(this);
    }

    public class ItemDiffUtil extends DiffUtil.Callback {

        private List<QuizInfo> oldList, newList;

        public ItemDiffUtil(List<QuizInfo> oldList, List<QuizInfo> newList) {
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
        void onClick(String id, int position);

        void onLongClick(String id, int position);
    }
    //-----------------------------------------------------------------------------------------
}