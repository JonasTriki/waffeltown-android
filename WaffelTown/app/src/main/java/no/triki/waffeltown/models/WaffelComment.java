package no.triki.waffeltown.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Jonas Triki on 22.08.2017.
 */

public class WaffelComment implements Parcelable {

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("date")
    @Expose
    private Date date;

    protected WaffelComment(Parcel in) {
        text = in.readString();
        date = new Date(in.readLong());
    }

    public static final Creator<WaffelComment> CREATOR = new Creator<WaffelComment>() {
        @Override
        public WaffelComment createFromParcel(Parcel in) {
            return new WaffelComment(in);
        }

        @Override
        public WaffelComment[] newArray(int size) {
            return new WaffelComment[size];
        }
    };

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeLong(date.getTime());
    }
}
