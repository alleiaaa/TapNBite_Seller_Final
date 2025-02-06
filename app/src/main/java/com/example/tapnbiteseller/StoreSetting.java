package com.example.tapnbiteseller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class StoreSetting extends AppCompatActivity {

    private TextView storeNameDisplay, storeLocationDisplay, storeManagerDisplay, contactNumberDisplay;
    private EditText storeNameInput, storeLocationInput, storeManagerInput, contactNumberInput;
    private Button saveChangesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_setting);


        ImageButton btnBack = findViewById(R.id.btnBack);
        storeNameDisplay = findViewById(R.id.storeNameDisplay);
        storeLocationDisplay = findViewById(R.id.storeLocationDisplay);
        storeManagerDisplay = findViewById(R.id.storeManagerDisplay);
        contactNumberDisplay = findViewById(R.id.contactNumberDisplay);
        storeNameInput = findViewById(R.id.storeNameInput);
        storeLocationInput = findViewById(R.id.storeLocationInput);
        storeManagerInput = findViewById(R.id.storeManagerInput);
        contactNumberInput = findViewById(R.id.contactNumberInput);
        saveChangesButton = findViewById(R.id.saveChangesButton);

        String currentStoreName = "Yumyum";
        String currentStoreLocation = "Canteen What";
        String currentStoreManager = "Mang Kanor";
        String currentContactNumber = "+1234567890";

        storeNameDisplay.setText(currentStoreName);
        storeLocationDisplay.setText(currentStoreLocation);
        storeManagerDisplay.setText(currentStoreManager);
        contactNumberDisplay.setText(currentContactNumber);

        storeNameInput.setText(currentStoreName);
        storeLocationInput.setText(currentStoreLocation);
        storeManagerInput.setText(currentStoreManager);
        contactNumberInput.setText(currentContactNumber);

        btnBack.setOnClickListener(v -> finish());

        saveChangesButton.setOnClickListener(v -> saveStoreDetails());

        getWindow().setStatusBarColor(ContextCompat.getColor(StoreSetting.this, R.color.primary));
    }

    private void saveStoreDetails() {
        String updatedStoreName = storeNameInput.getText().toString().trim();
        String updatedStoreLocation = storeLocationInput.getText().toString().trim();
        String updatedStoreManager = storeManagerInput.getText().toString().trim();
        String updatedContactNumber = contactNumberInput.getText().toString().trim();

        if (updatedStoreName.isEmpty() || updatedStoreLocation.isEmpty() || updatedStoreManager.isEmpty() || updatedContactNumber.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        storeNameDisplay.setText(updatedStoreName);
        storeLocationDisplay.setText(updatedStoreLocation);
        storeManagerDisplay.setText(updatedStoreManager);
        contactNumberDisplay.setText(updatedContactNumber);

        Toast.makeText(this, "Store details updated successfully", Toast.LENGTH_SHORT).show();

    }

    private void saveToDatabase(String storeName, String storeLocation, String storeManager, String contactNumber) {

    }
}