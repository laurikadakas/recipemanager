package eu.itcrafters.recipemanager.persistence.cuisinetype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CuisineTypeRepository extends JpaRepository<CuisineType, Integer> {

    @Query("select c from CuisineType c where upper(c.name) = upper(:typeName)")
    Optional<CuisineType> findCuisineTypeBy(String typeName);
}
