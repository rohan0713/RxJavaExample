package com.kotlin.rxjavaexample.data.remote;

import java.util.List;

public class Response {
    List<meals> meals;

    public Response(List<meals> meals) {
        this.meals = meals;
    }

    public List<meals> getMeals() {
        return meals;
    }

    public void setMeals(List<meals> meals) {
        this.meals = meals;
    }
}
