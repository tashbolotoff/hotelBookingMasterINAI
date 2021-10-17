package kg.inaiproject.booking.bootstrap;

import kg.inaiproject.booking.entities.PeriodType;
import kg.inaiproject.booking.entities.Tarif;
import kg.inaiproject.booking.entities.User;
import kg.inaiproject.booking.entities.UserRole;
import kg.inaiproject.booking.enums.Sex;
import kg.inaiproject.booking.repos.PeriodTypeRepo;
import kg.inaiproject.booking.repos.TarifRepo;
import kg.inaiproject.booking.repos.UserRepo;
import kg.inaiproject.booking.repos.UserRoleRepo;
import kg.inaiproject.booking.services.PeriodTypeService;
import kg.inaiproject.booking.services.TarifService;
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
    private TarifRepo tarifRepo;

    @Autowired
    private PeriodTypeRepo periodTypeRepo;

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
                .patronimic("Bakytbekovich")
                .phoneNumber("+996555211308")
                .email("b.tashbolotoff@gmail.com")
                .userRole(adminRole)
                .sex(Sex.man)
                .build();
        userRepo.save(admin);

        User client = User.builder()
                .username("client")
                .password(passwordEncoder.encode("123"))
                .firstName("Abdullo")
                .lastName("Karimov")
                .patronimic("Akhmadillaevich")
                .phoneNumber("+996555053202")
                .email("abdullokh-karimov2020@gmail.com")
                .sex(Sex.man)
                .userRole(clientRole)
                .build();
        userRepo.save(client);

        //Creating tarifs
        Tarif superSaver = Tarif.builder()
                .name("Super Saver")
                .price(50.0)
                .build();
        tarifRepo.save(superSaver);

        Tarif flexible = Tarif.builder()
                .name("Flexible")
                .price(100.0)
                .build();
        tarifRepo.save(flexible);

        //Creating period types
        PeriodType periodType = PeriodType.builder()
                .name("Holidays")
                .build();
        periodTypeRepo.save(periodType);

        PeriodType schoolTime = PeriodType.builder()
                .name("School Time")
                .build();
        periodTypeRepo.save(schoolTime);



    }
}
