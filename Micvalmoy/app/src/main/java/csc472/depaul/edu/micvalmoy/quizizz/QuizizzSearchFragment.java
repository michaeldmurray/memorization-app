package csc472.depaul.edu.micvalmoy.quizizz;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import csc472.depaul.edu.micvalmoy.BuildConfig;
import csc472.depaul.edu.micvalmoy.R;
import csc472.depaul.edu.micvalmoy.TimberDebugTree;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.QuizInfo;
import csc472.depaul.edu.micvalmoy.tools.IntentUtil;
import timber.log.Timber;


import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//** Moya - https://guides.codepath.com/android/creating-and-using-fragments

public class QuizizzSearchFragment extends Fragment implements View.OnClickListener, QuizizzRecyclerViewAdapter.OnClickItemAdapterListener {

    private QuizizzViewModel viewModel;


    RecyclerView recyclerView;
    QuizizzRecyclerViewAdapter mAdapter;

    QuizizzRepository quizizzRepository = QuizizzRepository.getInstance();


    String quizizzSearchParm = null;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param searchParameter The search parameter used to get quizzes from quizzizz.com.
     * @return A new instance of fragment QuizizzSearchFragment.
     */

    public static QuizizzSearchFragment newInstance(String searchParameter) {
        QuizizzSearchFragment fragment = new QuizizzSearchFragment();
        Bundle args = new Bundle();
        if (args != null) {
            args.putString(IntentUtil.EXTRA_SEARCH_PARAMETER, searchParameter);
            fragment.setArguments(args);
        }
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            quizizzSearchParm = getArguments().getString(IntentUtil.EXTRA_SEARCH_PARAMETER);
        }

        Timber.d("quizizzSearchParm:  %s", quizizzSearchParm);
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
        return inflater.inflate(R.layout.fragment_quizizz_search, parent, false);
    }




    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        if(quizizzSearchParm != null) {

            Toast.makeText(getActivity(), "Please wait ...", Toast.LENGTH_SHORT).show();


            // Set up your view model
            viewModel = ViewModelProviders.of(this).get(QuizizzViewModel.class);


            recyclerView    = (RecyclerView) view.findViewById(R.id.quizizzRecyclerView);

            mAdapter = new QuizizzRecyclerViewAdapter (getActivity(),this);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);

            Toast.makeText(getActivity(), getString(R.string.load_quiz), Toast.LENGTH_SHORT).show();

            //**************************************************************************************
            // Observe the view model
            viewModel.getQuizizzByTermLiveData(quizizzSearchParm).observe(this, new Observer<List<QuizInfo>>() {
                @Override
                public void onChanged(@Nullable List<QuizInfo> quizInfos) {
                    mAdapter.submitList(quizInfos);

                    Timber.d("listing the quizzes found on quizizz.com");
                }
            });




        }
        else{
            Toast.makeText(getActivity(), "Sorry there was an error loading quizzes", Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*
           case R.id.btnCategoryEdit:

                Intent intent = new Intent(getActivity(), QuizizzActivity.class);
                intent.putExtra(IntentUtil.EXTRA_SEARCH_PARAMETER, etQuizizzSrchParm.getText());
                startActivity(intent);

                break;
            */
            default:
                break;
        }

        Timber.d("onClick -- button ="+v.getId());
    }


    //-----------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------
    //QuizizzRecyclerViewAdapter

    @Override
    public void onClick(String quizId, int position) {

        Intent intent = new Intent(getActivity(), EditQuizizzActivity.class);
        intent.putExtras(EditQuizizzActivity.newInstanceBundle(quizId));
        startActivity(intent);
    }

    @Override
    public void onLongClick(String quizId, int position) {

    }

    /**
     * ---------------------------------------------------------------------------------
     * Moya - State to handle Rotation
     * onSaveInstanceState gets called before onStop but it is not guaranteed to be called before or after onPause.
     * Android will also only call it when the application needs to save temporary state which includes when
     * orientation changes occur and when the Activity is killed for its memory resources.
     * It will not be called in certain situations such as finishing an Activity normally or putting an Activity into the background
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(IntentUtil.EXTRA_SEARCH_PARAMETER, quizizzSearchParm);
    }



}