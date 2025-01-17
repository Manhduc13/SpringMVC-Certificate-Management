package fa.training.manhnd88_assignment02.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ModalDTO {
    int categoryID;
    String categoryName;
    Long certificateNumbers;
}
