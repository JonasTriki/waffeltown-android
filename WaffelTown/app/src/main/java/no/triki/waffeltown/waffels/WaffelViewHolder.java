package no.triki.waffeltown.waffels;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import no.triki.waffeltown.R;

/**
 * Created by Jonas on 24/08/2017.
 */

public class WaffelViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ivWaffel)
    ImageView ivWaffel;

    @BindView(R.id.waffelRating)
    RatingBar waffelRating;

    @BindView(R.id.tvDate)
    TextView tvDate;

    @BindView(R.id.tvDescription)
    TextView tvDescription;

    @BindView(R.id.ivUpvote)
    ImageView ivUpvote;

    @BindView(R.id.tvVotes)
    TextView tvVotes;

    @BindView(R.id.ivDownvote)
    ImageView ivDownvote;

    public WaffelViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
