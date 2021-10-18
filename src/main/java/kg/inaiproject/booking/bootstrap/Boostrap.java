package kg.inaiproject.booking.bootstrap;

import kg.inaiproject.booking.entities.*;
import kg.inaiproject.booking.enums.Sex;
import kg.inaiproject.booking.repos.*;
import kg.inaiproject.booking.services.ApartmentService;
import kg.inaiproject.booking.services.PeriodTypeService;
import kg.inaiproject.booking.services.TarifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Autowired
    private WalletRepo walletRepo;

    @Autowired
    private PeriodRepo periodRepo;

    @Autowired
    private ApartmentService apartmentService;

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

        Wallet walletAdmin = Wallet.builder()
                .user(admin)
                .balance((double) 0)
                .build();
        walletRepo.save(walletAdmin);

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

        Wallet walletClient = Wallet.builder()
                .user(client)
                .balance((double) 0)
                .build();
        walletRepo.save(walletClient);

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
        PeriodType holidays = PeriodType.builder()
                .name("Holidays")
                .build();
        periodTypeRepo.save(holidays);

        PeriodType schoolTime = PeriodType.builder()
                .name("School Time")
                .build();
        periodTypeRepo.save(schoolTime);

        //Creating apartments
        Apartment firstApartment = Apartment.builder()
                .name("Number #1")
                .tarif(superSaver)
                .countOfRooms(2)
                .build();
        apartmentService.create(firstApartment);

        Apartment secondApartment = Apartment.builder()
                .name("Number #2")
                .tarif(flexible)
                .countOfRooms(3)
                .build();
        apartmentService.create(secondApartment);

        //Creating periods
        Period periodHolidays = Period.builder()
                .startDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-01"))
                .endDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-01"))
                .periodType(holidays)
                .build();
        periodRepo.save(periodHolidays);

        Period periodSchoolTime = Period.builder()
                .startDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-09-01"))
                .endDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-01"))
                .periodType(schoolTime)
                .build();
        periodRepo.save(periodSchoolTime);




    }
}
