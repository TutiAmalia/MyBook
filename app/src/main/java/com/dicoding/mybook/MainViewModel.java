package com.dicoding.mybook;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;



public class MainViewModel extends ViewModel{
    private static final String API_KEY= "AIzaSyDZTFGTY8c-5e6kreSyVq-Y66FurfyyvSM";
    private static final String API_ENDPOINT = "https://www.googleapis.com/books/v1/volumes?q=";

    private MutableLiveData<ArrayList<BookItems>> listBook = new MutableLiveData<>();

    public static String getApiKey(){
        return API_KEY;
    }

    public MutableLiveData<ArrayList<BookItems>> getListBook(){
        return listBook;
    }

    void setBooks(String books){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<BookItems> listItems = new ArrayList<>();
        String url = API_ENDPOINT + books;

        client.get(url, new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("items");
                    for (int i =0;i<list.length();i++){
                        JSONObject book = list.getJSONObject(i);
                        BookItems bookItems = new BookItems(book);
                        listItems.add(bookItems);
                    }
                    listBook.postValue(listItems);
                } catch (Exception e){
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            Log.d("onFailure", error.getMessage());
            }
        });
    }
}
