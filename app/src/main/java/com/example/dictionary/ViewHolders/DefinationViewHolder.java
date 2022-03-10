package com.example.dictionary.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.R;

public class DefinationViewHolder extends RecyclerView.ViewHolder {
    public TextView textView_defination,textView_example,textView_synonyms,textView_antonyms;
    public DefinationViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_defination = itemView.findViewById(R.id.textView_defination);
        textView_example = itemView.findViewById(R.id.textView_example);
        textView_synonyms = itemView.findViewById(R.id.textView_synonyms);
        textView_antonyms = itemView.findViewById(R.id.textView_antonyms);
    }
}
