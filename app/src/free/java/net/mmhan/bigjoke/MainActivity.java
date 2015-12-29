package net.mmhan.bigjoke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import net.mmhan.bigjokeviews.JokeActivity;

public class MainActivity extends AppCompatActivity {

    AdManager manager;
    String result = null;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Log.e("FREE MainActivity", "onCreate");

        manager = new AdManager(this);
        manager.showAd(findViewById(R.id.adView));


        Button btn = (Button) findViewById(R.id.get_joke_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchJoke();
            }
        });
    }

    public void fetchJoke(){
        progressBar.setVisibility(View.VISIBLE);
        new GetJokeAsyncTask(true).execute(new GetJokeAsyncTask.GetJokeAsyncTaskListener() {
            @Override
            public void onComplete(String r) {
                result = r;
                if (manager.adIsLoaded()) {
                    manager.showAd();
                } else {
                    launchJokeActivity();
                }
            }
        });
    }

    public void launchJokeActivity(){
        Intent i = new Intent(getApplicationContext(), JokeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra(JokeActivity.JOKE_EXTRA, result);
        startActivity(i);
        progressBar.setVisibility(View.GONE);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
