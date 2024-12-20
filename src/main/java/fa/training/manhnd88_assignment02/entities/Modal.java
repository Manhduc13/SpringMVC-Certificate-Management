package fa.training.manhnd88_assignment02.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Modal {
    int categoryID;
    Long certificateNumbers;
}
