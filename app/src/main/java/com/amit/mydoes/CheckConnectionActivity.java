package com.amit.mydoes;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CheckConnectionActivity extends AppCompatActivity {
    Button retryBtn;
    ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_connection);
        checkInternetConnection();

        retryBtn=findViewById(R.id.retryBtn);
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInternetConnection();
            }
        });

    }

   public void checkInternetConnection()

    {
        if(ConnectionManager.checkConnetcion(getBaseContext()))
        {
            Intent intent=new Intent(CheckConnectionActivity.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else
        {

        }

    }
}
