package com.example.dictionary.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.R;

public class MeaningsViewHolder extends RecyclerView.ViewHolder {
    public TextView textView_partOfSpeech;
    public RecyclerView recycler_defination;
    public MeaningsViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_partOfSpeech = itemView.findViewById(R.id.textView_partOfSpeech);
        recycler_defination = itemView.findViewById(R.id.recycler_defination);
    }
}
