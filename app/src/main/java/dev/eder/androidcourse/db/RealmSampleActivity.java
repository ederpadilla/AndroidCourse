package dev.eder.androidcourse.db;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.eder.androidcourse.R;
import dev.eder.androidcourse.Util.KeysConstants;
import dev.eder.androidcourse.Util.Util;
import dev.eder.androidcourse.db.adapter.ShopClick;
import dev.eder.androidcourse.db.adapter.ShopLongClicked;
import dev.eder.androidcourse.db.adapter.ShopsAdapter;
import dev.eder.androidcourse.db.model.DogShop;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class RealmSampleActivity extends AppCompatActivity implements ShopClick,ShopLongClicked {

    private static final String TAG = RealmSampleActivity.class.getSimpleName();

    private RealmList<DogShop> dogShops = new RealmList<>();

    @BindView(R.id.shopsRecyclerView) RecyclerView mShopsRecyclerView;

    private ShopsAdapter shopsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_sample);
        ButterKnife.bind(this);
        setUpRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getRealmObjects();
    }

    private void getRealmObjects() {
        dogShops.clear();
        shopsAdapter.notifyDataSetChanged();
        Realm realm = Realm.getDefaultInstance();
        RealmResults<DogShop> dogsShopsResults = realm.where(DogShop.class).findAll();
        Util.showLog(TAG,"dogs shops "+dogsShopsResults.toString());
        dogShops.addAll(dogsShopsResults.subList(0,dogsShopsResults.size()));
        Util.showLog(TAG,"dog shops "+dogShops.toString());
        shopsAdapter.notifyDataSetChanged();
    }

    private void setUpRecyclerView() {
        shopsAdapter = new ShopsAdapter(dogShops,RealmSampleActivity.this,this,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mShopsRecyclerView.setAdapter(shopsAdapter);
        mShopsRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onDogShopClicked(DogShop dogShop) {
        Intent intent = new Intent(this,EditShopActivity.class);
        intent.putExtra(KeysConstants.DOG_SHOP_ID,dogShop.dogShopId);
        intent.putExtra(KeysConstants.MODE_KEY,KeysConstants.EDIT_MODE);
        startActivity(intent);
    }

    @Override
    public void onLongShopClick(DogShop dogShop) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            dogShops.remove(dogShop);
            shopsAdapter.notifyDataSetChanged();
            RealmResults<DogShop> shops = realm1.where(DogShop.class).equalTo(KeysConstants.DOG_SHOP_ID,dogShop.dogShopId).findAll();
            shops.deleteAllFromRealm();
        });
    }

    @OnClick(R.id.floatingActionButton) public void addDogShop(View view){
        Intent intent = new Intent(this,EditShopActivity.class);
        intent.putExtra(KeysConstants.MODE_KEY,KeysConstants.CREATE_MODE);
        startActivity(intent);
    }
}
