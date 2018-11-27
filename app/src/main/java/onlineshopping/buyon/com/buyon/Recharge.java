package onlineshopping.buyon.com.buyon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Recharge extends AppCompatActivity implements OnItemClickListener {

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView mRVFishPrice;
    private Adapter_ mAdapter;
    private OnItemClickListener clickListener;
    int RecyclerViewItemPosition ;
    final List<DataModel> data=new ArrayList<>();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);




        new Recharge.AsyncFetch().execute();
    }

    @Override
    public void onClick(View view, int position) {
        Toast.makeText(this, position, Toast.LENGTH_SHORT).show();
    }


    private class AsyncFetch extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(Recharge.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL("https://skolage.com/buyon/recharge.json");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();


            pdLoading.dismiss();
            try {

                JSONArray jArray = new JSONArray(result);

                // Extract data from json and store into ArrayList as class objects
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    DataModel fishData = new DataModel();
                    fishData.image= json_data.getString("image");
                    fishData.name= json_data.getString("name");
                    fishData.redirect_url= json_data.getString("redirect_url");
                    data.add(fishData);
                }

                // Setup and Handover data to recyclerview
                mRVFishPrice = findViewById(R.id.food_recyclerview);
                mAdapter = new Adapter_(Recharge.this, data);
                mRVFishPrice.setAdapter(mAdapter);
                mRVFishPrice.setLayoutManager(new LinearLayoutManager(Recharge.this));

                mRVFishPrice.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

                    GestureDetector gestureDetector = new GestureDetector(Recharge.this, new GestureDetector.SimpleOnGestureListener() {

                        @Override public boolean onSingleTapUp(MotionEvent motionEvent) {

                            return true;
                        }

                    });
                    @Override
                    public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

                        View  ChildView = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                        if(ChildView != null && gestureDetector.onTouchEvent(motionEvent)) {

                            RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(ChildView);

                            Intent i=new Intent(Recharge.this,WebActivity.class);
                            String redirect_url=data.get(RecyclerViewItemPosition).redirect_url.replace("https://","https://www.");
                            i.putExtra("redirect_url",redirect_url);

                            //Toast.makeText(Food.this,redirect_url, Toast.LENGTH_SHORT).show();
                            startActivity(i);
                        }

                        return false;
                    }

                    @Override
                    public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

                    }

                    @Override
                    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                    }
                });


            } catch (JSONException e) {
                Toast.makeText(Recharge.this, e.toString(), Toast.LENGTH_LONG).show();
            }

        }


    }

}
