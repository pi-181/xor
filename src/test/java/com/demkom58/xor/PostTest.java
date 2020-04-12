package com.demkom58.xor;

import com.demkom58.xor.entity.Post;
import com.demkom58.xor.entity.Role;
import com.demkom58.xor.entity.User;
import com.demkom58.xor.repository.PostRepository;
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
public class PostTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostRepository postRepository;

    private User createUser() {
        User user = new User(
                "u" + System.currentTimeMillis(),
                "#@&(B_PEC89027e38726,'\"",
                System.currentTimeMillis() + "@gmail.com",
                "Some",
                "User",
                Collections.singletonList(Role.USER)
        );

        entityManager.persist(user);
        entityManager.flush();

        return user;
    }

    private Post create() {
        Post source = new Post("content!!", createUser(), createUser());
        entityManager.persist(source);
        entityManager.flush();
        return source;
    }

    @Test
    public void isIdValid() {
        final Post source = create();
        final Post deployed = postRepository.findPostById(source.getId());

        Assertions.assertThat(deployed.getId()).isNotNull();
        Assertions.assertThat(deployed.getId()).isGreaterThan(-1);
    }

}
