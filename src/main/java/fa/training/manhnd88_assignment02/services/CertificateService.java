package fa.training.manhnd88_assignment02.services;

import fa.training.manhnd88_assignment02.dtos.CertificateDTO;

import java.util.List;

public interface CertificateService {
    List<CertificateDTO> findAll();

    CertificateDTO findById(String id);

    boolean create(CertificateDTO certificateDTO);

    boolean update(CertificateDTO certificateDTO);

    boolean delete(String id);
}
