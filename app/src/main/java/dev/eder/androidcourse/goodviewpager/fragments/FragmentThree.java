package dev.eder.androidcourse.goodviewpager.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.eder.androidcourse.R;

public class FragmentThree extends Fragment {

    public static String TAG = FragmentThree.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_three, container, false);

        ButterKnife.bind(this,view);

        return view;
    }

    @OnClick(R.id.buttonFragmentThree) public void click(View view){
        Toast.makeText(getActivity(),"Click frament three",Toast.LENGTH_SHORT).show();
    }

    public static FragmentThree newInstance() {
        FragmentThree myFragment = new FragmentThree();
        return myFragment;
    }
}

