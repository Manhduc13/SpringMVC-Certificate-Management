package fa.training.manhnd88_assignment02.services;

import fa.training.manhnd88_assignment02.dtos.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();

    CategoryDTO findById(int id);

    boolean create(CategoryDTO categoryDTO);

    boolean update(CategoryDTO categoryDTO);

    boolean delete(int id);
}
