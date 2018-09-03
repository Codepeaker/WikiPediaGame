package com.codepeaker.wikipediagame.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codepeaker.wikipediagame.R;

import java.util.Locale;

import static com.codepeaker.wikipediagame.utils.AppUtils.DASH_STRING;

public class DetailWordsAdapter extends RecyclerView.Adapter<DetailWordsAdapter.DetailWordsViewHolder> {
    private String[] rightWords;
    private String[] wrongWords;
    private String[] originalSentences;

    public DetailWordsAdapter(String[] rightWords, String[] wrongWords, String[] originalSentences) {
        this.rightWords = rightWords;
        this.wrongWords = wrongWords;
        this.originalSentences = originalSentences;
    }

    @NonNull
    @Override
    public DetailWordsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wrong_filled_layout, parent, false);
        return new DetailWordsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailWordsViewHolder holder, int position) {
        if (wrongWords[position].isEmpty()) {
            return;
        }
        holder.sentenceTv.setText(String.format(Locale.ENGLISH,
                "%d. %s", position + 1, originalSentences[position]));
        if (wrongWords[position].equals(DASH_STRING)) {
            holder.filledWordsTv.setText(R.string.no_attempts);
        } else {
            holder.filledWordsTv.setText(wrongWords[position]);
        }
        holder.rightWordsTv.setText(rightWords[position]);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return originalSentences.length;
    }

    public class DetailWordsViewHolder extends RecyclerView.ViewHolder {
        TextView rightWordsTv;
        TextView filledWordsTv;
        TextView sentenceTv;

        public DetailWordsViewHolder(View itemView) {
            super(itemView);
            sentenceTv = itemView.findViewById(R.id.sentence_tv);
            rightWordsTv = itemView.findViewById(R.id.right_text_tv);
            filledWordsTv = itemView.findViewById(R.id.wrong_text_tv);

        }
    }
}
