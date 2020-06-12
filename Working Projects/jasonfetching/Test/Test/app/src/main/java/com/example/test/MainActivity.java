package com.example.test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView hospital;
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    Button buttonParse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hospital=(TextView)findViewById(R.id.hosname);
     //   ActionBar.setTitle("Ankur MentalHospital ");
        mTextViewResult = findViewById(R.id.text_view_result);
         buttonParse = findViewById(R.id.button_parse);

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonParse();
            }
        });



    }
    private void jsonParse(){


        hospital.setVisibility(View.INVISIBLE);
        buttonParse.setVisibility(View.INVISIBLE);
        String url = "https://api.myjson.com/bins/r2oom";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String male="male";
                            String female="female";
                            JSONArray jsonArray = response.getJSONArray("patients");

                            for (int i = 0; i<jsonArray.length(); i++){
                                JSONObject patients = jsonArray.getJSONObject(i);

                                String name = patients.getString("name");

                                String blood_group= patients.getString("blood_group");

                                String sex=patients.getString("sex");


                                final String alergies= patients.getString("allergies");

                                final String prescription =patients.getString("prescriptions");

                         //   if( female.equals( sex ) ) {
                         //         mTextViewResult.setTextColor(Color.RED);
                        //       mTextViewResult.append(name + ", " + blood_group + "\n\n");

                        //    }


                                mTextViewResult.append(name + ", " + blood_group + "\n\n");

                                mTextViewResult.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i=new Intent(MainActivity.this,intentact1.class);
                                        i.putExtra("alergy",alergies);
                                        i.putExtra("pres",prescription);
                                        startActivity(i);
                                    }
                                });

                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });

        mQueue.add(request);

    }
}
