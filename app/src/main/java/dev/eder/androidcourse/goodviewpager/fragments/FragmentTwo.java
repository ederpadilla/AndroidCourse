package dev.eder.androidcourse.goodviewpager.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.eder.androidcourse.Util.KeysConstants;
import dev.eder.androidcourse.R;

public class FragmentTwo extends Fragment {

    @BindView(R.id.textViewFragmentTwo) TextView mTextViewFragmentTwo;

    public static String TAG = FragmentTwo.class.getSimpleName();

    String message;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        message = getArguments().getString(KeysConstants.FRAGMENT_ARG_FLAG);
        Log.e(TAG,"Message received "+message);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_two, container, false);

        ButterKnife.bind(this,view);
        mTextViewFragmentTwo.setText(message);

        return view;
    }

    public static FragmentTwo newInstance(String someString) {
        FragmentTwo myFragment = new FragmentTwo();
        Bundle args = new Bundle();
        args.putString(KeysConstants.FRAGMENT_ARG_FLAG, someString);
        myFragment.setArguments(args);

        return myFragment;
    }
}
