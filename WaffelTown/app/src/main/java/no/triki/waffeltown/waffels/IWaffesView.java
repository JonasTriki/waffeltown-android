package no.triki.waffeltown.waffels;

import java.util.ArrayList;

import no.triki.waffeltown.models.Waffel;

/**
 * Created by Jonas on 24/08/2017.
 */

public interface IWaffesView {
    void waffelsLoaded(ArrayList<Waffel> waffels);
}
