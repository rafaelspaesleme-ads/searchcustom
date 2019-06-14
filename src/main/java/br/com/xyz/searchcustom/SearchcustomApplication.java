package br.com.xyz.searchcustom;

import br.com.xyz.searchcustom.entities.User;
import br.com.xyz.searchcustom.repositories.UserRepository;
import br.com.xyz.searchcustom.repositories.specifications.SearchCriteria;
import br.com.xyz.searchcustom.repositories.specifications.UserSpecification;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class SearchcustomApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private List<User> users = new ArrayList<>();
    private Faker faker = new Faker();

    public SearchcustomApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(SearchcustomApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        saveUser();
        UserSpecification spec =
                new UserSpecification(new SearchCriteria("email", ":", "gmail"));

        List<User> results = userRepository.findAll(spec);
        System.out.println("Exibe: ".concat(String.valueOf(results)));
    }

    public void saveUser() {

        for (int i = 1; i < 10; i++) {
            User user = User.UserBuilder.anUser()
                    .withUsername(faker.name().username())
                    .withPassword(faker.crypto().md5())
                    .withFirstName(faker.name().firstName())
                    .withLastName(faker.name().lastName())
                    .withEmail(faker.internet().emailAddress())
                    .withAge(faker.number().randomDigit())
                    .withActive(Boolean.TRUE)
                    .build();
            users.addAll(Collections.singletonList(user));
        }

        userRepository.saveAll(users);

    }

}
