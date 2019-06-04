package com.amit.mydoes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

public class NewTaskActivity extends AppCompatActivity {
    Button saveTaskBtn;
    Button cancelBtn;
    EditText titledoes,descdoes,datedoes;
     DatabaseReference mRef;
     Integer doesNum=new Random().nextInt();
     String keyDoes=Integer.toString(doesNum);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        titledoes=findViewById(R.id.titledoes);
        descdoes=findViewById(R.id.descdoes);
        datedoes=findViewById(R.id.datedoes);

        saveTaskBtn=findViewById(R.id.btnSaveTask);
        cancelBtn=findViewById(R.id.btnCancel);
        saveTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insert new data in database
                 String title=titledoes.getText().toString();
                 String desc=descdoes.getText().toString();
                 String date=datedoes.getText().toString();

                 if(TextUtils.isEmpty(title)|TextUtils.isEmpty(desc)|TextUtils.isEmpty(date))
                     Toast.makeText(NewTaskActivity.this,"Add all details first",Toast.LENGTH_LONG).show();

                else {
                     mRef = FirebaseDatabase.getInstance().getReference().child("DoesApp");
                     HashMap<String, String> hashMap = new HashMap<>();
                     hashMap.put("titledoes", title);
                     hashMap.put("descdoes", desc);
                     hashMap.put("datedoes", date);
                     hashMap.put("keydoes", keyDoes);
                     mRef.child("Does" + doesNum).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                         @Override
                         public void onComplete(@NonNull Task<Void> task) {
                             if (task.isSuccessful()) {

                                 Toast.makeText(NewTaskActivity.this, "task added successfully", Toast.LENGTH_LONG).show();
                                 Intent intent = new Intent(NewTaskActivity.this, MainActivity.class);
                                 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                 startActivity(intent);

                             }
                         }
                     });
                 }
            }
        });
    }
}
