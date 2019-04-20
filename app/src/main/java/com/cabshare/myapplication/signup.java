package com.cabshare.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    EditText name;
    EditText phonenumber;
    EditText destination;
    EditText numberofpeople;
    TextView notmatch;
    Button Signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = (EditText)findViewById(R.id.name);
        phonenumber = (EditText)findViewById(R.id.phonenumber);
        destination = (EditText)findViewById(R.id.whereto);
        numberofpeople = (EditText)findViewById(R.id.number);
        Signup = (Button)findViewById(R.id.Signup);
        notmatch = (TextView)findViewById(R.id.incorrect);

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(numberofpeople.getText().toString());
                if((phonenumber.getText().length()==10)&&(num<=3)){
                Intent intent = new Intent(signup.this, Source.class);
                String pndn = phonenumber.getText().toString()+"/"+name.getText().toString()
                        +"/"+destination.getText().toString()+"/"+numberofpeople.getText().toString();
                intent.putExtra("nameandnum",pndn);
                startActivity(intent);}
                else {
                    Toast.makeText(getApplicationContext(),"Enter Valid Details",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
