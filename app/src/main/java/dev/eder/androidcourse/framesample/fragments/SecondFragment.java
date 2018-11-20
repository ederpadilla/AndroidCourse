package dev.eder.androidcourse.framesample.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import dev.eder.androidcourse.R;

public class SecondFragment extends Fragment {


    public static String TAG = SecondFragment.class.getSimpleName();

    Button mBuTwo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        mBuTwo = view.findViewById(R.id.buttonTwo);
        mBuTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Hello second fragment",
                        Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
