package kg.inaiproject.booking.bootstrap;

import kg.inaiproject.booking.entities.User;
import kg.inaiproject.booking.entities.UserRole;
import kg.inaiproject.booking.enums.Sex;
import kg.inaiproject.booking.repos.UserRepo;
import kg.inaiproject.booking.repos.UserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Boostrap implements CommandLineRunner {

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        //Creating all user roles
        UserRole adminRole = UserRole.builder()
                .name("ROLE_ADMIN")
                .build();
        userRoleRepo.save(adminRole);

        UserRole clientRole = UserRole.builder()
                .name("ROLE_CLIENT")
                .build();
        userRoleRepo.save(clientRole);

        //Creating users
        User admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("123"))
                .firstName("Bekbulat")
                .lastName("Tashbolotov")
                .patronomic("Bakytbekovich")
                .userRole(adminRole)
                .sex(Sex.man)
                .build();
        userRepo.save(admin);

        User client = User.builder()
                .username("client")
                .password(passwordEncoder.encode("123"))
                .firstName("Abdullo")
                .lastName("Karimov")
                .patronomic("Akhmadillaevich")
                .sex(Sex.man)
                .userRole(clientRole)
                .build();
        userRepo.save(client);

    }
}
