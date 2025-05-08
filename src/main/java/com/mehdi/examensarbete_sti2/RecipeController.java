package com.mehdi.examensarbete_sti2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RestTemplate restTemplate;

    private final String API_URL_BY_LETTER = "https://www.themealdb.com/api/json/v1/1/search.php?f=";
    private final String API_URL_BY_CATEGORY = "https://www.themealdb.com/api/json/v1/1/filter.php?c=";

    // Visar recept baserat på bokstav eller kategori
    @GetMapping
    public String getRecipes(@RequestParam(required = false) String letter,
                             @RequestParam(required = false) String category,
                             Model model) {
        try {
            RecipeResponse response = null;

            if (letter != null) {
                response = restTemplate.getForObject(API_URL_BY_LETTER + letter, RecipeResponse.class);
            } else if (category != null) {
                response = restTemplate.getForObject(API_URL_BY_CATEGORY + category, RecipeResponse.class);
            }

            if (response != null && response.getMeals() != null) {
                model.addAttribute("recipes", response.getMeals());
            } else {
                model.addAttribute("errorMessage", "Inga recept hittades.");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Ett fel inträffade vid hämtning av recept.");
        }

        return "recipes"; // Renders recipes.html
    }

    // Returnerar alla sparade recept från databasen (valfritt)
    @GetMapping("/all")
    @ResponseBody
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    // Spara ett nytt recept till databasen (valfritt)
    @PostMapping
    @ResponseBody
    public Recipe saveRecipe(@RequestBody Recipe recipe) {
        return recipeService.saveRecipe(recipe);
    }
}
