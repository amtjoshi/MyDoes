package com.amit.mydoes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

public class EditTextActivity extends AppCompatActivity {
    EditText titleDoes,descDoes,dateDoes;
    Button saveUpdateBtn,deleteTaskBtn;
    DatabaseReference mRootRef;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        titleDoes=findViewById(R.id.titledoes);
        descDoes=findViewById(R.id.descdoes);
        dateDoes=findViewById(R.id.datedoes);
        saveUpdateBtn=findViewById(R.id.btnUpdateTask);
        deleteTaskBtn=findViewById(R.id.btnDeleteTask);

        key=getIntent().getStringExtra("keyDoes");

        mRootRef= FirebaseDatabase.getInstance().getReference();


        titleDoes.setText(getIntent().getStringExtra("titleDoes"));
        descDoes.setText(getIntent().getStringExtra("descDoes"));
        dateDoes.setText(getIntent().getStringExtra("dateDoes"));

        saveUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=titleDoes.getText().toString();
                String desc=descDoes.getText().toString();
                String date=dateDoes.getText().toString();
                key=getIntent().getStringExtra("keyDoes");

                HashMap<String, String> hashMap=new HashMap<>();
                hashMap.put("titledoes",title);
                hashMap.put("descdoes",desc);
                hashMap.put("datedoes",date);
                hashMap.put("keydoes",key);
                Toast.makeText(EditTextActivity.this,""+key,Toast.LENGTH_LONG).show();
                mRootRef.child("DoesApp").child("Does"+key).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                          if (task.isSuccessful())
                          {
                              Intent intent=new Intent(EditTextActivity.this,MainActivity.class);
                              intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                              startActivity(intent);
                          }
                    }
                });
            }
        });

        deleteTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRootRef.child("DoesApp").child("Does"+key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent=new Intent(EditTextActivity.this,MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                        Toast.makeText(EditTextActivity.this,"Task deleted successfully",Toast.LENGTH_LONG).show();
                    }
                })  ;
            }
        });
    }
}
