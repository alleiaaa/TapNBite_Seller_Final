package com.example.tapnbiteseller;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.tapnbiteseller.fragments.DrinksFragment;
import com.example.tapnbiteseller.fragments.RiceMealsFragment;
import com.example.tapnbiteseller.fragments.SnacksFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class InventoryMenu extends AppCompatActivity {
    private Spinner categorySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_inventory);

        findViewById(R.id.backButton).setOnClickListener(v -> finish());

        getWindow().setStatusBarColor(ContextCompat.getColor(InventoryMenu.this, R.color.primary));

        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        ImageView addIcon = findViewById(R.id.addIcon);
        addIcon.setOnClickListener(v -> showAddProductDialog());
    }

    private void showAddProductDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_product);

        EditText editProductName = dialog.findViewById(R.id.editProductName);
        EditText editProductPrice = dialog.findViewById(R.id.editProductPrice);
        categorySpinner = dialog.findViewById(R.id.categorySpinner);
        Button btnAdd = dialog.findViewById(R.id.btnAdd);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.categories,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            String productName = editProductName.getText().toString().trim();
            String productPrice = editProductPrice.getText().toString().trim();
            String selectedCategory = categorySpinner.getSelectedItem().toString();

            if (productName.isEmpty()) {
                editProductName.setError("Product name is required");
                return;
            }

            if (productPrice.isEmpty()) {
                editProductPrice.setError("Product price is required");
                return;
            }

            double price = Double.parseDouble(productPrice);
            ProductModel newProduct = new ProductModel(productName, price, selectedCategory);

            Fragment currentFragment = getCurrentFragment();
            if (currentFragment instanceof RiceMealsFragment) {
                ((RiceMealsFragment) currentFragment).addProduct(newProduct);
            } else if (currentFragment instanceof DrinksFragment) {
                ((DrinksFragment) currentFragment).addProduct(newProduct);
            } else if (currentFragment instanceof SnacksFragment) {
                ((SnacksFragment) currentFragment).addProduct(newProduct);
            }

            dialog.dismiss();
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private Fragment getCurrentFragment() {
        ViewPager viewPager = findViewById(R.id.viewPager);
        int currentItem = viewPager.getCurrentItem();
        return ((ViewPagerAdapter) viewPager.getAdapter()).getItem(currentItem);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RiceMealsFragment(), "Rice Meals");
        adapter.addFragment(new DrinksFragment(), "Drinks");
        adapter.addFragment(new SnacksFragment(), "Snacks");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }
}