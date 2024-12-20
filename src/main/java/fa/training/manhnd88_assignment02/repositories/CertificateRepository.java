package fa.training.manhnd88_assignment02.repositories;

import fa.training.manhnd88_assignment02.entities.Certificate;
import fa.training.manhnd88_assignment02.entities.Modal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate, String> {
    List<Certificate> findByCategory_Id(int categoryID);

    @Query("SELECT new fa.training.manhnd88_assignment02.entities.Modal(c.category.id, COUNT(c.id)) FROM Certificate c GROUP BY c.category.id")
    List<Modal> getModals();
}
