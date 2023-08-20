package com.kotlin.rxjavaexample.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.kotlin.rxjavaexample.R;
import com.kotlin.rxjavaexample.data.remote.Response;
import com.kotlin.rxjavaexample.data.remote.meals;
import com.kotlin.rxjavaexample.databinding.ActivityHomeBinding;
import com.kotlin.rxjavaexample.network.Retrofit;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    List<meals> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        binding.rvFood.setLayoutManager(new GridLayoutManager(this, 2));
        Observable<Response> observable = Retrofit.getClient().getFoodList("Indian").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe();

        Log.d("list", list.toString());
        binding.rvFood.setAdapter(new FoodAdapter(list));
    }

    private void handleResults(Response response) {
        if (response.getMeals() != null && response.getMeals().size() != 0) {
            binding.rvFood.setAdapter(new FoodAdapter(response.getMeals()));
        }
    }

    private void handleResults(List<meals> meals) {
        if (meals != null && meals.size() != 0) {

            binding.rvFood.setAdapter(new FoodAdapter(meals));

        } else {
            Toast.makeText(this, "NO RESULTS FOUND",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void handleError(Throwable t) {

        Toast.makeText(this, "ERROR IN FETCHING API RESPONSE. Try again",
                Toast.LENGTH_LONG).show();
    }
}