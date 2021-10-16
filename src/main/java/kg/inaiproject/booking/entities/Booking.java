package kg.inaiproject.booking.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    User user;

    @ManyToOne
    Apartment apartment;

    @Column(name = "startDate")
    LocalDateTime startDate;

    @Column(name = "endDate")
    LocalDateTime endDate;

    @Column(name = "sum")
    BigDecimal sum;

    @Column(name = "sumBonus")
    BigDecimal sumBonus;

    @Column(name = "generalSum")
    BigDecimal generalSum;

    @ManyToOne
    Period period;
}
