package no.triki.waffeltown.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jonas Triki on 22.08.2017.
 */

public class WaffelDevice implements Parcelable {

    @SerializedName("_id")
    @Expose
    private String id;

    protected WaffelDevice(Parcel in) {
        id = in.readString();
    }

    public static final Creator<WaffelDevice> CREATOR = new Creator<WaffelDevice>() {
        @Override
        public WaffelDevice createFromParcel(Parcel in) {
            return new WaffelDevice(in);
        }

        @Override
        public WaffelDevice[] newArray(int size) {
            return new WaffelDevice[size];
        }
    };

    public String getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
    }
}
