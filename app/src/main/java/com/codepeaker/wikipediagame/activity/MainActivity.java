package com.codepeaker.wikipediagame.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.codepeaker.wikipediagame.R;
import com.codepeaker.wikipediagame.databinding.ActivityMainBinding;
import com.codepeaker.wikipediagame.databinding.ContentMainBinding;
import com.codepeaker.wikipediagame.fragment.FTBFragment;
import com.codepeaker.wikipediagame.model.BundleConverter;
import com.codepeaker.wikipediagame.utils.Constant;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_REFRESH = 101;
    ActivityMainBinding binding;
    ContentMainBinding contentMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contentMain = binding.contentMain;

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, FTBFragment.newInstance(), FTBFragment.TAG)
                    .commit();
        }


        contentMain.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FTBFragment ftbFragment = ((FTBFragment) getSupportFragmentManager()
                        .findFragmentByTag(FTBFragment.TAG));

                if (ftbFragment != null && ftbFragment.isAdded()) {

                    processFilledAndOriginalString(ftbFragment.getWordsReplaceArray(),
                            ftbFragment.getFilledExtractWordsMap(),
                            ftbFragment.getSentencesWithDashes());
                }
            }
        });


    }

    private void processFilledAndOriginalString(SparseArray<String> originalExtractWords
            , SparseArray<String> filledExtractWords, StringBuilder[] originalSentences) {
        int totalScore = originalExtractWords.size();
        int score = 0;
        for (int i = 0; i < originalExtractWords.size(); i++) {
            if (filledExtractWords.get(i) != null) {
                if (filledExtractWords.get(i).equals(originalExtractWords.get(i))) {
                    score++;
                }
            }
        }

        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra(Constant.TOTAL_SCORE, totalScore);
        intent.putExtra(Constant.SCORE, score);
        intent.putExtra(FTBFragment.FILLED_EXTRACT_WORDS
                , BundleConverter.getStringArray(filledExtractWords));
        intent.putExtra(FTBFragment.ORIGINAL_EXTRACT_WORDS
                , BundleConverter.getStringArray(originalExtractWords));
        intent.putExtra(FTBFragment.ORIGINAL_SENTENCES
                , BundleConverter.getStringArray(originalSentences));
        startActivityForResult(intent, REQUEST_CODE_REFRESH);
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
        if (id == R.id.action_reload) {
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refresh() {
        FTBFragment ftbFragment = ((FTBFragment) getSupportFragmentManager()
                .findFragmentByTag(FTBFragment.TAG));

        if (ftbFragment != null && ftbFragment.isAdded())
            ftbFragment.getRandomPage();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_REFRESH) {
            if (resultCode == RESULT_OK) {
                refresh();
            }
        }
    }
}
