package YTJ;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.my_application.R;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {

    private static final String TAG = "DataActivity";

    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private String companyName;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            companyName = extras.getString("Value1");
        }
        url = "http://avoindata.prh.fi/bis/v1?totalResults=false&maxResults=50&resultsFrom=0&name=" + companyName + "&companyRegistrationFrom=1945-01-01";
        Log.e(TAG, url);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            url = "http://avoindata.prh.fi/bis/v1?totalResults=false&maxResults=50&resultsFrom=0&name=" + query + "&companyRegistrationFrom=1945-01-01";
            Log.e(TAG, url);
            APIGet(url);
        } else {
            APIGet(url);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Do nothing here
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // Do nothing here
                return false;
            }
        });

        return true;
    }

    void APIGet(String url) {
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest

                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<Item> itemList = new ArrayList<Item>();

                        try {
                            JSONArray responseItems = (JSONArray) response.getJSONArray("results");

                            for (int i = 0; i < responseItems.length(); i++) {
                                JSONObject x = responseItems.getJSONObject(i);
                                Item item = new Item();
                                item.name = x.getString("name");
                                item.registrationDate = x.getString("registrationDate");
                                item.companyForm = x.getString("companyForm");
                                item.businessId = x.getString("businessId");

                                itemList.add(item);

                                //Log.i(TAG, "Name: " + item.name + ", Registration Date: " + item.registrationDate + ", Company Form: " + item.companyForm + ", Business ID: " + item.businessId);
                            }

                            Log.e(TAG, "Number of results: " + responseItems.length());
                            adapter = new RecyclerAdapter(itemList);
                            recyclerView.setAdapter(adapter);

                            // Hide the progress bar
                            ProgressBar progressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);
                            progressBar.setVisibility(View.INVISIBLE);

                        } catch (JSONException e) {
                            Log.e(TAG, "Error parsing JSON response: " + e.getMessage());
                        }
                        adapter = new RecyclerAdapter(itemList);
                        recyclerView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error retrieving data: " + error.getMessage());
                    }
                });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        requestQueue.add(jsonObjectRequest);
    }

}