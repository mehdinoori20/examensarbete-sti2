package com.mehdi.examensarbete_sti2;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class RecipeResponse {

    @JsonProperty("meals")
    private List<Recipe> meals;

    public List<Recipe> getMeals() {
        return meals;
    }

    public void setMeals(List<Recipe> meals) {
        this.meals = meals;
    }

}
