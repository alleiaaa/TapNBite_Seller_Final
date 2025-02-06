package com.example.tapnbiteseller.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tapnbiteseller.R;
import com.example.tapnbiteseller.ProductAdapter;
import com.example.tapnbiteseller.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class SnacksFragment extends Fragment implements ProductAdapter.OnProductActionListener {
    private RecyclerView recyclerViewProducts;
    private ProductAdapter productAdapter;
    private List<ProductModel> productList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_rice_meals, container, false);

        productList = new ArrayList<>();

        recyclerViewProducts = view.findViewById(R.id.recyclerViewProducts);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getContext()));

        productAdapter = new ProductAdapter(requireContext(), productList, this);
        recyclerViewProducts.setAdapter(productAdapter);

        return view;
    }

    public void addProduct(String name, double price) {
        ProductModel newProduct = new ProductModel(name, price, "Rice Meals", 15, true);
        newProduct.setImageResourceId(R.drawable.adobo); // Default image, change as needed
        productAdapter.addProduct(newProduct);
    }

    @Override
    public void onEditName(ProductModel product) {
        showEditDialog(product, "name");
    }

    @Override
    public void onEditPrice(ProductModel product) {
        showEditDialog(product, "price");
    }

    @Override
    public void onEditStock(ProductModel product) {
        showEditDialog(product, "stock");
    }

    @Override
    public void onDeleteProduct(ProductModel product) {
        Toast.makeText(getContext(), "Product Deleted", Toast.LENGTH_SHORT).show();
    }

    public void addProduct(ProductModel product) {
        productList.add(product);
        productAdapter.notifyItemInserted(productList.size() - 1);
    }

    private void showEditDialog(ProductModel product, String editType) {
        Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_product);

        EditText editText = dialog.findViewById(R.id.editProductName);
        Button btnSave = dialog.findViewById(R.id.btnAdd);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);

        switch (editType) {
            case "name":
                editText.setHint("Enter new product name");
                editText.setText(product.getName());
                break;
            case "price":
                editText.setHint("Enter new product price");
                editText.setText(String.valueOf(product.getPrice()));
                editText.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
                break;
            case "stock":
                editText.setHint("Enter new stock quantity");
                editText.setText(String.valueOf(product.getStock()));
                editText.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
                break;
        }

        btnSave.setOnClickListener(v -> {
            String newValue = editText.getText().toString().trim();
            if (!newValue.isEmpty()) {
                try {
                    switch (editType) {
                        case "name":
                            product.setName(newValue);
                            break;
                        case "price":
                            double newPrice = Double.parseDouble(newValue);
                            product.setPrice(newPrice);
                            break;
                        case "stock":
                            int newStock = Integer.parseInt(newValue);
                            product.setStock(newStock);
                            break;
                    }
                    productAdapter.updateProduct(product);
                    dialog.dismiss();
                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }
            } else {
                editText.setError("Cannot be empty");
            }
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

}