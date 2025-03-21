//Nguyễn Lý Hùng - 22110337

package com.android.projectnhom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.projectnhom.R;
import com.android.projectnhom.entity.Category;
import com.bumptech.glide.Glide;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> categoryList;
    private Context context;
    private OnCategoryClickListener listener;

    public interface OnCategoryClickListener {
        void onCategoryClick(int categoryId);
    }

    // Constructor
    public CategoryAdapter(Context context, List<Category> categoryList, OnCategoryClickListener listener) {
        this.context = context;
        this.categoryList = categoryList;
        this.listener = listener;
    }

    // Tạo ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, null);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categoryList.get(position);

        // Set tên danh mục
        holder.txtCategoryName.setText(category.getCategoryName());

        // Load ảnh từ URL vào ImageView bằng Glide
        Glide.with(context)
                .load(category.getImage())
                .placeholder(R.drawable.ic_launcher_foreground) // Hình ảnh mặc định nếu không có ảnh
                .into(holder.imgCategory);

        // Gán sự kiện click
        holder.itemView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onCategoryClick((int) category.getCategoryId());
                Toast.makeText(context, "Hiển thị Product của Category " + category.getCategoryName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    // Lớp ViewHolder chứa các thành phần UI của mỗi item trong danh sách
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCategoryName;
        ImageView imgCategory;
        Context context;

        TextView txtCateProduct;
        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            txtCategoryName = itemView.findViewById(R.id.tvNameCategory);
            imgCategory = itemView.findViewById(R.id.image_cate);

        }
    }
}
