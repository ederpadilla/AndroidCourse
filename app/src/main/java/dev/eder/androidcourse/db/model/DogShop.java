package dev.eder.androidcourse.db.model;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class DogShop extends RealmObject {

    public DogShop() {
    }

    public DogShop(String dogShopId, String name, String image, String address) {
        this.dogShopId = dogShopId;
        this.name = name;
        this.image = image;
        this.address = address;
    }

    @PrimaryKey
    public String dogShopId;

    public String name;

    public String image;

    public String address;

    @Override
    public String toString() {
        return "DogShop{" +
                "dogShopId='" + dogShopId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
