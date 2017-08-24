package no.triki.waffeltown.waffels;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import no.triki.waffeltown.R;
import no.triki.waffeltown.models.Waffel;

/**
 * Created by Jonas on 24/08/2017.
 */

public class WaffelListAdapter extends RecyclerView.Adapter<WaffelViewHolder> implements View.OnClickListener {

    private Context context;
    private ArrayList<Waffel> data;
    private WaffelItemListener listener;
    private SimpleDateFormat sdf;

    public WaffelListAdapter(Context context, ArrayList<Waffel> data, WaffelItemListener listener) {
        this.context = context;
        this.data = data;
        this.listener = listener;
        sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
    }

    @Override
    public WaffelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WaffelViewHolder(LayoutInflater.from(context).inflate(R.layout.waffel_card, parent, false));
    }

    @Override
    public void onBindViewHolder(WaffelViewHolder holder, int position) {
        Waffel curWaffel = data.get(position);

        // Load data from arraylist -> view.
        Picasso.with(context).load(curWaffel.getImageUrl()).into(holder.ivWaffel);
        holder.waffelRating.setNumStars(5);
        holder.waffelRating.setRating(curWaffel.getRating());
        holder.waffelRating.setIsIndicator(true);
        holder.tvDate.setText(sdf.format(curWaffel.getDate()));
        holder.tvDescription.setText(curWaffel.getDescription());
        holder.ivUpvote.setTag(curWaffel);
        holder.ivUpvote.setOnClickListener(this);
        holder.tvVotes.setText(String.format(Locale.ENGLISH, "%d", curWaffel.getUpwaffels()));
        holder.ivDownvote.setTag(curWaffel);
        holder.ivDownvote.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivUpvote:
                listener.onUpvoteClicked((Waffel)view.getTag());
                break;
            case R.id.ivDownvote:
                listener.onDownvoteClicked((Waffel)view.getTag());
                break;
        }
    }
}
