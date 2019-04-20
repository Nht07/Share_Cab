package com.cabshare.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Checklist extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView List;
    private ArrayList<String> source = new ArrayList<String>();
    private FirebaseDatabase database;
    private DatabaseReference ref;
    String value;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);

        Bundle bundle = getIntent().getExtras();
        String Source = bundle.getString("start");
        String name = bundle.getString("name");
        int position = name.indexOf('/');
        phone = name.substring(0,position);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference(Source);

        List = (ListView)findViewById(R.id.list);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,source);
        List.setAdapter(arrayAdapter);
        List.setOnItemClickListener(this);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                value = dataSnapshot.getValue().toString().concat("/");


                if(value.equals("xx/")==false){
                int l = value.indexOf('/'),l1=0;
                String p = value.substring(l1,l);
                l1=l+1;
                l = value.indexOf('/',l1);
                String n = value.substring(l1,l);
                l1=l+1;
                l = value.indexOf('/',l1);
                String d = value.substring(l1,l);
                l1=l+1;
                l = value.indexOf('/',l1);
                String np = value.substring(l1,l);
                l1=l+1;
                l = value.indexOf('/',l1);
               if(phone.equals(p)==false){
                source.add(n+" off to "+d.toUpperCase()+" with "+np+" seat(s)"+"\nCall "+p+"?");
                arrayAdapter.notifyDataSetChanged();}}
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView tv = (TextView)view;
        String temp = tv.getText().toString();
        int l2=temp.length()-1;
        temp = temp.substring(l2-10,l2);
        //Toast.makeText(getApplicationContext(),phone,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+temp));
        startActivity(intent);
    }
}
