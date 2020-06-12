package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class intentact1 extends AppCompatActivity {

    TextView ale,pre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intentact1);

        ale = findViewById(R.id.textView);
        pre = findViewById(R.id.textView2);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String alergy = extras.getString("alergy");
            String pres = extras.getString("pres");
            Toast.makeText(getApplicationContext(),""+alergy,Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),""+pres,Toast.LENGTH_LONG).show();
            ale.setText(alergy);
            pre.setText(pres);

        }


    }
}
