package com.example.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.databasetest.ui.login.LoginActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

     DatabaseReference databaseReference;
     EditText editText_name;
     Button save_button;
    EditText editText_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference= FirebaseDatabase.getInstance().getReference(String.valueOf(editText_name));
        editText_name=(EditText)findViewById(R.id.editText2);
        save_button=(Button)findViewById(R.id.button2);
        editText_pass=(EditText)findViewById(R.id.editText);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo();
            }
        });


    }

    public void saveInfo()
    {
        String name1 = editText_name.getText().toString().trim();
        String pass=editText_pass.getText().toString().trim();
        String id=databaseReference.push().getKey();

        UserInformation userInformation= new UserInformation(id , name1, pass);

         databaseReference.child(id).setValue(userInformation);
        Toast.makeText(this,"Stored successfully",Toast.LENGTH_LONG).show();

        Intent i=new Intent(MainActivity.this,LoginActivity.class);
        startActivity(i);



    }


}
