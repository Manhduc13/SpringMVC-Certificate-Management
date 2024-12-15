package fa.training.manhnd88_assignment02.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @NotBlank(message = "Certificate Id is required")
    @Pattern(regexp = "^[A-Za-z0-9-]+$", message = "Id wrong format!")
    String id;

    @NotBlank(message = "Certificate name is required")
    String cert_name;

    @NotNull(message = "Certificate price is required")
    BigDecimal cost;

    String description;

    int categoryID;

    CategoryDTO categoryDTO;
}
