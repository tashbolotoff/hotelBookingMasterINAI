package kg.inaiproject.booking.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "startDate")
    Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "endDate")
    Date endDate;

    @Column(name = "sum")
    BigDecimal sum;

    @Column(name = "sumBonus")
    BigDecimal sumBonus;

    @Column(name = "generalSum")
    BigDecimal generalSum;
}
