package com.example.dz1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NumAdapter extends RecyclerView.Adapter<NumViewHolder> {
    private  List<Integer> nums;
    private  ClickedOne clickedOne;
    private boolean clickChecker;

    public NumAdapter(List<Integer> numbers, ClickedOne clickedOne){
        this.nums = numbers;
        this.clickedOne = clickedOne;
    }

    @NonNull
    @Override
    public NumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.num_item,parent,false);
        return new NumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumViewHolder holder, int position) {
        Integer curNum = nums.get(position);
        holder.bind(curNum);
        holder.getNumber().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedOne.onItemClick(curNum);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nums.size();
    }
}
