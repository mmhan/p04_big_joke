package net.mmhan.bigjoke;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import net.mmhan.bigjoke.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by mmhan on 15/12/15.
 */
public class GetJokeAsyncTask extends AsyncTask<GetJokeAsyncTask.GetJokeAsyncTaskListener, Integer, String> {
    private static MyApi myApiService = null;
    private GetJokeAsyncTaskListener mListener;

    @Override
    protected String doInBackground(GetJokeAsyncTaskListener... params) {
        mListener = params[0];
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

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mListener.onComplete(result);

    }

    public interface GetJokeAsyncTaskListener{
        public void onComplete(String result);
    }
}
