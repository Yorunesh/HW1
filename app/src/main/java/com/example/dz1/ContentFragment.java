package com.example.dz1;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dz1.ClickedOne;
import com.example.dz1.NumAdapter.*;
import java.util.ArrayList;
import java.util.List;
import static com.example.dz1.SelectFragment.newInstance;

public class ContentFragment extends Fragment implements ClickedOne {
    private List<Integer> data;
    private static final String value_for_instance = "tag";

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView= view.findViewById(R.id.main_recycler_view);

        if (data == null && savedInstanceState == null) {
            data = new ArrayList<>();
            insert(data);
        } else {
            data = savedInstanceState.getIntegerArrayList(value_for_instance);
        }


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(requireContext(),3));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(requireContext(),4));
        }

        NumAdapter adapter = new NumAdapter(data,this);
        recyclerView.setAdapter(adapter);

        Button addNum = view.findViewById(R.id.add_num);
        addNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                data.add(data.get(data.size()-1)+1);
                adapter.notifyItemInserted(data.size()-1);
            }
        });
    }

        public void insert(@NonNull List<Integer> data) {
            for (int i = 1; i <= 100; i++) {
                data.add(i);
            }
        }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putIntegerArrayList(value_for_instance,(ArrayList<Integer>)data);
    }

    @Override
    public void onItemClick(int value) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment fragment = newInstance(value);
        fragmentTransaction.add(R.id.main_one,fragment,"MAIN_FRAG").addToBackStack("STACK");
        fragmentTransaction.commit();
    }
}
