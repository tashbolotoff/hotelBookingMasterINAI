package kg.inaiproject.booking.entities;

import kg.inaiproject.booking.enums.Sex;
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
@Table(name = "usr")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "username", unique = true, nullable = false)
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "firstName")
    String firstName;

    @Column(name = "lastName")
    String lastName;

    @Column
    String patronomic;

    @Enumerated(EnumType.STRING)
    Sex sex;

    @Column(name = "birthDate")
    LocalDate birthDate;

    @ManyToOne
    @Column(name = "userRole")
    UserRole userRole;
}