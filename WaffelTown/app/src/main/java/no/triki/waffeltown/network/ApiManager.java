package no.triki.waffeltown.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import no.triki.waffeltown.models.Waffel;
import no.triki.waffeltown.models.WaffelComment;
import no.triki.waffeltown.models.WaffelData;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Jonas Triki on 22.08.2017.
 */

public class ApiManager {

    public interface WaffelTownAPI {

        @GET("/")
        Call<WaffelData<ArrayList<Waffel>>> getWaffels();

        @GET("/{id}")
        Call<WaffelData<Waffel>> getWaffel(@Path("id") String id);

        @Multipart
        @GET("/vision")
        Call<WaffelData<Boolean>> recognizeWaffel(@Part("image") RequestBody image);

        @Multipart
        @POST("/")
        Call<WaffelData<Waffel>> postWaffel(@Body int rating,
                                            @Body String description,
                                            @Body String topping,
                                            @Body int consistency,
                                            @Part("image") RequestBody image);

        @POST("/{id}/upwaffel")
        Call<WaffelData<Boolean>> upwaffel(@Path("id") String id,
                                           @Body String device_id);

        @POST("/{id}/downwaffel")
        Call<WaffelData<Boolean>> downwaffel(@Path("id") String id,
                                             @Body String device_id);

        @POST("/{id}/comment")
        Call<WaffelData<WaffelComment>> comment(@Path("id") String id,
                                                @Body String comment);
    }

    public static final String WAFFEL_TOWN_URL = "https://waffel-town.triki.no/api";

    private static Retrofit getInstance(String url) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .create();
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static WaffelTownAPI getWaffelTownService() {
        return getInstance(WAFFEL_TOWN_URL).create(WaffelTownAPI.class);
    }
}
