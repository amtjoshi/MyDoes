package com.amit.mydoes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.core.Context;

import java.util.ArrayList;

public class DoesAdapter extends RecyclerView.Adapter < DoesAdapter.MyViewHolder >{

    ArrayList<MyDoes> myDoes;
    public DoesAdapter( ArrayList<MyDoes>myDoes)
    {
      this.myDoes=myDoes;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_does,viewGroup,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.titledoes.setText(myDoes.get(i).getTitledoes());
        myViewHolder.datedoes.setText(myDoes.get(i).getDatedoes());
        myViewHolder.descdoes.setText(myDoes.get(i).getDescdoes());

        final String titleDoes=myDoes.get(i).getTitledoes();
        final String descDoes=myDoes.get(i).getDescdoes();
        final String dateDoes=myDoes.get(i).getDatedoes();
        final String keyDoes=myDoes.get(i).getKeydoes();
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),EditTextActivity.class);
                intent.putExtra("titleDoes",titleDoes);
                intent.putExtra("descDoes",descDoes);
                intent.putExtra("dateDoes",dateDoes);
                intent.putExtra("keyDoes",keyDoes);
                v.getContext().startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return myDoes.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView datedoes,titledoes,descdoes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            datedoes=itemView.findViewById(R.id.datedoes);
            descdoes=itemView.findViewById(R.id.descdoes);
            titledoes=itemView.findViewById(R.id.titledoes);
        }
    }
}
