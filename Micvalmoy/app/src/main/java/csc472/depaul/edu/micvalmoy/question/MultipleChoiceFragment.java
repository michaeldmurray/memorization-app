package csc472.depaul.edu.micvalmoy.question;
/**
 * @author mrichards
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import csc472.depaul.edu.micvalmoy.R;

public class MultipleChoiceFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question_multiple_choice, container, false);
    }
}
