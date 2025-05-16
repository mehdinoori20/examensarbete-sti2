package com.mehdi.examensarbete_sti2;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class APIClient {

    private final RestTemplate restTemplate;

    public APIClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Hämtar recept baserat på första bokstaven
    public List<Recipe> getRecipesByFirstLetter(String letter) {
        String url = "https://www.themealdb.com/api/json/v1/1/search.php?f=" + letter;

        RecipeResponse response = restTemplate.getForObject(url, RecipeResponse.class);

        if (response != null && response.getMeals() != null) {
            return response.getMeals();
        }
        return null;
    }
}