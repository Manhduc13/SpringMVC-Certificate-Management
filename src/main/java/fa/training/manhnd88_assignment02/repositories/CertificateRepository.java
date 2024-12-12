package fa.training.manhnd88_assignment02.repositories;

import fa.training.manhnd88_assignment02.entities.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, String> {
}
