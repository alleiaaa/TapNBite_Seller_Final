package com.example.tapnbiteseller;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.widget.ImageButton;

public class AnalyticsMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics_menu);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        getWindow().setStatusBarColor(ContextCompat.getColor(AnalyticsMenu.this, R.color.primary));
    }
}
