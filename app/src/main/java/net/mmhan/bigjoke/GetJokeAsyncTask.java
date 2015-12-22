package net.mmhan.bigjoke;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.view.View;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import net.mmhan.JokeRepo;
import net.mmhan.bigjoke.backend.myApi.MyApi;
import net.mmhan.bigjokeviews.JokeActivity;

import java.io.IOException;

/**
 * Created by mmhan on 15/12/15.
 */
public class GetJokeAsyncTask extends AsyncTask<Pair<Context, View>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private View view;

    @Override
    protected String doInBackground(Pair<Context, View>... params) {
        if(myApiService == null) {  // Only do this once
//            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
//                    new AndroidJsonFactory(), null)
//                    // options for running against local devappserver
//                    // - 10.0.2.2 is localhost's IP address in Android emulator
//                    // - turn off compression when running against local devappserver
//                    .setRootUrl("http://192.168.2.40:8080/_ah/api/")
//                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                        @Override
//                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                            abstractGoogleClientRequest.setDisableGZipContent(true);
//                        }
//                    });
            // end options for devappserver

            //Deployed module
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://bigjokegce.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        context = params[0].first;
        view = params[0].second;

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Intent i = new Intent(context, JokeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra(JokeActivity.JOKE_EXTRA, JokeRepo.get());
        context.startActivity(i);
        view.setVisibility(View.GONE);
    }
}
