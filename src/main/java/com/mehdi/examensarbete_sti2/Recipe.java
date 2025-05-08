package com.mehdi.examensarbete_sti2;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @JsonProperty("idMeal")
    private Long id;

    @JsonProperty("strMeal")
    private String title;

    @JsonProperty("strCategory")
    private String category;

    @JsonProperty("strInstructions")
    private String instructions;

    @JsonProperty("strMealThumb")
    private String thumbnail;

    // Getters och setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}