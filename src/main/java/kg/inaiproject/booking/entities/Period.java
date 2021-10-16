package kg.inaiproject.booking.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table
@Entity
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "startDate")
    LocalDate startDate;

    @Column(name = "endDate")
    LocalDate endDate;

    @ManyToOne
    PeriodType periodType;
}
