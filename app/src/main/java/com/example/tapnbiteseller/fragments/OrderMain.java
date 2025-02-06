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

import com.example.tapnbiteseller.OrderMenu;
import com.example.tapnbiteseller.R;

public class OrderMain extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order_main, container, false);

        CardView ordersCard = view.findViewById(R.id.ordersCard);
        if (ordersCard != null) {
            ordersCard.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), OrderMenu.class);
                startActivity(intent);
            });
        }

        return view;
    }
}
