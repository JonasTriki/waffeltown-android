package no.triki.waffeltown.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Jonas Triki on 22.08.2017.
 */

public class WaffelComment {

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("date")
    @Expose
    private Date date;

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }
}
