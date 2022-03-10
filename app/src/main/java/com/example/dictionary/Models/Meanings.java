package com.example.dictionary.Models;

import java.util.List;

public class Meanings {
    String partOfSpeech ="";
    List<Definations> definations = null;

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public List<Definations> getDefinations() {
        return definations;
    }

    public void setDefinations(List<Definations> definations) {
        this.definations = definations;
    }
}
