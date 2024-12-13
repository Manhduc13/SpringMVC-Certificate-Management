package fa.training.manhnd88_assignment02.repositories;

import fa.training.manhnd88_assignment02.entities.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate, String> {
    List<Certificate> findByCategory_Id(int categoryID);
}
