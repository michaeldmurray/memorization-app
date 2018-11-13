package csc472.depaul.edu.micvalmoy;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import csc472.depaul.edu.micvalmoy.entity.Quiz;
import timber.log.Timber;


import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

//** Moya - https://guides.codepath.com/android/creating-and-using-fragments

public class QuizListFragment extends Fragment implements View.OnClickListener{


    private QuizViewModel viewModel;
    String quizListParm = null;

    public static final String EXTRA_QUIZ_ID = "EXTRA_QUIZ_ID";

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param quizListParameter The search argument.
     * @return A new instance of fragment QuizListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizListFragment newInstance(String quizListParameter) {
        QuizListFragment fragment = new QuizListFragment();
        Bundle args = new Bundle();
        args.putString("QuizListParameter", quizListParameter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            quizListParm = getArguments().getString("QuizListParameter");
        }
    }


    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Enable timber for logging
        if (BuildConfig.DEBUG || BuildConfig.FLAVOR.equals("withlog")) {
            //Remove all planted trees.
            Timber.uprootAll();

            Timber.plant(new TimberDebugTree());
        }
        //TODO: Add a crash analytics tool for production
        //**************************************************************

        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_quiz_list, parent, false);
    }


    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

            Toast.makeText(getActivity().getApplicationContext(), "Please wait ... loading quizzes", Toast.LENGTH_LONG).show();

            // Set up your view model
            viewModel = ViewModelProviders.of(this).get(QuizViewModel.class);


            //TODO setup quiz recycler view
            //**************************************************************************************
            // Observe the view model
            viewModel.getQuizzes().observe(this, new Observer<List<Quiz>>() {
                @Override
                public void onChanged(List<Quiz> quizzes) {


                    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.quizListRecyclerView);
                    RecyclerView.LayoutManager quizRecyclerLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                    recyclerView.setLayoutManager(quizRecyclerLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                    QuizListRecyclerViewAdapter quizListRecyclerViewAdapter = new QuizListRecyclerViewAdapter(quizzes);
                    recyclerView.setAdapter(quizListRecyclerViewAdapter);

                    Timber.d("listing the quizzes found in database");
                }
            });


            /**
             * TODO: if no quizzes available, show button to add new quiz
             */
            Timber.d("now in quiz list fragment");

    }








    @Override
    public void onClick(View v) {
        switch (v.getId()) {
/*            case R.id.btnLoadQuiz:
                EditText etQuizEdit = (EditText) v.findViewById(R.id.etQuizEdit);
                Intent intent = new Intent(getActivity(), QuizActivity.class);
                intent.putExtra(EXTRA_QUIZ_ID, etQuizLoad.getText());
                startActivity(intent);

                break;

            case R.id.btnEditQuiz:
                EditText etQuizEdit = (EditText) v.findViewById(R.id.etQuizEdit);
                Intent intent = new Intent(getActivity(), EditQuizActivity.class);
                intent.putExtra(EXTRA_QUIZ_ID, etQuizEdit.getText());
                startActivity(intent);

                break;*/
            default:

                break;
        }
        Timber.d("onClick -- button ="+v.getId());
    }

}