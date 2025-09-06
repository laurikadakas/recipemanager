package eu.itcrafters.recipemanager.persistence.ingredient;

import eu.itcrafters.recipemanager.persistence.recipeingredient.RecipeIngredient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "INGREDIENT")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "INGREDIENT_NAME", nullable = false, length = 50)
    private String ingredientName;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> recipes = new ArrayList<>();

}