package kg.inaiproject.booking.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "startDate")
    Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm")
    @Column(name = "endDate")
    Date endDate;

    @ManyToOne
    PeriodType periodType;
}
