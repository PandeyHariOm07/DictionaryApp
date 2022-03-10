package com.example.dictionary.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.Models.Meanings;
import com.example.dictionary.R;
import com.example.dictionary.ViewHolders.MeaningsViewHolder;

import java.util.List;

public class MeaningAdapter extends RecyclerView.Adapter<MeaningsViewHolder> {
    private Context context;
    protected List<Meanings> meaningList;

    public MeaningAdapter(Context context, List<Meanings> meaningList) {
        this.context = context;
        this.meaningList = meaningList;
    }

    @NonNull
    @Override
    public MeaningsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MeaningsViewHolder(LayoutInflater.from(context).inflate(R.layout.meanings_list_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MeaningsViewHolder holder, int position) {
        holder.textView_partOfSpeech.setText("Parts Of Speech" +meaningList.get(position).getPartOfSpeech());
        holder.recycler_defination.setHasFixedSize(true);
        holder.recycler_defination.setLayoutManager(new GridLayoutManager(context, 1));
        DefinationAdapter definationAdapter = new DefinationAdapter(context,meaningList.get(position).getDefinations());
        holder.recycler_defination.setAdapter(definationAdapter);
    }

    @Override
    public int getItemCount() {
        return meaningList.size();
    }
}
