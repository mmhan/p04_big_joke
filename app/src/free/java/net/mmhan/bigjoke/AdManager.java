package net.mmhan.bigjoke;

import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by mmhan on 22/12/15.
 */
public class AdManager {
    private static final String TEST_DEVICE = "60E1E5A35CB33F44D8AC2E0D78E18CA1";

    InterstitialAd interstitialAd;

    public AdManager(final MainActivity ctx) {
        interstitialAd = new InterstitialAd(ctx);
        interstitialAd.setAdUnitId(ctx.getString(R.string.ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                ctx.launchJokeActivity();
                super.onAdClosed();
            }
        });
        requestNewInterstitial();
    }

    public boolean adIsLoaded(){
        return interstitialAd.isLoaded();
    }

    public void showAd(){
        interstitialAd.show();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(TEST_DEVICE)
                .build();
        interstitialAd.loadAd(adRequest);
    }


    public void showAd(View adView){
        AdView mAdView = (AdView) adView;
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(TEST_DEVICE)
                .build();
        mAdView.loadAd(adRequest);
    }
}
