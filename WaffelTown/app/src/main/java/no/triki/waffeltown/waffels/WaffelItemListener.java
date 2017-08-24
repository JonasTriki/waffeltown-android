package no.triki.waffeltown.waffels;

import no.triki.waffeltown.models.Waffel;

/**
 * Created by Jonas on 24/08/2017.
 */

public interface WaffelItemListener {
    void onUpvoteClicked(Waffel waffel);
    void onDownvoteClicked(Waffel waffel);
}
