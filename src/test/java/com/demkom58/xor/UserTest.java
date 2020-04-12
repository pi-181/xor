package com.demkom58.xor;

import com.demkom58.xor.entity.Role;
import com.demkom58.xor.entity.User;
import com.demkom58.xor.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collections;

@DataJpaTest
@AutoConfigureTestDatabase
@ContextConfiguration(classes=XorApplication.class)
public class UserTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private User createUser() {
        User source = new User(
                "alexZ",
                "#@&(B_PEC89027e38726,'\"",
                "email@gmail.com",
                "Alex",
                "Kuplinov",
                Collections.singletonList(Role.USER)
        );
        entityManager.persist(source);
        entityManager.flush();

        return source;
    }

    @Test
    public void isLoginValid() {
        final User source = createUser();
        final User deployed = userRepository.findByLogin(source.getLogin());

        Assertions.assertThat(deployed.getLogin()).isNotNull();
        Assertions.assertThat(deployed.getLogin()).isEqualTo(source.getLogin());
        Assertions.assertThat(deployed.getLogin()).isEqualTo(source.getLogin().toLowerCase());

        deployed.setLogin("NewLogin");
        Assertions.assertThat(deployed.getLogin()).isEqualTo(deployed.getLogin().toLowerCase());
    }

    @Test
    public void isRolesValid() {
        final User source = createUser();
        final User deployed = userRepository.findByLogin(source.getLogin());

        Assertions.assertThat(deployed).isNotNull();
        Assertions.assertThat(deployed.getRoles()).isNotEmpty();
    }

    @Test
    public void isIdValid() {
        final User source = createUser();
        final User deployed = userRepository.findByLogin(source.getLogin());

        Assertions.assertThat(deployed.getId()).isNotNull();
        Assertions.assertThat(deployed.getId()).isGreaterThan(-1);
    }

    @Test
    public void isEmailValid() {
        final User source = createUser();
        final User deployed = userRepository.findByLogin(source.getLogin());

        Assertions.assertThat(deployed.getEmail()).isNotNull();
        Assertions.assertThat(deployed.getId()).isGreaterThan(-1);
    }

}
