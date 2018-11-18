package csc472.depaul.edu.micvalmoy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import csc472.depaul.edu.micvalmoy.entity.Quiz;
import csc472.depaul.edu.micvalmoy.repository.QuizViewModel;
import timber.log.Timber;

public class QuizListEditDialogFragment extends DialogFragment {

    public static final String TAG = QuizListEditDialogFragment.class.getSimpleName();

    private QuizViewModel viewModel;



    // Use this instance of the interface to deliver action events
    NoticeDialogListener mListener;

    public static QuizListEditDialogFragment newInstance(final boolean shouldUpdate, final Quiz quiz, final int position) {
        QuizListEditDialogFragment f = new QuizListEditDialogFragment();

        Bundle args = new Bundle();
        args.putBoolean("shouldUpdate", shouldUpdate);


        // for Parcelable :
        args.putParcelable ("quiz", quiz);

        args.putInt("position", position);

        f.setArguments(args);

        return f;
    }

/*    public QuizListEditDialogFragment(){

    };*/

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        viewModel = ViewModelProviders.of(getActivity()).get(QuizViewModel.class);

        if (context instanceof NoticeDialogListener) {
            mListener = (NoticeDialogListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final boolean shouldUpdate = getArguments().getBoolean("shouldUpdate");
        final Quiz quiz = getArguments().getParcelable("quiz");
        int position = getArguments().getInt("position");



        // Build the dialog and set up the button click handlers
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());

        View view = layoutInflaterAndroid.inflate(R.layout.quiz_edit_dialog, null);
        builder.setView(view);

        final EditText etQuizDescription = (EditText) view.findViewById(R.id.etDescription);
        final EditText etQuizName = (EditText) view.findViewById(R.id.etQuizName);

        TextView tvdialogTitle = (TextView) view.findViewById(R.id.tvdialogTitle);
        tvdialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_quiz) : getString(R.string.lbl_edit_quiz));

        if (shouldUpdate && quiz != null) {
            // append sets text to EditText and places the cursor at the end
            etQuizDescription.setText(quiz.getDescription());
            etQuizName.setText(quiz.getName());
        }


        builder.setPositiveButton(shouldUpdate ? getString(R.string.update) : getString(R.string.save), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {



                        Timber.d("inside show quiz dialog- setPositiveButton clicked ");
                        //----------------------------------------------------------

                        // Show toast message when no text is entered
                        if (TextUtils.isEmpty(etQuizDescription.getText().toString())) {
                            Toast.makeText(getActivity(), getString(R.string.dialog_title_enter_quiz), Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            dialog.dismiss();
                        }

                        // check if user updating quiz
                        if (shouldUpdate && quiz != null) {

                            final Long quizId = quiz.getId();
                            quiz.setName(etQuizName.getText().toString());
                            quiz.setDescription(etQuizDescription.getText().toString());


                            viewModel.updateQuiz(quiz).observe(getActivity(), new Observer<Integer>() {
                                DialogEventArgs dialogEventArgs = new DialogEventArgs();

                                @Override
                                public void onChanged(@Nullable Integer count) {

                                    if(count > 0){
                                        dialogEventArgs.setId(quizId);
                                        dialogEventArgs.setStatus("updated");
                                        mListener.onDialogPositiveClick(QuizListEditDialogFragment.this,dialogEventArgs);
                                    }

                                }
                            });
                        } else {
                            // create new quiz
                            Quiz quiz = new Quiz();
                            quiz.setName(etQuizName.getText().toString());
                            quiz.setDescription(etQuizDescription.getText().toString());

                            Timber.d("--- insert quiz :  %s ",quiz);


                            viewModel.insertQuiz(quiz).observe(getActivity(), new Observer<Long>() {
                                DialogEventArgs dialogEventArgs = new DialogEventArgs();

                                @Override
                                public void onChanged(@Nullable Long quizId) {

                                    if(quizId != null){
                                        dialogEventArgs.setId(quizId);
                                        dialogEventArgs.setStatus("insert");

                                        // Send the positive button event back to the host activity
                                        mListener.onDialogPositiveClick(QuizListEditDialogFragment.this,dialogEventArgs);
                                    }
                                }
                            });
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DialogEventArgs dialogEventArgs = new DialogEventArgs();

                        dialogEventArgs.setStatus("cancelled");

                        // Send the negative button event back to the host activity
                        mListener.onDialogNegativeClick(QuizListEditDialogFragment.this,dialogEventArgs);

                        Timber.d("inside show quiz dialog- setNegativeButton clicked ");
                        //----------------------------------------------------------
                    }
                });
        return builder.create();
    }






    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */

    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, DialogEventArgs dialogEventArgs);
        public void onDialogNegativeClick(DialogFragment dialog,DialogEventArgs dialogEventArgs);
    }
    public class DialogEventArgs
    {
        //you can put other properties here that may be relevant to check from activity
        //for example: if a cancel button was clicked, other text values, etc.
        Long Id;

        //updated, deleted, inserted, cancelled
        String status;

        public Long getId() {
            return Id;
        }

        public void setId(Long id) {
            Id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

}