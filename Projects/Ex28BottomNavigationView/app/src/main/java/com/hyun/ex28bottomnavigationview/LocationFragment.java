package com.hyun.ex28bottomnavigationview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LocationFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_location, container, false);
    }

    ArrayList<LocationRecyclerItem> items= new ArrayList<>();
    RecyclerView recyclerView1;
    LocationRecyclerAdapter adapter1;


    ArrayList<LocationRecyclerItem> items2= new ArrayList<>();
    RecyclerView recyclerView2;
    LocationRecyclerAdapter adapter2;


    ArrayList<LocationRecyclerItem> items3= new ArrayList<>();
    RecyclerView recyclerView3;
    LocationRecyclerAdapter adapter3;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        items.add( new LocationRecyclerItem(R.drawable.bg_one01, "루피") );
        items.add( new LocationRecyclerItem(R.drawable.bg_one02, "조로") );
        items.add( new LocationRecyclerItem(R.drawable.bg_one03, "나미") );
        items.add( new LocationRecyclerItem(R.drawable.bg_one04, "우솝") );
        items.add( new LocationRecyclerItem(R.drawable.bg_one05, "상디") );
        items.add( new LocationRecyclerItem(R.drawable.bg_one06, "쵸파") );
        items.add( new LocationRecyclerItem(R.drawable.bg_one07, "프랑크") );
        items.add( new LocationRecyclerItem(R.drawable.bg_one08, "브룩") );
        items.add( new LocationRecyclerItem(R.drawable.bg_one09, "징베") );
        items.add( new LocationRecyclerItem(R.drawable.bg_one10, "니코로빈") );

        recyclerView1= view.findViewById(R.id.recycler1);
        adapter1= new LocationRecyclerAdapter(getActivity(), items);
        recyclerView1.setAdapter(adapter1);



        items2.add( new LocationRecyclerItem(R.drawable.bg_one01, "루피") );
        items2.add( new LocationRecyclerItem(R.drawable.bg_one02, "조로") );
        items2.add( new LocationRecyclerItem(R.drawable.bg_one03, "나미") );
        items2.add( new LocationRecyclerItem(R.drawable.bg_one04, "우솝") );
        items2.add( new LocationRecyclerItem(R.drawable.bg_one05, "상디") );
        items2.add( new LocationRecyclerItem(R.drawable.bg_one06, "쵸파") );
        items2.add( new LocationRecyclerItem(R.drawable.bg_one07, "프랑크") );
        items2.add( new LocationRecyclerItem(R.drawable.bg_one08, "브룩") );
        items2.add( new LocationRecyclerItem(R.drawable.bg_one09, "징베") );
        items2.add( new LocationRecyclerItem(R.drawable.bg_one10, "니코로빈") );

        recyclerView2= view.findViewById(R.id.recycler2);
        adapter2= new LocationRecyclerAdapter(getActivity(), items2);
        recyclerView2.setAdapter(adapter2);



        items3.add( new LocationRecyclerItem(R.drawable.bg_one01, "루피") );
        items3.add( new LocationRecyclerItem(R.drawable.bg_one02, "조로") );
        items3.add( new LocationRecyclerItem(R.drawable.bg_one03, "나미") );
        items3.add( new LocationRecyclerItem(R.drawable.bg_one04, "우솝") );
        items3.add( new LocationRecyclerItem(R.drawable.bg_one05, "상디") );
        items3.add( new LocationRecyclerItem(R.drawable.bg_one06, "쵸파") );
        items3.add( new LocationRecyclerItem(R.drawable.bg_one07, "프랑크") );
        items3.add( new LocationRecyclerItem(R.drawable.bg_one08, "브룩") );
        items3.add( new LocationRecyclerItem(R.drawable.bg_one09, "징베") );
        items3.add( new LocationRecyclerItem(R.drawable.bg_one10, "니코로빈") );

        recyclerView3= view.findViewById(R.id.recycler3);
        adapter3= new LocationRecyclerAdapter(getActivity(), items3);
        recyclerView3.setAdapter(adapter3);

    }
}
