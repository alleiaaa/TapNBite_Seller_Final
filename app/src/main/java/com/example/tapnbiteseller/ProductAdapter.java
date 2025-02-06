package com.example.tapnbiteseller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<ProductModel> productList;
    private Context context;
    private OnProductActionListener actionListener;

    public interface OnProductActionListener {
        void onEditName(ProductModel product);
        void onEditPrice(ProductModel product);
        void onEditStock(ProductModel product);
        void onDeleteProduct(ProductModel product);
    }

    public ProductAdapter(Context context, List<ProductModel> productList, OnProductActionListener listener) {
        this.context = context;
        this.productList = productList;
        this.actionListener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductModel product = productList.get(position);

        holder.foodName.setText(product.getName());
        holder.foodPrice.setText(String.format(Locale.getDefault(), "%d Pellets", (int)product.getPrice()));

        holder.foodStock.setText(String.format(Locale.getDefault(), "Stock: %d", product.getStock()));
        holder.foodStatus.setText(product.isAvailable() ? "Available" : "Unavailable");

        if (product.getImageResourceId() != 0) {
            holder.foodImage.setImageResource(product.getImageResourceId());
        }

        holder.optionsButton.setOnClickListener(v -> showPopupMenu(v, product));

        holder.editButton.setOnClickListener(v -> actionListener.onEditName(product));

        holder.stockButton.setOnClickListener(v -> actionListener.onEditStock(product));
    }

    private void showPopupMenu(View view, ProductModel product) {
        PopupMenu popup = new PopupMenu(context, view);
        popup.getMenuInflater().inflate(R.menu.product_options, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.edit_name) {
                actionListener.onEditName(product);
                return true;
            } else if (itemId == R.id.edit_price) {
                actionListener.onEditPrice(product);
                return true;
            } else if (itemId == R.id.delete) {
                showDeleteConfirmationDialog(product);
                return true;
            }
            return false;
        });
        popup.show();
    }

    private void showDeleteConfirmationDialog(ProductModel product) {
        new AlertDialog.Builder(context)
                .setTitle("Delete Product")
                .setMessage("Are you sure you want to delete this product?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    int position = productList.indexOf(product);
                    if (position != -1) {
                        actionListener.onDeleteProduct(product);
                        productList.remove(position);
                        notifyItemRemoved(position);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void addProduct(ProductModel product) {
        productList.add(product);
        notifyItemInserted(productList.size() - 1);
    }

    public void updateProduct(ProductModel updatedProduct) {
        int index = productList.indexOf(updatedProduct);
        if (index != -1) {
            productList.set(index, updatedProduct);
            notifyItemChanged(index);
        }
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImage;
        TextView foodName;
        TextView foodPrice;
        TextView foodStatus;
        TextView foodStock;
        ImageButton optionsButton;
        Button editButton;
        Button stockButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.foodImage);
            foodName = itemView.findViewById(R.id.foodName);
            foodPrice = itemView.findViewById(R.id.foodPrice);
            foodStatus = itemView.findViewById(R.id.foodStatus);
            foodStock = itemView.findViewById(R.id.foodStock);
            optionsButton = itemView.findViewById(R.id.optionsButton);
            editButton = itemView.findViewById(R.id.editButton);
            stockButton = itemView.findViewById(R.id.stockButton);
        }
    }
}