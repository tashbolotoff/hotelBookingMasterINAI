package kg.inaiproject.booking.entities;

import kg.inaiproject.booking.enums.Sex;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "patronimic")
    String patronimic;

    @Enumerated(EnumType.STRING)
    Sex sex;

    @Column(name = "phoneNumber")
    String phoneNumber;

    @Column(name = "email")
    String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthDate")
    Date birthDate;

    @ManyToOne
    UserRole userRole;
}