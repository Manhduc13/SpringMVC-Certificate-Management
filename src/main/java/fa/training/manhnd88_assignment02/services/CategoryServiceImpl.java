package fa.training.manhnd88_assignment02.services;

import fa.training.manhnd88_assignment02.dtos.CategoryDTO;
import fa.training.manhnd88_assignment02.entities.Category;
import fa.training.manhnd88_assignment02.entities.Certificate;
import fa.training.manhnd88_assignment02.repositories.CategoryRepository;
import fa.training.manhnd88_assignment02.repositories.CertificateRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CertificateRepository certificateRepository;

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream().map(category -> {
            return CategoryDTO.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .description(category.getDescription())
                    .build();
        }).toList();
    }

    @Override
    public CategoryDTO findById(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    @Override
    public Page<CategoryDTO> findAllWithPageable(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(category -> {
            return CategoryDTO.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .description(category.getDescription())
                    .build();
        });
    }

    @Override
    public boolean save(CategoryDTO categoryDTO) {
        // Check null request
        if (categoryDTO == null) {
            throw new IllegalArgumentException("Request is null");
        }
        // Check create or update
        if (categoryRepository.existsById(categoryDTO.getId())) {
            // Update
            Category category = categoryRepository.findById(categoryDTO.getId()).get();
            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());
            categoryRepository.save(category);
            return true;
        } else {
            // Create
            Category category = Category.builder()
                    .name(categoryDTO.getName())
                    .description(categoryDTO.getDescription())
                    .build();
            Category savedCategory = categoryRepository.save(category);
            return categoryRepository.existsById(savedCategory.getId());
        }
    }

    @Override
    public boolean create(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            throw new IllegalArgumentException("Category is null");
        }
        if (categoryRepository.findByName(categoryDTO.getName()) != null) {
            throw new IllegalArgumentException("Category name already exists");
        }
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .build();
        categoryRepository.save(category);
        return true;
    }

    @Override
    public boolean update(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            throw new IllegalArgumentException("Category is null");
        }
        Category category = categoryRepository.findById(categoryDTO.getId()).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        Category sameNameCategory = categoryRepository.findByName(categoryDTO.getName());
        if (sameNameCategory != null && sameNameCategory.getId() != category.getId()) {
            throw new IllegalArgumentException("Category name already exists");
        }
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        try {
            categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        // Find category to delete
        Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        // Update product table
        List<Certificate> certificateList = certificateRepository.findByCategory_Id(id);
        for (Certificate certificate : certificateList) {
            certificate.setCategory(null);
            certificateRepository.save(certificate);
        }
        // Delete category
        try {
            categoryRepository.delete(category);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
