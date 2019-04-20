package com.cabshare.myapplication;

import android.content.Intent;
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

public class Source extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView List;
    private ArrayList<String> source = new ArrayList<String>();
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private String NameandNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);
        Bundle bundle = getIntent().getExtras();

        NameandNum = bundle.getString("nameandnum");

        database = FirebaseDatabase.getInstance();
        ref = database.getReference();

        List = (ListView)findViewById(R.id.list);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,source);
        List.setAdapter(arrayAdapter);
        List.setOnItemClickListener(this);


        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getKey();
                source.add(value);
                arrayAdapter.notifyDataSetChanged();
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
        Intent intent = new Intent(Source.this,PeopleList.class);
        intent.putExtra("start",tv.getText());
        intent.putExtra("name",NameandNum);
        startActivity(intent);
        //Toast.makeText(this, "You clicked on "+tv.getText()+" "+NameandNum,Toast.LENGTH_SHORT).show();
    }
}
