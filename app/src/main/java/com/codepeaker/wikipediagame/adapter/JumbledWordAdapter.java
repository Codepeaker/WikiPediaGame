package com.codepeaker.wikipediagame.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codepeaker.wikipediagame.R;
import com.codepeaker.wikipediagame.fragment.FTBFragment;

public class JumbledWordAdapter extends RecyclerView.Adapter<JumbledWordAdapter.JumbledViewHolder> {

    private FTBFragment ftbFragment;
    private String[] jumbledWords;

    public JumbledWordAdapter(FTBFragment ftbFragment, String[] jumbledWords) {
        this.ftbFragment = ftbFragment;
        this.jumbledWords = jumbledWords;
    }

    @NonNull
    @Override
    public JumbledViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.jumble_text_view, parent, false);
        return new JumbledViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JumbledViewHolder holder, int position) {
        holder.wordTv.setText(jumbledWords[position]);
    }

    @Override
    public int getItemCount() {
        return jumbledWords.length;
    }

    public class JumbledViewHolder extends RecyclerView.ViewHolder {
        TextView wordTv;

        public JumbledViewHolder(View itemView) {
            super(itemView);
            wordTv = itemView.findViewById(R.id.word_tv);

            wordTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ftbFragment.setText(wordTv.getText().toString());
                }
            });
        }
    }
}
