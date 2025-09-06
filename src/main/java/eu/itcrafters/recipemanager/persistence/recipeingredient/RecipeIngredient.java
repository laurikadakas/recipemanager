package eu.itcrafters.recipemanager.persistence.recipeingredient;

import eu.itcrafters.recipemanager.persistence.ingredient.Ingredient;
import eu.itcrafters.recipemanager.persistence.recipe.Recipe;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "RECIPE_INGREDIENTS")
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "INGREDIENT_ID", nullable = false)
    private Ingredient ingredient;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "RECIPE_ID", nullable = false)
    private Recipe recipe;

    @Size(max = 50)
    @NotNull
    @Column(name = "QUANTITY", nullable = false, length = 50)
    private String quantity;

}