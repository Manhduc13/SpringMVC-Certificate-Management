package fa.training.manhnd88_assignment02.controllers;

import fa.training.manhnd88_assignment02.dtos.CategoryDTO;
import fa.training.manhnd88_assignment02.dtos.CertificateDTO;
import fa.training.manhnd88_assignment02.dtos.ModalDTO;
import fa.training.manhnd88_assignment02.services.CategoryService;
import fa.training.manhnd88_assignment02.services.CertificateService;
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

@Controller
@RequestMapping("/certificates")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CertificateController {
    CertificateService certificateService;
    CategoryService categoryService;

    @GetMapping
    public String home(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            Model model) {

        loadData(page, model, new CertificateDTO());

        return "certificates/index";
    }

    @GetMapping("/save/{id}")
    public String save(@PathVariable("id") String id, Model model) {
        CertificateDTO certificateDTO = certificateService.findById(id);
        loadData(0, model, certificateDTO);
        return "certificates/index";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute("certificateDTO") @Valid CertificateDTO certificateDTO,
            BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model
    ) {
        if (bindingResult.hasErrors()) {
            loadData(0, model, certificateDTO);
            return "certificates/index";
        }
        try {
            certificateService.save(certificateDTO);
            redirectAttributes.addFlashAttribute("success", "Certificate saved successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/certificates";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        boolean result = false;
        try {
            result = certificateService.delete(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/certificates";
        }
        if (result) {
            // Redirect to index of categories
            redirectAttributes.addFlashAttribute("success", "Delete category successfully");
        } else {
            // Passing error message to index
            redirectAttributes.addFlashAttribute("error", "Delete category failed");
        }
        return "redirect:/certificates";
    }

    private void loadData(int page, Model model, CertificateDTO certificateDTO) {
        // Pass obj to create form
        model.addAttribute("certificateDTO", certificateDTO);
        // Pass category list to view
        List<CategoryDTO> categoryDTOs = categoryService.findAll();
        model.addAttribute("categoryDTOs", categoryDTOs);
        // Data with pagination
        Pageable pageable = PageRequest.of(page, 5);
        Page<CertificateDTO> certificateDTOs = certificateService.findAllWithPageable(pageable);
        model.addAttribute("certificateDTOs", certificateDTOs.getContent());
        // Pagination
        int pageLimit = 2;
        int totalPages = certificateDTOs.getTotalPages();
        List<Integer> pageNumbers = IntStream
                .range(Math.max(page - pageLimit, 0), Math.min(page + pageLimit + 1, totalPages)).boxed()
                .collect(Collectors.toList());
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("totalPages", certificateDTOs.getTotalPages());
        model.addAttribute("page", page);
        // Get Modals table
        List<ModalDTO> modals = certificateService.getModals();
        model.addAttribute("modals", modals);
    }
}
