package com.example.tapnbiteseller.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.tapnbiteseller.AnalyticsMenu;
import com.example.tapnbiteseller.InventoryMenu;
import com.example.tapnbiteseller.R;

public class InventoryMain extends Fragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_inventory_main, container, false);


        CardView menuCard = view.findViewById(R.id.menuCard);
        menuCard.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InventoryMenu.class);
            startActivity(intent);
        });

        CardView analyticsCard = view.findViewById(R.id.analyticsCard);
        analyticsCard.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AnalyticsMenu.class);
            startActivity(intent);
        });

        return view;
    }
}