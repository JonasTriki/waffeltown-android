package no.triki.waffeltown.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jonas Triki on 22.08.2017.
 */

public class WaffelDevice {

    @SerializedName("_id")
    @Expose
    private String id;

    public String getId() {
        return id;
    }
}
