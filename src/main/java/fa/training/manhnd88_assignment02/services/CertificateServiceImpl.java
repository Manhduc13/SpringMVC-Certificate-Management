package fa.training.manhnd88_assignment02.services;

import fa.training.manhnd88_assignment02.dtos.CategoryDTO;
import fa.training.manhnd88_assignment02.dtos.CertificateDTO;
import fa.training.manhnd88_assignment02.dtos.ModalDTO;
import fa.training.manhnd88_assignment02.entities.Category;
import fa.training.manhnd88_assignment02.entities.Certificate;
import fa.training.manhnd88_assignment02.entities.Modal;
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
public class CertificateServiceImpl implements CertificateService {
    CertificateRepository certificateRepository;
    CategoryRepository categoryRepository;
    CategoryService categoryService;

    @Override
    public List<CertificateDTO> findAll() {
        return certificateRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    @Override
    public CertificateDTO findById(String id) {
        Certificate certificate = certificateRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Certificate not found"));
        return mapToDTO(certificate);
    }

    @Override
    public Page<CertificateDTO> findAllWithPageable(Pageable pageable) {
        Page<Certificate> certificates = certificateRepository.findAll(pageable);
        return certificates.map(certificate -> {
            CategoryDTO categoryDTO = categoryService.findById(certificate.getCategory().getId());
            return CertificateDTO.builder()
                    .id(certificate.getId())
                    .name(certificate.getName())
                    .cost(certificate.getCost())
                    .categoryDTO(categoryDTO)
                    .build();
        });
    }

    @Override
    public boolean save(CertificateDTO certificateDTO) {
        // Check null request
        if (certificateDTO == null) {
            throw new IllegalArgumentException("Request is null");
        }
        // Check existed category
        Category category = categoryRepository.findById(certificateDTO.getCategoryID()).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        // Check create or update
        if (certificateRepository.existsById(certificateDTO.getId())) {
            // Update
            Certificate certificate = certificateRepository.findById(certificateDTO.getId()).get();
            certificate.setName(certificateDTO.getName());
            certificate.setCost(certificateDTO.getCost());
            certificate.setCategory(category);
            certificateRepository.save(certificate);
            return true;
        } else {
            // Create
            Certificate certificate = Certificate.builder()
                    .id(certificateDTO.getId())
                    .name(certificateDTO.getName())
                    .cost(certificateDTO.getCost())
                    .category(category)
                    .build();
            Certificate savedCertificate = certificateRepository.save(certificate);
            return certificateRepository.existsById(certificateDTO.getId());
        }
    }

    @Override
    public boolean delete(String id) {
        Certificate certificate = certificateRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Certificate not found"));
        certificateRepository.delete(certificate);
        return !certificateRepository.existsById(id);
    }

    public CertificateDTO mapToDTO(Certificate certificate) {
        CategoryDTO categoryDTO = null;
        if (certificate.getCategory() != null) {
            categoryDTO = CategoryDTO.builder()
                    .id(certificate.getCategory().getId())
                    .name(certificate.getCategory().getName())
                    .description(certificate.getCategory().getDescription())
                    .build();
        }
        return CertificateDTO.builder()
                .id(certificate.getId())
                .name(certificate.getName())
                .cost(certificate.getCost())
                .categoryID(certificate.getCategory().getId())
                .categoryDTO(categoryDTO)
                .build();
    }

    @Override
    public List<ModalDTO> getModals() {
        List<Modal> modals = certificateRepository.getModals();
        return modals.stream().map(modal -> {
            Category category = categoryRepository.findById(modal.getCategoryID()).orElseThrow(() -> new IllegalArgumentException("Category not found"));
            return ModalDTO.builder()
                    .categoryID(modal.getCategoryID())
                    .certificateNumbers(modal.getCertificateNumbers())
                    .categoryName(category.getName())
                    .build();
        }).toList();
    }
}
