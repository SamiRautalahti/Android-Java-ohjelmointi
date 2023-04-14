package YTJ;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.UrlRewriter;
import com.android.volley.toolbox.Volley;
import com.example.my_application.R;
import com.google.android.material.tabs.TabLayout;
import static android.content.ContentValues.TAG;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataActivity extends AppCompatActivity {

    public static final String TAG = "DataActivity";

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
            url ="http://avoindata.prh.fi/bis/v1?totalResults=false&maxResults=50&resultsFrom=0&name="+ companyName +"&companyRegistrationFrom=1945-01-01";
        }
        Log.e(TAG, url + "");

        APIGet();
    }


    void APIGet(){
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest

                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray responseItems=(JSONArray) response.getJSONArray("results") ;

                            for (int i = 0; i < responseItems.length(); i++) {
                                JSONObject x=responseItems.getJSONObject(i);
                                Log.i(TAG, String.valueOf(x.getString("name")));  //tulostaa hakuehdon täyttävien yritysten nimet consoliin
                            }

                            Log.e(TAG,String.valueOf(responseItems.length()));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });


        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        requestQueue.add(jsonObjectRequest);
    }
}