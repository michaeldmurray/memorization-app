package csc472.depaul.edu.micvalmoy.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

public class QuestionActivity extends DescendantActivity {

    // Passing the below information through bundles seems lightweight.
    // We could instead make Question class implement Parcelable and pass the instance.
    public static final String MODE = "question mode";
    public static final String ID = "question id";

    public enum mode {
        QUIZ,
        PRACTICE,
        RESULT
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
