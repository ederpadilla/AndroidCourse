package dev.eder.androidcourse.db.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.eder.androidcourse.R;
import dev.eder.androidcourse.db.model.DogShop;
import io.realm.RealmList;

public class ShopsAdapter extends RecyclerView.Adapter<ShopsAdapter.ItemViewHolderAdapter> {

    private RealmList<DogShop> modelList;

    private Context adapterContext;

    private ShopLongClicked shopLongClicked;

    private ShopClick shopClick;

    public ShopsAdapter(RealmList<DogShop> modelList, Context adapterContext, ShopLongClicked shopLongClicked, ShopClick shopClick) {
        this.modelList = modelList;
        this.adapterContext = adapterContext;
        this.shopLongClicked = shopLongClicked;
        this.shopClick = shopClick;
    }

    @NonNull
    @Override
    public ItemViewHolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        adapterContext = parent.getContext();
        View view = LayoutInflater.from(adapterContext).inflate(R.layout.item_dog_shop, parent, false);
        return new ItemViewHolderAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolderAdapter holder, int position) {
        DogShop model = modelList.get(position);
        holder.bindMyItemView(model);
        holder.rootView.setOnClickListener(v -> shopClick.onDogShopClicked(model));
        holder.rootView.setOnLongClickListener(v -> {
            shopLongClicked.onLongShopClick(model);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


    public class ItemViewHolderAdapter extends RecyclerView.ViewHolder {

        @BindView(R.id.imageViewDogShop) ImageView mImgModel;

        @BindView(R.id.textViewDogShopName)TextView mTvModelName;

        @BindView(R.id.textViewAddress)TextView mTvModelType;

        View rootView;

        public ItemViewHolderAdapter(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            rootView = itemView;
        }

        @SuppressLint("SetTextI18n")
        public void bindMyItemView(DogShop dogShop){
            Glide.with(adapterContext).load(dogShop.image).centerCrop().crossFade(1500).into(mImgModel);
            mTvModelName.setText("Nombre : "+dogShop.name);
            mTvModelType.setText("Dirección : "+dogShop.address);
        }
    }
}