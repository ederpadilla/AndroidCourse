package dev.eder.androidcourse.framesample.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import dev.eder.androidcourse.R;

public class FirstFragment extends Fragment {

    public static String TAG = FirstFragment.class.getSimpleName();

    private Button mBuOne;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        mBuOne = view.findViewById(R.id.buttonOne);
        mBuOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Hello first fragment",
                        Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
