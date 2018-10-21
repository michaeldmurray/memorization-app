package csc472.depaul.edu.micvalmoy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.QuizInfo;
import timber.log.Timber;

public class quizizzAdapter extends ArrayAdapter<QuizInfo> {

 public quizizzAdapter(Context context, ArrayList<QuizInfo> quizizz) {

     super(context, 0, quizizz);

 }


 @Override

 public View getView(int position, View convertView, ViewGroup parent) {

     // Get the data item for this position

     QuizInfo quizInfo = getItem(position);

     // Check if an existing view is being reused, otherwise inflate the view
     if (convertView == null) {
         convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_quizizz, parent, false);
     }

     // Lookup view for data population
     TextView tvQuizName   = (TextView) convertView.findViewById(R.id.tvQuizName);

     TextView tvQuizId = (TextView) convertView.findViewById(R.id.tvQuizId);

     // Populate the data into the template view using the data object
    String quizName = quizInfo.getName();
    String quizId   = quizInfo.getId();

     tvQuizName.setText(quizName);

     tvQuizId.setText(quizId);

     Timber.d("quizInfo - name:  %s" , quizName);
     Timber.d("quizInfo - id:  %s" , quizId);

     // Return the completed view to render on screen
     return convertView;

 }

}
