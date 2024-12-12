package fa.training.manhnd88_assignment02.repositories;

import fa.training.manhnd88_assignment02.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
}
