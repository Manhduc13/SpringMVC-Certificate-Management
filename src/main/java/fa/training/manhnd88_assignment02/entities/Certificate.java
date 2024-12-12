package fa.training.manhnd88_assignment02.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "certificates")
public class Certificate {
    @Id
    @Column(length = 12)
    String id;

    String cert_name;

    @Column(precision = 5, scale = 2)
    BigDecimal cost;

    String description;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    Category category;
}
