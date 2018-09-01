package com.codepeaker.wikipediagame.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepeaker.wikipediagame.R;
import com.codepeaker.wikipediagame.databinding.FragmentFtbBinding;
import com.codepeaker.wikipediagame.model.ApiResponse;
import com.codepeaker.wikipediagame.model.Page;
import com.codepeaker.wikipediagame.utils.AppUtils;
import com.codepeaker.wikipediagame.viewmodel.RandomPageViewModel;

import java.util.Map;

public class FTBFragment extends Fragment {

    private RandomPageViewModel mViewModel;
    private FragmentFtbBinding binding;

    public FTBFragment() {
    }


    public static final String TAG = FTBFragment.class.getSimpleName();

    public static FTBFragment newInstance() {
        return new FTBFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ftb, container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RandomPageViewModel.class);

        mViewModel.getPageLiveData().observe(getActivity(), new Observer<ApiResponse>() {
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
            }
        });

    }

    private void setUpScreen(Page page) {
        String descArray[] = AppUtils.getSentenceArray(page.getExtract().replaceAll("\\n"
                , ""));
        SpannableStringBuilder fullSpanString = new SpannableStringBuilder();
        for (int i = 0; i < descArray.length; i++) {
            String aDescArray = descArray[i];
            if (aDescArray != null) {

                fullSpanString.append(AppUtils.makeClickableSpan(getActivity(), aDescArray,i)).append("\n");
            }

        }


        binding.title.setText(page.getTitle().replaceAll("\\n", ""));
        binding.desc.setText(fullSpanString);
        binding.desc.setMovementMethod(LinkMovementMethod.getInstance());
        binding.desc.setHighlightColor(Color.TRANSPARENT);
    }

    public void setRandomPage() {
        mViewModel.setPageLiveData(mViewModel.getRandomPage());
    }
}
