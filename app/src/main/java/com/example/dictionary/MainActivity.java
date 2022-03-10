package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dictionary.Adapters.MeaningAdapter;
import com.example.dictionary.Adapters.PhoneticsAdapter;
import com.example.dictionary.Models.ApiResponce;

//dictionaryApi.dev
//retrofit : Retrofit is a type-safe REST client for Android,
            // Java and Kotlin developed by Square. The library provides a powerful
            // framework for authenticating and interacting with APIs and
            // sending network requests with OkHttp
public class MainActivity extends AppCompatActivity {
 SearchView search_view;
 TextView textView_word;
 RecyclerView recycler_phonetics, recycler_meaning;
 ProgressDialog progressDialog;
 PhoneticsAdapter phoneticsAdapter;
 MeaningAdapter meaningAdapter;
 Toolbar toolbar;
 private PackageInfo mPackageInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_view = findViewById(R.id.search_view);
        textView_word = findViewById(R.id.textView_word);
        recycler_meaning = findViewById(R.id.recycler_meanings);
        recycler_phonetics = findViewById(R.id.recycler_phonetics);
        progressDialog = new ProgressDialog(this);
        toolbar = findViewById(R.id.toolbar);
        try {
            mPackageInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            setupVersionInfo();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        progressDialog.setTitle("Loading...");
        progressDialog.show();
        RequestManager manager = new RequestManager(MainActivity.this);
        manager.getWordMeaning(listener,"Hello");
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Fetching responce for "+ query);
                progressDialog.show();
                RequestManager manager = new RequestManager(MainActivity.this);
                manager.getWordMeaning(listener,query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(ApiResponce apiResponce, String message) {
            progressDialog.dismiss();
            if(apiResponce==null){
                Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponce);
        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showData(ApiResponce apiResponce) {
        textView_word.setText("Word:"+ apiResponce.getWord());
        recycler_phonetics.setHasFixedSize(true);
        recycler_phonetics.setLayoutManager(new GridLayoutManager(this,1));
        phoneticsAdapter = new PhoneticsAdapter(this, apiResponce.getPhonetics());
        recycler_phonetics.setAdapter(phoneticsAdapter);

        recycler_meaning.setHasFixedSize(true);
        recycler_meaning.setLayoutManager(new GridLayoutManager(this,1));
        meaningAdapter = new MeaningAdapter(this,apiResponce.getMeanings());
        recycler_meaning.setAdapter(meaningAdapter);
    }
    private void setupVersionInfo() {

        if (mPackageInfo != null) {
            String vinfo = String.format("V: %s", mPackageInfo.versionName);
            toolbar.setSubtitle(vinfo);
        }

    }
}