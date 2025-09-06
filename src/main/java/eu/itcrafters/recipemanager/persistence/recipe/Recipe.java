package eu.itcrafters.recipemanager.persistence.recipe;

import eu.itcrafters.recipemanager.persistence.cuisinetype.CuisineType;
import eu.itcrafters.recipemanager.persistence.instruction.Instruction;
import eu.itcrafters.recipemanager.persistence.recipeingredient.RecipeIngredient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "RECIPE")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CUISINE_TYPE_ID", nullable = false)
    private CuisineType cuisineType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "INSTRUCTION_ID", nullable = false)
    private Instruction instruction;

    @Size(max = 50)
    @NotNull
    @Column(name = "RECIPE_NAME", nullable = false, length = 50)
    private String recipeName;

    @NotNull
    @Column(name = "DATE_ADDED", nullable = false)
    private LocalDate dateAdded;

    @Size(max = 255)
    @NotNull
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> ingredients = new ArrayList<>();

}