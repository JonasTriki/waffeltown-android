package no.triki.waffeltown.network;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import no.triki.waffeltown.models.Waffel;
import no.triki.waffeltown.models.WaffelComment;
import no.triki.waffeltown.models.WaffelData;
import no.triki.waffeltown.models.WaffelRestTypes;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jonas Triki on 22.08.2017.
 */

public class WaffelDataReceiver {

    private OnWaffelDataReceivedListener listener;

    public WaffelDataReceiver(OnWaffelDataReceivedListener listener) {
        this.listener = listener;
    }

    public void getWaffels() {
        ApiManager.getWaffelTownService().getWaffels().enqueue(new Callback<WaffelData<ArrayList<Waffel>>>() {

            @Override
            public void onResponse(Call<WaffelData<ArrayList<Waffel>>> call, Response<WaffelData<ArrayList<Waffel>>> response) {
                if (response.isSuccessful()) {
                    listener.onWaffelDataReceived(response.body(), WaffelRestTypes.GET_WAFFELS);
                }
            }

            @Override
            public void onFailure(Call<WaffelData<ArrayList<Waffel>>> call, Throwable t) {
                listener.onWaffelDataError(t, WaffelRestTypes.GET_WAFFELS);
            }
        });
    }

    public void getWaffel(String id) {
        ApiManager.getWaffelTownService().getWaffel(id).enqueue(new Callback<WaffelData<Waffel>>() {

            @Override
            public void onResponse(Call<WaffelData<Waffel>> call, Response<WaffelData<Waffel>> response) {
                if (response.isSuccessful()) {
                    listener.onWaffelDataReceived(response.body(), WaffelRestTypes.GET_WAFFEL);
                }
            }

            @Override
            public void onFailure(Call<WaffelData<Waffel>> call, Throwable t) {
                listener.onWaffelDataError(t, WaffelRestTypes.GET_WAFFEL);
            }
        });
    }

    public void recognizeWaffel(RequestBody image) {
        ApiManager.getWaffelTownService().recognizeWaffel(image).enqueue(new Callback<WaffelData<Boolean>>() {

            @Override
            public void onResponse(Call<WaffelData<Boolean>> call, Response<WaffelData<Boolean>> response) {
                if (response.isSuccessful()) {
                    listener.onWaffelDataReceived(response.body(), WaffelRestTypes.RECOGNIZE_WAFFEL);
                }
            }

            @Override
            public void onFailure(Call<WaffelData<Boolean>> call, Throwable t) {
                t.printStackTrace();
                listener.onWaffelDataError(t, WaffelRestTypes.RECOGNIZE_WAFFEL);
            }
        });
    }

    public void postWaffel(int rating, String description, String topping, int consistency, RequestBody image) {
        ApiManager.getWaffelTownService().postWaffel(rating, description, topping, consistency, image).enqueue(new Callback<WaffelData<Waffel>>() {

            @Override
            public void onResponse(Call<WaffelData<Waffel>> call, Response<WaffelData<Waffel>> response) {
                if (response.isSuccessful()) {
                    listener.onWaffelDataReceived(response.body(), WaffelRestTypes.POST_WAFFEL);
                }
            }

            @Override
            public void onFailure(Call<WaffelData<Waffel>> call, Throwable t) {
                listener.onWaffelDataError(t, WaffelRestTypes.POST_WAFFEL);
            }
        });
    }

    public void upwaffel(String id, String deviceId) {
        ApiManager.getWaffelTownService().upwaffel(id, deviceId).enqueue(new Callback<WaffelData<Boolean>>() {

            @Override
            public void onResponse(Call<WaffelData<Boolean>> call, Response<WaffelData<Boolean>> response) {
                if (response.isSuccessful()) {
                    listener.onWaffelDataReceived(response.body(), WaffelRestTypes.UPWAFFEL);
                }
            }

            @Override
            public void onFailure(Call<WaffelData<Boolean>> call, Throwable t) {
                listener.onWaffelDataError(t, WaffelRestTypes.UPWAFFEL);
            }
        });
    }

    public void downwaffel(String id, String deviceId) {
        ApiManager.getWaffelTownService().downwaffel(id, deviceId).enqueue(new Callback<WaffelData<Boolean>>() {

            @Override
            public void onResponse(Call<WaffelData<Boolean>> call, Response<WaffelData<Boolean>> response) {
                if (response.isSuccessful()) {
                    listener.onWaffelDataReceived(response.body(), WaffelRestTypes.DOWNWAFFEL);
                }
            }

            @Override
            public void onFailure(Call<WaffelData<Boolean>> call, Throwable t) {
                listener.onWaffelDataError(t, WaffelRestTypes.DOWNWAFFEL);
            }
        });
    }

    public void comment(String id, String comment) {
        ApiManager.getWaffelTownService().comment(id, comment).enqueue(new Callback<WaffelData<WaffelComment>>() {

            @Override
            public void onResponse(Call<WaffelData<WaffelComment>> call, Response<WaffelData<WaffelComment>> response) {
                if (response.isSuccessful()) {
                    listener.onWaffelDataReceived(response.body(), WaffelRestTypes.COMMENT_WAFFEL);
                }
            }

            @Override
            public void onFailure(Call<WaffelData<WaffelComment>> call, Throwable t) {
                listener.onWaffelDataError(t, WaffelRestTypes.COMMENT_WAFFEL);
            }
        });
    }
}
