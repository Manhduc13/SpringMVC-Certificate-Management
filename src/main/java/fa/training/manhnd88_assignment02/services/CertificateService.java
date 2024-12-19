package fa.training.manhnd88_assignment02.services;

import fa.training.manhnd88_assignment02.dtos.CertificateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CertificateService {
    List<CertificateDTO> findAll();

    CertificateDTO findById(String id);

    Page<CertificateDTO> findAllWithPageable(Pageable pageable);

    boolean save(CertificateDTO certificateDTO);

    boolean delete(String id);
}
