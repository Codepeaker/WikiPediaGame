package com.codepeaker.wikipediagame.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.codepeaker.wikipediagame.R;
import com.codepeaker.wikipediagame.adapter.DetailWordsAdapter;
import com.codepeaker.wikipediagame.databinding.ActivityScoreBinding;
import com.codepeaker.wikipediagame.fragment.FTBFragment;
import com.codepeaker.wikipediagame.utils.Constant;

import java.util.Locale;

public class ScoreActivity extends AppCompatActivity {

    ActivityScoreBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_score);

        int score = getIntent().getIntExtra(Constant.SCORE, 0);
        int totalScore = getIntent().getIntExtra(Constant.TOTAL_SCORE, 0);
        String[] replacedWords = getIntent().getStringArrayExtra(FTBFragment.ORIGINAL_EXTRACT_WORDS);
        String[] filledWords = getIntent().getStringArrayExtra(FTBFragment.FILLED_EXTRACT_WORDS);
        String[] originalSentences = getIntent().getStringArrayExtra(FTBFragment.ORIGINAL_SENTENCES);

        binding.descRv.setLayoutManager(new LinearLayoutManager(this));
        binding.descRv.setAdapter(new DetailWordsAdapter(replacedWords, filledWords
                , originalSentences));

        ((TextView) findViewById(R.id.score_tv)).setText(String.format(Locale.ENGLISH,
                "%d / %d", score, totalScore));
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_score, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reload) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
