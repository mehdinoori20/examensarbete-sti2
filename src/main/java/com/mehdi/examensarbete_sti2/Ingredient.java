package com.mehdi.examensarbete_sti2;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String title;
    private String category;
    private String instructions;
    private String thumbnail;
}