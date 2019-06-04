package com.amit.mydoes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView title;
    TextView subtitle;
    TextView endPage;
    RecyclerView ourDoes;
    DoesAdapter doesAdapter;
    ArrayList<MyDoes> list;
    DatabaseReference mRootRef;
    Button addNew;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title=findViewById(R.id.titlepage);
        subtitle=findViewById(R.id.subtitlepage);
        endPage=findViewById(R.id.endpage);

        addNew=findViewById(R.id.btn_add_new);
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,NewTaskActivity.class);
                startActivity(intent);
            }
        });

        Typeface light=ResourcesCompat.getFont(MainActivity.this,R.font.mm);
        Typeface medium=ResourcesCompat.getFont(MainActivity.this,R.font.ml);
        title.setTypeface(medium);
        subtitle.setTypeface(light);
        endPage.setTypeface(light);

        ourDoes=findViewById(R.id.our_does);
        ourDoes.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        list=new ArrayList<>();

         progressDialog=new ProgressDialog(MainActivity.this);
         progressDialog.setMessage("please wait while data is loaded");
         progressDialog.show();

        mRootRef= FirebaseDatabase.getInstance().getReference().child("DoesApp");
        mRootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                             MyDoes p= dataSnapshot1.getValue(MyDoes.class);
                             list.add(p);

                }
                doesAdapter=new DoesAdapter(list);
                ourDoes.setAdapter(doesAdapter);
               doesAdapter.notifyDataSetChanged();
               progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
         }
}
