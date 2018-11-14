package csc472.depaul.edu.micvalmoy;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import csc472.depaul.edu.micvalmoy.db.AppDatabase;

import csc472.depaul.edu.micvalmoy.entity.Category;
import csc472.depaul.edu.micvalmoy.entity.QuizWithCategory;
import csc472.depaul.edu.micvalmoy.entity.Quiz;

import csc472.depaul.edu.micvalmoy.mock.FakeQuizData;
import csc472.depaul.edu.micvalmoy.repository.QuizRepository;
import timber.log.Timber;

public class EditQuizFragment extends Fragment implements OnClickListener, OnItemSelectedListener {

    public static final String EXTRA_QUIZ_ID = "EXTRA_QUIZ_ID";
    private Long quizId;
    private Quiz quiz;

    private AppDatabase appDatabase;
    private TextView tvTitle;
    private TextView tvDescription;
    private TextView tvCategory;
    private Spinner categorySpinner;



    private AdapterCategorySpinnerItem adapterCategorySpinnerItem;


    private EditQuizViewModel viewModel;
    QuizRepository quizRepository = null;


    public static Bundle newInstanceBundle(long quizId) {
        Bundle bundle = new Bundle();
        bundle.putLong(EXTRA_QUIZ_ID, quizId);
        return bundle;
    }

    public static EditQuizFragment newInstance(long quizId) {
        EditQuizFragment fragment = new EditQuizFragment();
        Bundle args = new Bundle();
        if (args != null) {
            args.putLong(EXTRA_QUIZ_ID, quizId);
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            quizId = getArguments().getLong(EXTRA_QUIZ_ID);
        }

        Timber.d("quizId:  %s", quizId);

        quizRepository = QuizRepository.getInstance(getActivity().getApplicationContext());
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_edit_quiz, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        tvTitle       = (TextView) view.findViewById(R.id.tvTitle);
        tvDescription = (TextView) view.findViewById(R.id.tvDescription);
        tvCategory    = (TextView) view.findViewById(R.id.tvCategory);

        view.findViewById(R.id.save).setOnClickListener(this);


        categorySpinner = (Spinner) view.findViewById(R.id.category_spinner);
        categorySpinner.setOnItemSelectedListener(this);


        // Set up your view model
        viewModel = ViewModelProviders.of(this).get(EditQuizViewModel.class);

        //**************************************************************************************
        // Observe the view model
            /*
            viewModel.getQuizByTermLiveData().observe(this, new Observer<List<Quiz>>() {
                @Override
                public void onChanged(List<Quiz> quizzes) {

                    Timber.d("listing the quizzes ");
                }
            });
            */
    }



    @Override
    public void onResume() {
        super.onResume();
        loadCategories();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                saveQuiz();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            tvCategory.setVisibility(View.VISIBLE);
        } else {
            tvCategory.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

/*

    private void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        title.setText(quiz.getName());
         tvDescription.setText(quiz.getDescription());
        int categoryPosition = adapter.getCategoryPosition(quiz.getCategoryId());
        if (categoryPosition > 0) {
            categorySpinner.setSelection(categoryPosition);
        }
    }
*/


    private void loadCategories() {

        setDemoCategories();

        /*
        viewModel.getCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category>categories) {
                setCategories(categories);
                if (quizId != null) {
                    viewModel.loadQuiz();
                }
            }
        });*/
    }

    private void setDemoCategories() {

        //TODO - delete soon this is fake category data
        FakeQuizData fakeQuizData = new FakeQuizData();
        List<Category> tagsNames = fakeQuizData.getFakeCategoryList(8);

        adapterCategorySpinnerItem = new AdapterCategorySpinnerItem(getActivity().getApplicationContext(), 0, tagsNames,categorySpinner);
        categorySpinner.setAdapter(adapterCategorySpinnerItem);
    }

    private void setCategories(List<csc472.depaul.edu.micvalmoy.entity.Category> categories) {
        adapterCategorySpinnerItem = new AdapterCategorySpinnerItem(getActivity().getApplicationContext(), 0, categories,categorySpinner);
        categorySpinner.setAdapter(adapterCategorySpinnerItem);
    }

    private void saveQuiz(){
        QuizWithCategory quizWithCategory = new QuizWithCategory();
        List<csc472.depaul.edu.micvalmoy.entity.Category> categories = quizWithCategory.getCategories();
        List<Category> selectedCategories = new ArrayList<>();


        String quizName           = tvTitle.getText().toString().trim();
        String quizDescription    = tvDescription.getText().toString().trim();
        final String categoryName = tvCategory.getText().toString().trim();

        int countCatSelected = adapterCategorySpinnerItem.getSelectedCount();
        SparseBooleanArray selectedCategoryIds = adapterCategorySpinnerItem.getSelectedIds();

        selectedCategories = adapterCategorySpinnerItem.getSelectedItems();

        Timber.d("countCatSelected:  %s", countCatSelected);
        Timber.d("selectedCategories id:  %s", selectedCategoryIds);
        Timber.d("selectedCategories  %s",selectedCategories);

/*
        Category selectedCategory = adapterCategorySpinnerItem.getItem(categorySpinner.getSelectedItemPosition());
        final Long selectedCategoryId = (selectedCategory == null || selectedCategory.getId() <= 0 || selectedCategory.getId()==null) ? null : selectedCategory.getId();
*/


        quizWithCategory.setName(quizName);
        quizWithCategory.setDescription(quizDescription);

        csc472.depaul.edu.micvalmoy.entity.Category categoryObj = new csc472.depaul.edu.micvalmoy.entity.Category();
        //categoryObj.setId(selectedCategoryId);
        categoryObj.setName(categoryName);
        categoryObj.setSelected(true);

        quizWithCategory.addCategory(categoryObj);

        Timber.d("quiz data:  %s", quizWithCategory);
        //viewModel.saveQuiz(quizWithCategory);
    }
}
