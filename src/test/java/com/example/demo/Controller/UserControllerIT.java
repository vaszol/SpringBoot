package com.example.demo.Controller;

import com.example.demo.Entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIT {
    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void getAllUsers() throws Exception {
        ResponseEntity<Collection<User>> responseEntity =
                restTemplate.exchange("http://localhost:8080/all", HttpMethod.GET, null,
                        new ParameterizedTypeReference<Collection<User>>() {
                        });
        Collection<User> listUsers = responseEntity.getBody();

        assertThat(listUsers.size(), is(2));
        List<Integer> ids = listUsers.stream()
                .map(user -> user.getId())
                .collect(toList());
        assertThat(ids, containsInAnyOrder(0, 1));
    }

}