package com.example.foodbae.ui.tools;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.foodbae.AustraliaActivity;
import com.example.foodbae.R;
import com.example.foodbae.camera;
import com.example.foodbae.ui.tools.ToolsViewModel;


public class ToolsFragment extends Fragment {


    private ToolsViewModel toolsViewModel;
    private Button capture;
    private Button phone;
    private Button feedsubmit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        Button capture = (Button) root.findViewById(R.id.capture);
        Button phone = (Button) root.findViewById(R.id.phone);
        Button feedsubmit = (Button) root.findViewById(R.id.feedsubmit);
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), camera.class);
                startActivity(intent);
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AustraliaActivity.class);
                startActivity(intent);
            }
        });




        feedsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Thank you for your feedback",Toast.LENGTH_LONG).show();;
            }
        });
        return root;
    }


}