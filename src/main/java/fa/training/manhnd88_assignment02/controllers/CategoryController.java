package fa.training.manhnd88_assignment02.controllers;

import fa.training.manhnd88_assignment02.dtos.CategoryDTO;
import fa.training.manhnd88_assignment02.services.CategoryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Controller
@RequestMapping("/categories")
public class CategoryController {
    CategoryService categoryService;

    @GetMapping
    public String findAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            Model model) {
        // Create new categoryDTO
        CategoryDTO categoryDTO = new CategoryDTO();
        // Passing to view to validate data
        model.addAttribute("categoryDTO", categoryDTO);
        // Pagination
        Pageable pageable = PageRequest.of(page, 5);
        Page<CategoryDTO> categoryDTOList = categoryService.findAllWithPageable(pageable);
        model.addAttribute("categoryDTOList", categoryDTOList.getContent());
        model.addAttribute("totalPages", categoryDTOList.getTotalPages());
        model.addAttribute("page", page);
        int pageLimit = 2;
        int totalPages = categoryDTOList.getTotalPages();
        List<Integer> pageNumbers = IntStream
                .range(Math.max(page - pageLimit, 0), Math.min(page + pageLimit + 1, totalPages)).boxed()
                .collect(Collectors.toList());
        model.addAttribute("pageNumbers", pageNumbers);

        return "categories/index";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("categoryDTO") @Valid CategoryDTO categoryDTO,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model,
                         @RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        // Validate data from form
        if (bindingResult.hasErrors()) {
            // Recreate table data and pagination
            Pageable pageable = PageRequest.of(page, 5);
            Page<CategoryDTO> categoryDTOList = categoryService.findAllWithPageable(pageable);
            model.addAttribute("categoryDTOList", categoryDTOList.getContent());
            model.addAttribute("totalPages", categoryDTOList.getTotalPages());
            model.addAttribute("page", page);
            int pageLimit = 2;
            int totalPages = categoryDTOList.getTotalPages();
            List<Integer> pageNumbers = IntStream
                    .range(Math.max(page - pageLimit, 0), Math.min(page + pageLimit + 1, totalPages)).boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
            return "categories/index";
        }
        boolean result = false;
        try {
            result = categoryService.create(categoryDTO);
        } catch (Exception e) {
            // Passing error message to view creating
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/categories";
        }
        if (result) {
            // Redirect to index of categories with success message
            redirectAttributes.addFlashAttribute("success", "Create category success");
        } else {
            // Passing error message to view creating
            redirectAttributes.addFlashAttribute("error", "Create category failed");
        }
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        boolean result = false;
        try {
            result = categoryService.delete(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/categories";
        }
        if (result) {
            // Redirect to index of categories
            redirectAttributes.addFlashAttribute("success", "Delete category successfully");
        } else {
            // Passing error message to index
            redirectAttributes.addFlashAttribute("error", "Delete category failed");
        }
        return "redirect:/categories";
    }

}
