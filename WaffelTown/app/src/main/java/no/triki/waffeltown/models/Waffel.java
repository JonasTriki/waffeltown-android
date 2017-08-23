package no.triki.waffeltown.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

import no.triki.waffeltown.network.ApiManager;

/**
 * Created by Jonas Triki on 22.08.2017.
 */

public class Waffel implements Parcelable {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("date")
    @Expose
    private Date date;

    @SerializedName("comments")
    @Expose
    private ArrayList<WaffelComment> comments;

    @SerializedName("upwaffels")
    @Expose
    private int upwaffels;

    @SerializedName("upwaffelDevices")
    @Expose
    private ArrayList<WaffelDevice> upwaffelDevices;

    @SerializedName("rating")
    @Expose
    private int rating;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("topping")
    @Expose
    private String topping;

    @SerializedName("consistency")
    @Expose
    private int consistency;

    @SerializedName("image")
    @Expose
    private String imageUrl;

    protected Waffel(Parcel in) {
        id = in.readString();
        upwaffels = in.readInt();
        rating = in.readInt();
        description = in.readString();
        topping = in.readString();
        consistency = in.readInt();
        imageUrl = in.readString();
    }

    public static final Creator<Waffel> CREATOR = new Creator<Waffel>() {
        @Override
        public Waffel createFromParcel(Parcel in) {
            return new Waffel(in);
        }

        @Override
        public Waffel[] newArray(int size) {
            return new Waffel[size];
        }
    };

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<WaffelComment> getComments() {
        return comments;
    }

    public int getUpwaffels() {
        return upwaffels;
    }

    public ArrayList<WaffelDevice> getUpwaffelDevices() {
        return upwaffelDevices;
    }

    public int getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public String getTopping() {
        return topping;
    }

    public int getConsistency() {
        return consistency;
    }

    public String getImageUrl() {
        return ApiManager.WAFFEL_TOWN_URL + imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeInt(upwaffels);
        parcel.writeInt(rating);
        parcel.writeString(description);
        parcel.writeString(topping);
        parcel.writeInt(consistency);
        parcel.writeString(imageUrl);
    }
}
