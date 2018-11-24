package dev.eder.androidcourse.goodviewpager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.eder.androidcourse.R;
import dev.eder.androidcourse.Util.Util;
import dev.eder.androidcourse.goodviewpager.fragments.FragmentOne;
import dev.eder.androidcourse.goodviewpager.fragments.FragmentThree;
import dev.eder.androidcourse.goodviewpager.fragments.FragmentTwo;

public class GoodViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final String TAG = GoodViewPagerActivity.class.getSimpleName();

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_view_pager);
        ButterKnife.bind(this);
        setUpViewPager();
    }

    private void setUpViewPager() {
        GoodPagerAdapter goodPagerAdapter = new GoodPagerAdapter(getSupportFragmentManager());
        goodPagerAdapter.addFragment(FragmentOne.newInstance(),FragmentOne.TAG);
        goodPagerAdapter.addFragment(FragmentTwo.newInstance("Prueba de enviar datos a fragments"),
                FragmentTwo.TAG);
        goodPagerAdapter.addFragment(FragmentThree.newInstance(),FragmentThree.TAG);
        viewPager.setAdapter(goodPagerAdapter);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        Log.e(TAG,"Page selected "+i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        Util.showLog(TAG,"on page scroll "+i);
    }
}
