package fa.training.manhnd88_assignment02.services;

import fa.training.manhnd88_assignment02.dtos.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();

    CategoryDTO findById(int id);

    Page<CategoryDTO> findAllWithPageable(Pageable pageable);

    boolean save(CategoryDTO categoryDTO);

    boolean delete(int id);
}
