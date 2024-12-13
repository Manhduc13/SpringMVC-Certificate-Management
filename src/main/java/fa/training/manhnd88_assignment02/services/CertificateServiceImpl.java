package fa.training.manhnd88_assignment02.services;

import fa.training.manhnd88_assignment02.dtos.CategoryDTO;
import fa.training.manhnd88_assignment02.dtos.CertificateDTO;
import fa.training.manhnd88_assignment02.entities.Category;
import fa.training.manhnd88_assignment02.entities.Certificate;
import fa.training.manhnd88_assignment02.repositories.CategoryRepository;
import fa.training.manhnd88_assignment02.repositories.CertificateRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CertificateServiceImpl implements CertificateService {
    CertificateRepository certificateRepository;
    CategoryRepository categoryRepository;

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
    public boolean create(CertificateDTO certificateDTO) {
        if (certificateDTO == null) {
            throw new IllegalArgumentException("Certificate is null");
        }
        Category category = categoryRepository.findById(certificateDTO.getCategoryID()).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        Certificate certificate = Certificate.builder()
                .id(certificateDTO.getId())
                .cert_name(certificateDTO.getCert_name())
                .description(certificateDTO.getDescription())
                .cost(certificateDTO.getCost())
                .category(category)
                .build();
        try {
            certificateRepository.save(certificate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(CertificateDTO certificateDTO) {
        if (certificateDTO == null) {
            throw new IllegalArgumentException("Certificate is null");
        }
        Certificate certificate = certificateRepository.findById(certificateDTO.getId()).orElseThrow(() -> new IllegalArgumentException("Certificate not found"));
        Category category = categoryRepository.findById(certificateDTO.getCategoryID()).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        certificate = Certificate.builder()
                .id(certificateDTO.getId())
                .cert_name(certificateDTO.getCert_name())
                .description(certificateDTO.getDescription())
                .cost(certificateDTO.getCost())
                .category(category)
                .build();
        try {
            certificateRepository.save(certificate);
            return true;
        } catch (Exception e) {
            return false;
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
                .cert_name(certificate.getCert_name())
                .description(certificate.getDescription())
                .cost(certificate.getCost())
                .categoryID(certificate.getCategory().getId())
                .categoryDTO(categoryDTO)
                .build();
    }
}
