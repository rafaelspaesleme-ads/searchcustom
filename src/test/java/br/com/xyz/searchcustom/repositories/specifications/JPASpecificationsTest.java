package br.com.xyz.searchcustom.repositories.specifications;

import br.com.xyz.searchcustom.entities.User;
import br.com.xyz.searchcustom.repositories.UserRepository;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class JPASpecificationsTest {

    @Autowired
    private UserRepository userRepository;
    private User[] user = new User[10];
    private List<User> users = new ArrayList<>();
    private Faker faker;

    @Before
    public void saveUser(){

        for (int i = 1; i < 10; i++) {
            user[i] = User.UserBuilder.anUser()
                    .withUsername(faker.name().username())
                    .withPassword(faker.crypto().md5())
                    .withFirstName(faker.name().firstName())
                    .withLastName(faker.name().lastName())
                    .withEmail(faker.internet().emailAddress())
                    .withAge(faker.number().randomDigit())
                    .build();
        }

        users.addAll(Arrays.asList(user));

        userRepository.saveAll(users);

    }

    @Test
    public void givenLast_whenGettingListOfUsers_thenCorrect() {
        UserSpecification spec =
                new UserSpecification(new SearchCriteria("lastName", ":", "doe"));

        List<User> results = userRepository.findAll(spec);
        System.out.println(results);

        Assert.assertEquals("Era para vir informações", results.size() > 0, results.size());
    }

}
