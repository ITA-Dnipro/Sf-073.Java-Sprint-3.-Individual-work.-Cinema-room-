package antifraud.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class CardDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotEmpty
    String number;
    @Column(name = "MAX_ALLOWED")
    Integer maxAllowed;
    @Column(name = "MAX_MANUAL")
    Integer maxManual;
}
