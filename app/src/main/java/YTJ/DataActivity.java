package YTJ;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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

    public RecyclerAdapter adapter;

    public RecyclerView recyclerView;

    private String businessId;
    private String companyName;
    private String registrationDate;
    private String companyForm;
    private String url= "http://avoindata.prh.fi/bis/v1?totalResults=false&maxResults=50&resultsFrom=0&name=lappeen&companyRegistrationFrom=2000-01-01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            companyName = extras.getString("Value1");
        }
        String url ="http://avoindata.prh.fi/bis/v1?totalResults=false&maxResults=50&resultsFrom=0&name="+ companyName +"&companyRegistrationFrom=1945-01-01";
        Log.e(TAG, url);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        APIGet(url);
    }

    void APIGet(String url){
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest

                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<Item> itemList= new ArrayList<Item>();

                        try {
                            JSONArray responseItems=(JSONArray) response.getJSONArray("results");


                            for (int i = 0; i < responseItems.length(); i++) {
                                JSONObject x=responseItems.getJSONObject(i);
                                Item item = new Item();
                                item.name = x.getString("name");
                                item.registrationDate= x.getString("registrationDate");
                                item.companyForm=x.getString("companyForm");
                                item.businessId=x.getString("businessId");

                                itemList.add(item);

                                Log.i(TAG, "Name: " + companyName + ", Registration Date: " + registrationDate + ", Company Form: " + companyForm + ", Business ID: " + businessId);
                            }

                            Log.e(TAG,"Number of results: " + responseItems.length());
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