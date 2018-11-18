package csc472.depaul.edu.micvalmoy;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import csc472.depaul.edu.micvalmoy.entity.Quiz;
import csc472.depaul.edu.micvalmoy.repository.QuizViewModel;
import timber.log.Timber;


import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

//** Moya - https://guides.codepath.com/android/creating-and-using-fragments

public class QuizListFragment extends Fragment implements View.OnClickListener, QuizListRecyclerViewAdapter.QuizAdapterListener {
    public static final String TAG = QuizListFragment.class.getSimpleName();

    private QuizViewModel viewModel;
    RecyclerView recyclerView;
    QuizListRecyclerViewAdapter mAdapter;

    private TextView tvQuizId;
    private TextView tvQuizName;
    private TextView tvQuizDescription;
    private TextView tvEmptyQuizList;

    FloatingActionButton fabAddNewQuiz;


    public static QuizListFragment newInstance() {
        QuizListFragment fragment = new QuizListFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_quiz_list, parent, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(QuizViewModel.class);
        recyclerView    = (RecyclerView) view.findViewById(R.id.quizListRecyclerView);
        tvEmptyQuizList = (TextView) view.findViewById(R.id.tvEmptyQuizList);
        fabAddNewQuiz   = (FloatingActionButton) view.findViewById(R.id.fabAddNewQuiz);



        mAdapter = new QuizListRecyclerViewAdapter(getActivity(),this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new MyDividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        Toast.makeText(getActivity(), getString(R.string.load_quiz), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel.fetchAllQuizzes().observe(this, new Observer<List<Quiz>>() {
            @Override
            public void onChanged(@Nullable List<Quiz> quizzes) {
                mAdapter.submitList(quizzes);
                toggleEmptyQuizzes(quizzes.size());
            }
        });
    }

    private void toggleEmptyQuizzes(int size) {
        if (size > 0) {
            tvEmptyQuizList.setVisibility(View.GONE);
        } else {
            tvEmptyQuizList.setVisibility(View.VISIBLE);
        }
    }





    @Override
    public void onClick(View v) {
        switch (v.getId()) {
          case R.id.fabAddNewQuiz:

              Timber.d("onClick -- buttonfabAddNewQuiz");


                //startActivity(new Intent(getActivity(), EditQuizActivity.class));
                break;

          /*    case R.id.btnLoadQuiz:
                EditText etQuizEdit = (EditText) v.findViewById(R.id.etQuizEdit);
                Intent intent = new Intent(getActivity(), QuizActivity.class);
                intent.putExtra(IntentUtil.EXTRA_QUIZ_LIST_ID, etQuizLoad.getText());
                startActivity(intent);

                break;

            case R.id.btnEditQuiz:
                EditText etQuizEdit = (EditText) v.findViewById(R.id.etQuizEdit);
                Intent intent = new Intent(getActivity(), EditQuizActivity.class);
                intent.putExtra(IntentUtil.EXTRA_QUIZ_LIST_ID, etQuizEdit.getText());
                startActivity(intent);

                break;*/
            default:

                break;
        }
        Timber.d("onClick -- button ="+v.getId());
    }







    public void showQuizDialog2(final boolean shouldUpdate, final Quiz quiz, final int position) {
        Timber.d("inside show quiz dialog- shouldUpdate %s",shouldUpdate);

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
        View view = layoutInflaterAndroid.inflate(R.layout.quiz_edit_dialog, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getContext());
        alertDialogBuilderUserInput.setView(view);

        final EditText etQuizDescription = view.findViewById(R.id.etDescription);
        final EditText etQuizName = view.findViewById(R.id.etQuizName);

        TextView dialogTitle = view.findViewById(R.id.tvdialogTitle);
        dialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_quiz) : getString(R.string.lbl_edit_quiz));

        if (shouldUpdate && quiz != null) {
            // append sets text to EditText and places the cursor at the end
            tvQuizDescription.append(quiz.getDescription());
        }
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(shouldUpdate ? getString(R.string.update) : getString(R.string.save), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                    }
                })
                .setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message when no text is entered
                if (TextUtils.isEmpty(tvQuizDescription.getText().toString())) {
                    Toast.makeText(getActivity(), getString(R.string.dialog_title_enter_quiz), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    alertDialog.dismiss();
                }

                // check if user updating quiz
                if (shouldUpdate && quiz != null) {
                    quiz.setName(tvQuizName.getText().toString());
                    quiz.setDescription(tvQuizDescription.getText().toString());
                    viewModel.updateQuiz(quiz);
                } else {
                    // create new quiz
                    Quiz quiz = new Quiz();
                    quiz.setName(tvQuizName.getText().toString());
                    quiz.setDescription(tvQuizDescription.getText().toString());
                    viewModel.insertQuiz(quiz);
                }
            }
        });
    }


    /**
     *     QuizAdapterListener
     */

    @Override
    public void onClick(Long quizId, int position) {

    }

    @Override
    public void onLongClick(Long quizId, int position) {

    }
}