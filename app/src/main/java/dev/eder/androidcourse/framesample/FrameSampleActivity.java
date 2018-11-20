package dev.eder.androidcourse.framesample;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import dev.eder.androidcourse.R;
import dev.eder.androidcourse.framesample.fragments.FirstFragment;
import dev.eder.androidcourse.framesample.fragments.SecondFragment;

public class FrameSampleActivity extends AppCompatActivity {

    private int counter = 0;

    private String currentFragmentTag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_sample);
    }

    public void changeFragment(View view){
        if (isEvenOrOdd(counter)){
            switchToFragment(new FirstFragment(),FirstFragment.TAG);
        }else{
            switchToFragment(new SecondFragment(),SecondFragment.TAG);
        }
        counter ++;
    }

    public boolean isEvenOrOdd(int number){
        return (number % 2) == 0;
    }

    public void switchToFragment(Fragment fragment, String tag) {
            currentFragmentTag = tag;
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout, fragment, currentFragmentTag)
                    .commit();
    }
}
