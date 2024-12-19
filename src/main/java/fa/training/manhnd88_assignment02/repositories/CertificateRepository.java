package fa.training.manhnd88_assignment02.repositories;

import fa.training.manhnd88_assignment02.dtos.ModalDTO;
import fa.training.manhnd88_assignment02.entities.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate, String> {
    List<Certificate> findByCategory_Id(int categoryID);

    @Modifying
    @Query("SELECT c.category, COUNT(c.id) FROM Certificate c GROUP BY c.category")
    List<ModalDTO> getModals();
}
