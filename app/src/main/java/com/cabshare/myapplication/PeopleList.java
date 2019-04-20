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

public class PeopleList extends AppCompatActivity /*implements AdapterView.OnItemClickListener*/ {

    ListView List;
    private ArrayList<String> source = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_list);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String Source = bundle.getString("start");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Source);
        ref.child(name.substring(0,name.indexOf("/"))).setValue(name);

        Intent intent = new Intent(PeopleList.this,Checklist.class);
        intent.putExtra("start",Source);
        intent.putExtra("name",name);
        startActivity(intent);

//        List = (ListView)findViewById(R.id.list);
//        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,source);
//        List.setAdapter(arrayAdapter);
//        List.setOnItemClickListener(this);
//
//        //Text.setText(name+" "+Source);
//        ref.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                String value = dataSnapshot.getKey();
//                if(value.equals("xx")==false){
//                source.add(value.substring(0,value.indexOf("/")));
//                arrayAdapter.notifyDataSetChanged();}
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }

//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        TextView tv = (TextView)view;
//        Toast.makeText(this, "You clicked on "+tv.getText(),Toast.LENGTH_SHORT).show();
//    }
}
