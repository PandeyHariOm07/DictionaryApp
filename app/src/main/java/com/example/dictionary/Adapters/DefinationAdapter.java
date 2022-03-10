package com.example.dictionary.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.Models.Definations;
import com.example.dictionary.R;
import com.example.dictionary.ViewHolders.DefinationViewHolder;

import java.util.List;

public class DefinationAdapter extends RecyclerView.Adapter<DefinationViewHolder> {
    private Context context;
    private List<Definations> definationsList;

    public DefinationAdapter(Context context, List<Definations> definationsList) {
        this.context = context;
        this.definationsList = definationsList;
    }

    @NonNull
    @Override
    public DefinationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DefinationViewHolder(LayoutInflater.from(context).inflate(R.layout.definations_list_items,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull DefinationViewHolder holder, int position) {
    holder.textView_defination.setText("Defination:"+ definationsList.get(position).getDefinition());
    holder.textView_example.setText("Exa,ple:"+definationsList.get(position).getExample());
    StringBuilder synonyms = new StringBuilder();
    StringBuilder antonyms = new StringBuilder();

    synonyms.append(definationsList.get(position).getSynonyms());
    antonyms.append(definationsList.get(position).getAntonyms());
    holder.textView_synonyms.setText(synonyms);
    holder.textView_antonyms.setText(antonyms);
    holder.textView_synonyms.setSelected(true);
    holder.textView_antonyms.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return definationsList.size();
    }
}
