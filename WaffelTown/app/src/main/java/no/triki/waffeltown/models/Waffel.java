package no.triki.waffeltown.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

import no.triki.waffeltown.network.ApiManager;

/**
 * Created by Jonas Triki on 22.08.2017.
 */

public class Waffel {

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
}
