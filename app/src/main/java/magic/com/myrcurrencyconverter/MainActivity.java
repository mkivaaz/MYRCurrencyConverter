package magic.com.myrcurrencyconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import magic.com.myrcurrencyconvertor.R;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    private CountryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final List<CountryList> data  = new ArrayList<>();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://restcountries.eu/rest/v2/all", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                int val = response.length();
                for(int i = 0; i < response.length();i++){

                    CountryList current = new CountryList();

                    try {
                        JSONObject countryName = response.getJSONObject(i);
                        current.setCountryName(countryName.getString("name"));
                        current.setFlag(countryName.getString("flag"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    data.add(current);
                }
                recyclerView = (RecyclerView) findViewById(R.id.recycle);
                adapter = new CountryAdapter(getBaseContext(),data);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);



    }


    public static List<CountryList> getData(){

        List<CountryList> data = new ArrayList<>();
        String[] country = {"America","Australia","Japan","England", "New Zealand"};
        for (int i = 0; i < 4; i++){
            CountryList current = new CountryList();
            current.setCountryName(country[i]);
            data.add(current);
        }
        return data;
    }
}
