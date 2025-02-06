package com.example.tapnbiteseller.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tapnbiteseller.HelpCenter;
import com.example.tapnbiteseller.PrivacyPolicy;
import com.example.tapnbiteseller.StoreSetting;
import com.example.tapnbiteseller.R;
import com.example.tapnbiteseller.TermsNConditions;

public class SettingMain extends Fragment {
    private View view;
    private boolean isStoreOpen = true; // Track store status
    private String operatingHours = "7:00 am - 5:00 pm"; // Default operating hours

    private TextView storeStatusTextView;
    private TextView operatingHoursTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting_main, container, false);

        storeStatusTextView = view.findViewById(R.id.currentlyopen);
        operatingHoursTextView = view.findViewById(R.id.times);

        updateStoreStatus();
        updateOperatingHours();

        view.findViewById(R.id.storesettingButton).setOnClickListener(v -> handleStoreSettings());

        // Help Center Button
        view.findViewById(R.id.helpcenter).setOnClickListener(v -> handleHelpCenter());

        // Terms and Conditions Button
        view.findViewById(R.id.terms).setOnClickListener(v -> handleTermsConditions());

        // Privacy Policy Button
        view.findViewById(R.id.privacypolicy).setOnClickListener(v -> handlePrivacyPolicy());

        // Change Store Status Button
        view.findViewById(R.id.changebutton).setOnClickListener(v -> handleChangeStoreStatus());

        // Edit Operating Hours Button
        view.findViewById(R.id.openhoursbutton).setOnClickListener(v -> handleEditOperatingHours());

        // Logout Button
        view.findViewById(R.id.logout).setOnClickListener(v -> handleLogout());

        return view;
    }

    private void updateStoreStatus() {
        storeStatusTextView.setText(isStoreOpen ? "Currently Open" : "Currently Closed");
    }

    private void updateOperatingHours() {
        operatingHoursTextView.setText(operatingHours);
    }

    private void handleStoreSettings() {
        Intent intent = new Intent(getActivity(), StoreSetting.class);
        startActivity(intent);
    }

    private void handleHelpCenter() {
        Intent intent = new Intent(getActivity(), HelpCenter.class);
        startActivity(intent);
    }

    private void handleTermsConditions() {
        Intent intent = new Intent(getActivity(), TermsNConditions.class);
        startActivity(intent);
    }

    private void handlePrivacyPolicy() {

        Intent intent = new Intent(getActivity(), PrivacyPolicy.class);
        startActivity(intent);
    }


    private void handleChangeStoreStatus() {
        new AlertDialog.Builder(getActivity())
                .setTitle("Change Store Status")
                .setMessage("Do you want to change the store status?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    isStoreOpen = !isStoreOpen; // Toggle store status
                    updateStoreStatus(); // Update UI
                    Toast.makeText(getActivity(), "Store status changed", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void handleEditOperatingHours() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Edit Operating Hours");

        final EditText input = new EditText(getActivity());
        input.setHint("Enter new operating hours");
        input.setText(operatingHours);
        builder.setView(input);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String newHours = input.getText().toString().trim();
            if (!newHours.isEmpty()) {
                operatingHours = newHours;
                updateOperatingHours();
                Toast.makeText(getActivity(), "Operating hours updated: " + newHours, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Please enter valid hours", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void handleLogout() {
        Toast.makeText(getActivity(), "Logged out successfully", Toast.LENGTH_SHORT).show();
    }
}