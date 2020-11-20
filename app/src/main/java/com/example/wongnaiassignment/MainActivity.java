package com.example.wongnaiassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.wongnaiassignment.Adapter.RecyclerAdapter;
import com.example.wongnaiassignment.Model.Data;
import com.example.wongnaiassignment.Presenter.CPresenter;
import com.example.wongnaiassignment.View.InterView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements InterView{

    private RequestQueue requestQueue;

    private RecyclerView recyclerView;
    private List<Data> list;
    private RecyclerAdapter recyclerAdapter;

    private CPresenter cPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.container);

        cPresenter = new CPresenter(this);
        requestQueue = Volley.newRequestQueue(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        list = new ArrayList<Data>();
        recyclerAdapter = new RecyclerAdapter(this,list);
        recyclerView.setAdapter(recyclerAdapter);

        jsonParse();
    }

    private void jsonParse() {
        String url = "https://api.coinranking.com/v1/public/coins";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = response.getJSONObject("data");
                    JSONArray jsonArray = jsonObject.getJSONArray("coins");
                    for(int i = 0;i < jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        list.add(convertJsonObjectToObject(object));
                    }
                    cPresenter.loadData(list);
                }catch (Exception e){e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("TAG", "onErrorResponse: "+error.toString());
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void resultData(List<Data> list) {
        recyclerAdapter.notifyDataSetChanged();
    }

    private Data convertJsonObjectToObject(JSONObject jsonObject) {
        try {
            String Picture = jsonObject.getString("iconUrl");
            String Title = jsonObject.getString("name");
            String Desc = jsonObject.getString("description");

            return new Data(Picture,Title,Desc);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}