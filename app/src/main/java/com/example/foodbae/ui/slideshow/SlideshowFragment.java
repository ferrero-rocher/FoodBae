package com.example.foodbae.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.example.foodbae.R;
import com.example.foodbae.ui.slideshow.SlideshowViewModel;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment {
    SearchView searchView;
    ListView mylist;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_share);
        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        searchView=(SearchView)root.findViewById(R.id.searchView);
        mylist=(ListView)root.findViewById(R.id.mylist);
        list=new ArrayList<String>();
        list.add("Joeys Pizza");
        list.add("KFC");
        list.add("Golden Dragon");
        list.add("Mainland China");
        list.add("Absolute Barbeques");
        list.add("Jimmis Burger");
        list.add("Delhi Darbar");
        list.add("Roomaniya");
        list.add("The Olives");
        list.add("Flags");
        list.add("Tatus");
        list.add("Udta Punjab");
        list.add("Chotus");
        list.add("Rudys");
        list.add("Warehouse Kitchen");
        list.add("Sai Veg World" +
                "");

        adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,list);
        mylist.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return root;
    }
}