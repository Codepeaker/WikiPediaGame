package com.codepeaker.wikipediagame.fragment;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.codepeaker.wikipediagame.R;
import com.codepeaker.wikipediagame.adapter.JumbledWordAdapter;
import com.codepeaker.wikipediagame.databinding.FragmentFtbBinding;
import com.codepeaker.wikipediagame.model.ApiResponse;
import com.codepeaker.wikipediagame.model.BundleConverter;
import com.codepeaker.wikipediagame.model.Page;
import com.codepeaker.wikipediagame.utils.AppUtils;
import com.codepeaker.wikipediagame.utils.StringUtils;
import com.codepeaker.wikipediagame.viewmodel.RandomPageViewModel;

import java.util.Map;
import java.util.Random;

import static com.codepeaker.wikipediagame.utils.AppUtils.DASH_STRING;

public class FTBFragment extends Fragment {

    public static final String DESC_SENTENCES = "DESC_SENTENCES";
    public static final String FILLED_EXTRACT_WORDS = "FILLED_EXTRACT_WORDS";
    public static final String ORIGINAL_EXTRACT_WORDS = "ORIGINAL_EXTRACT_WORDS";
    public static final String REPLACED_WORDS_INDEX = "REPLACED_WORDS_INDEX";
    public static final String ORIGINAL_SENTENCES = "ORIGINAL_SENTENCES";
    private static final String TITLE = "TITLE";
    private static final String SENTENCE_WITH_DASHES = "SENTENCE_WITH_DASHES";
    private FragmentFtbBinding binding;

    private SparseArray<String> wordsReplaceArray = new SparseArray<>();

    private SparseArray<String> filledExtractWordsMap = new SparseArray<>();
    private StringBuilder[] descSentences;
    private Integer choosenPosition;
    private SparseIntArray wordsReplaceIndex = new SparseIntArray();
    private StringBuilder[] sentenceWithDashes;

    public FTBFragment() {
    }

    public static final String TAG = FTBFragment.class.getSimpleName();


    public static FTBFragment newInstance() {
        return new FTBFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ftb, container
                , false);

        return binding.getRoot();
    }

    RandomPageViewModel mViewModel;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null) {
            mViewModel = ViewModelProviders.of(this).get(RandomPageViewModel.class);

            mViewModel.getRandomPage();

            AppUtils.showPleaseWaitDialog(getActivity());
            mViewModel.getPageLiveData().observe(this, new Observer<ApiResponse>() {
                @Override
                public void onChanged(@Nullable ApiResponse apiResponse) {
                    if (apiResponse != null) {
                        Map<String, Page> pageMap = apiResponse.getQuery().getPageMap();
                        for (Page page : pageMap.values()) {
                            setUpScreen(page);
                            break;
                        }
                    } else {
                        AppUtils.ptalToast(getActivity());
                    }
                    AppUtils.hidePDialog();
                }
            });

        } else {
            reSetUpScreen(savedInstanceState);
        }

    }

    private void reSetUpScreen(Bundle savedInstanceState) {
        String[] descs = savedInstanceState.getStringArray(DESC_SENTENCES);
        String[] filledExtracts = savedInstanceState.getStringArray(FILLED_EXTRACT_WORDS);
        String[] wordReplaced = savedInstanceState.getStringArray(ORIGINAL_EXTRACT_WORDS);
        String[] sentencesWithDash = savedInstanceState.getStringArray(SENTENCE_WITH_DASHES);
        int[] wordsReplacedIndeces = savedInstanceState.getIntArray(REPLACED_WORDS_INDEX);
        String title = savedInstanceState.getString(TITLE);

        if (descs != null) {
            descSentences = BundleConverter.getStringBuilderArray(descs);
        }
        if (filledExtracts != null) {
            filledExtractWordsMap = BundleConverter.getSparshArray(filledExtracts);
        }
        if (wordReplaced != null) {
            wordsReplaceArray = BundleConverter.getSparshArray(wordReplaced);
        }
        if (wordsReplacedIndeces != null) {
            wordsReplaceIndex = BundleConverter.getSparseIntArray(wordsReplacedIndeces);
        }
        if (sentencesWithDash != null) {
            sentenceWithDashes = BundleConverter.getStringBuilderArray(sentencesWithDash);
        }

        SpannableStringBuilder fullSpanString = new SpannableStringBuilder();

        for (int position = 0; position < descSentences.length; position++) {
            StringBuilder descSentence = descSentences[position];
            if (!TextUtils.isEmpty(descSentence)) {
                fullSpanString.append(makeClickableSpan(
                        filledExtractWordsMap.get(position),
                        wordsReplaceIndex.get(position),
                        descSentence, position)).append("\n");
            }

        }
        binding.title.setText(title);
        binding.desc.setText(fullSpanString);
        binding.desc.setMovementMethod(LinkMovementMethod.getInstance());
        binding.desc.setHighlightColor(Color.TRANSPARENT);

    }

    private void setUpScreen(Page page) {

        StringBuilder[] tempBuilder = AppUtils.getSentenceArray(page.getExtract()
                .replaceAll("\\n", "")); //To remove multiple \n

        descSentences = StringUtils.cleanEmptySpace(tempBuilder);

        descSentences = setRandomPlaceWithDashes(descSentences);

        setUnfilledString(descSentences);

        SpannableStringBuilder fullSpanString = new SpannableStringBuilder();

        for (int position = 0; position < descSentences.length; position++) {
            StringBuilder descSentence = descSentences[position];
            if (!TextUtils.isEmpty(descSentence)) {
                fullSpanString.append(makeClickableSpan(
                        DASH_STRING,
                        wordsReplaceIndex.get(position),
                        descSentence, position)).append("\n");
                filledExtractWordsMap.put(position, DASH_STRING);
            }

        }
        binding.title.setText(page.getTitle().replaceAll("\\n", ""));
        binding.desc.setText(fullSpanString);
        binding.desc.setMovementMethod(LinkMovementMethod.getInstance());
        binding.desc.setHighlightColor(Color.TRANSPARENT);
    }

    public SpannableStringBuilder makeClickableSpan(String clickableWord
            , int clickableWordIndex, StringBuilder s, final int sentencePosition) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(s);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View textView) {
                OpenDialogWithWords(sentencePosition);
            }

        };

        spannableStringBuilder.setSpan(clickableSpan, clickableWordIndex
                , clickableWordIndex + clickableWord.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableStringBuilder;
    }

    Dialog dialog;


    private void OpenDialogWithWords(int position) {
        dialog = new Dialog(getActivity());
        this.choosenPosition = position;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.jumbled_words_list_dialog);

        RecyclerView recyclerView = dialog.findViewById(R.id.jumble_word_rv);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        String[] shuffleString = StringUtils.randomize(BundleConverter.getStringArray(wordsReplaceArray)
                , wordsReplaceArray.size());
        recyclerView.setAdapter(new JumbledWordAdapter(this, shuffleString));

        dialog.show();

    }

    public void setText(String word) {

        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        descSentences = StringUtils.setChoosenString(word, filledExtractWordsMap.get(choosenPosition), descSentences, choosenPosition);
        filledExtractWordsMap.put(choosenPosition, word);
        SpannableStringBuilder fullSpanString = new SpannableStringBuilder();
        for (int sentencePosition = 0; sentencePosition < descSentences.length; sentencePosition++) {
            StringBuilder descSentence = descSentences[sentencePosition];
            if (!TextUtils.isEmpty(descSentence)) {
                fullSpanString.append(makeClickableSpan(
                        filledExtractWordsMap.get(sentencePosition),
                        wordsReplaceIndex.get(sentencePosition),
                        descSentence, sentencePosition)).append("\n");
            }
        }

        binding.desc.setText(fullSpanString);
        binding.desc.setMovementMethod(LinkMovementMethod.getInstance());
        binding.desc.setHighlightColor(Color.TRANSPARENT);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArray(DESC_SENTENCES
                , BundleConverter.getStringArray(descSentences));
        outState.putStringArray(FILLED_EXTRACT_WORDS
                , BundleConverter.getStringArray(filledExtractWordsMap));
        outState.putStringArray(ORIGINAL_EXTRACT_WORDS
                , BundleConverter.getStringArray(wordsReplaceArray));
        outState.putIntArray(REPLACED_WORDS_INDEX
                , BundleConverter.getIntArray(wordsReplaceIndex));
        outState.putStringArray(SENTENCE_WITH_DASHES
                , BundleConverter.getStringArray(getSentencesWithDashes()));

        outState.putString(TITLE, binding.title.getText().toString());
    }


    public StringBuilder[] setRandomPlaceWithDashes(StringBuilder[] stringBuilder) {
        Random rn = new Random();
        for (int i = 0; i < stringBuilder.length; i++) {
            String[] strings = stringBuilder[i].toString().split(" ");
            int randomNo = rn.nextInt(strings.length);
            String randomString = strings[randomNo];
            wordsReplaceArray.put(i, randomString);
            wordsReplaceIndex.put(i, stringBuilder[i].indexOf(randomString));
            stringBuilder[i].replace(stringBuilder[i].indexOf(randomString)
                    , stringBuilder[i].indexOf(randomString) + randomString.length()
                    , DASH_STRING);
        }


        return stringBuilder;

    }

    public SparseArray<String> getWordsReplaceArray() {
        return wordsReplaceArray;
    }

    public SparseArray<String> getFilledExtractWordsMap() {
        return filledExtractWordsMap;
    }

    public void getRandomPage() {

        AppUtils.showPleaseWaitDialog(getActivity());
        reInitializeData();
        mViewModel.getRandomPage();

    }

    private void reInitializeData() {
        wordsReplaceArray = new SparseArray<>();
        filledExtractWordsMap = new SparseArray<>();
        wordsReplaceIndex = new SparseIntArray();
    }

    public StringBuilder[] getSentencesWithDashes() {
        return sentenceWithDashes;
    }

    private void setUnfilledString(StringBuilder[] descSentences) {
        StringBuilder[] stringBuilders = new StringBuilder[descSentences.length];
        for (int i = 0; i < descSentences.length; i++) {
            stringBuilders[i] = new StringBuilder(descSentences[i]);
        }
        this.sentenceWithDashes = stringBuilders;
    }
}
