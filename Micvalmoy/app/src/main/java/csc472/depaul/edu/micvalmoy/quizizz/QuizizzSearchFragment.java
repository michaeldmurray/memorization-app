package csc472.depaul.edu.micvalmoy.quizizz;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
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
import timber.log.Timber;


import android.widget.Toast;

import java.util.ArrayList;

//** Moya - https://guides.codepath.com/android/creating-and-using-fragments

public class QuizizzSearchFragment extends Fragment implements View.OnClickListener{

    private QuizizzViewModel viewModel;
    QuizizzRepository quizizzRepository = QuizizzRepository.getInstance();

    public static final String EXTRA_PARM1 = "QuizizzSearchParameter";
    String quizizzSearchParm = null;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment QuizizzSearchFragment.
     */

    public static QuizizzSearchFragment newInstance(String param1) {
        QuizizzSearchFragment fragment = new QuizizzSearchFragment();
        Bundle args = new Bundle();
        if (args != null) {
            args.putString(EXTRA_PARM1, param1);
            fragment.setArguments(args);
        }
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            quizizzSearchParm = getArguments().getString(EXTRA_PARM1);
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

            Toast.makeText(getActivity().getApplicationContext(), "Please wait ... loading quizzes", Toast.LENGTH_LONG).show();


            // Set up your view model
            viewModel = ViewModelProviders.of(this).get(QuizizzViewModel.class);

            //**************************************************************************************
            // Observe the view model
            viewModel.getQuizizzByTermLiveData().observe(this, new Observer<ArrayList<QuizInfo>>() {
                @Override
                public void onChanged(ArrayList<QuizInfo> quizzes) {

                    /**
                     * work with the data provided through the view model here,delivering UI updates.
                     * load custom list adapter to show the list of quizzes found on quizizz.com
                     */

                    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.quizizzRecyclerView);
                    RecyclerView.LayoutManager quizizzRecyclerLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                    recyclerView.setLayoutManager(quizizzRecyclerLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                    QuizizzRecyclerViewAdapter quizizzRecyclerViewAdapter = new QuizizzRecyclerViewAdapter(quizzes);
                    recyclerView.setAdapter(quizizzRecyclerViewAdapter);

                    Timber.d("listing the quizzes found on quizizz.com");
                }
            });



            /**
             * Start Background process
             * This will start the Background off-the-UI-thread work to search quizizz.com based on the search parameter provided
             */
            quizizzRepository.findQuizizzByTerm(quizizzSearchParm);


        }





    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSearchQuizizz:

/*                Intent intent = new Intent(getActivity(), QuizizzActivity.class);
                intent.putExtra("QuizizzSearchParameter", etQuizizzSrchParm.getText());
                startActivity(intent);*/

                break;
            default:
                break;
        }

        Timber.d("onClick -- button ="+v.getId());
    }

}