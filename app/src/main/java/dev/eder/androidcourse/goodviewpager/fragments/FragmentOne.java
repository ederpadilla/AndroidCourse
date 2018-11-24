package dev.eder.androidcourse.goodviewpager.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.eder.androidcourse.R;

public class FragmentOne extends Fragment {

    @BindView(R.id.imageViewFragmentOne) ImageView mImgFragmentOne;

    public static String TAG = FragmentOne.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_one, container, false);

        /*ButterKnife.bind(this,view);*/

        Glide.with(getActivity())
                .load("https://media.metrolatam.com/2018/05/04/avengersinfinitywarposter-8282957db451c823784175c5d10797ff-1200x600.jpg")
                .crossFade(1500)
                .into(mImgFragmentOne);
        return view;
    }

    public static FragmentOne newInstance() {
        FragmentOne myFragment = new FragmentOne();
        return myFragment;
    }
}
