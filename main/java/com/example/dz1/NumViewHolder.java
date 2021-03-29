package com.example.dz1;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class NumViewHolder extends RecyclerView.ViewHolder {

    private final TextView number;

    public NumViewHolder(@NonNull View itemView){
        super(itemView);
        number = itemView.findViewById(R.id.number);
    }
    public void bind(int inpValue){
        if (inpValue %2 == 0){
            number.setTextColor(Color.RED);
        } else {
            number.setTextColor(Color.BLUE);
        }
        number.setText(String.valueOf(inpValue));

    }
    public TextView getNumber(){
        return number;
    }



}
