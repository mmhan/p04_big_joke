package net.mmhan.bigjokeviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_EXTRA = "net.mmhan.bigjokeviews.JokeActivity.JOKE_EXTRA";
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        mTextView = (TextView) findViewById(R.id.textView);
        if(getIntent() != null && getIntent().getStringExtra(JOKE_EXTRA) != null)
            mTextView.setText(getIntent().getStringExtra(JOKE_EXTRA));
    }
}
