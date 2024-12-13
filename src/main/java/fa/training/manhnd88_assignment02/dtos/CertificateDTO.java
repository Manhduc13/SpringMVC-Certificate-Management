package fa.training.manhnd88_assignment02.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CertificateDTO {
    String id;

    String cert_name;

    BigDecimal cost;

    String description;

    int categoryID;

    CategoryDTO categoryDTO;
}
