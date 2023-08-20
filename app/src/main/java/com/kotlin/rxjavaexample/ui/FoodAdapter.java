package com.kotlin.rxjavaexample.ui;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kotlin.rxjavaexample.R;
import com.kotlin.rxjavaexample.data.remote.meals;
import com.kotlin.rxjavaexample.databinding.FoodCategoryBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    List<meals> list;
    public FoodAdapter(List<meals> list){
        this.list = list;
    }

    static FoodCategoryBinding binding;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = binding.getRoot();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(meals meals) {

            binding.foodItemName.setText(meals.strMeal);
            String url = meals.strMealThumb.replaceAll("\\\\", "");
            Log.d("url", url);
            Picasso.get().load(url).placeholder(R.drawable.burger).fit().into(binding.foodItem);        }
    }
}
