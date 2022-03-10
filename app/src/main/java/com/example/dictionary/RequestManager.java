package com.example.dictionary;

import android.content.Context;
import android.widget.Toast;

import com.example.dictionary.Models.ApiResponce;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.dictionaryapi.dev/api/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build();

    public RequestManager(Context context) {
        this.context = context;
    }
    public void getWordMeaning(OnFetchDataListener listener,String word){
        CallDictionary callDictionary = retrofit.create(CallDictionary.class);
        Call<List<ApiResponce>> call = callDictionary.callMeanings(word);
        try {
            call.enqueue(new Callback<List<ApiResponce>>() {
                @Override
                public void onResponse(Call<List<ApiResponce>> call, Response<List<ApiResponce>> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(context, "Error!!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    listener.onFetchData(response.body().get(0), response.message());
                }

                @Override
                public void onFailure(Call<List<ApiResponce>> call, Throwable t) {
                    listener.onError("Request Failure");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context,"An Error Occured!!",Toast.LENGTH_SHORT).show();
        }
    }
    public interface CallDictionary{
        @GET("entries/en/{word}")
        Call<List<ApiResponce>> callMeanings(
                @Path("word") String word
        );
    }
}
