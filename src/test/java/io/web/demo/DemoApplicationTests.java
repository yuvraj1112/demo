package io.web.demo;

import io.web.demo.entities.Role;
import io.web.demo.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

    @Autowired
    private TestRestTemplate rt;

    @LocalServerPort
    private int port;

    String baseURL = "http://localhost:";

    static User u1, u2;
    static Role r1, r2;

    @BeforeEach
    public void setUp() {
        u1 = new User();
        u1.setId(100L);
        u1.setFirstName("Elon");
        u1.setLastName("Musk");


        r1 = new Role();
        r1.setId(10L);
        r1.setName("CEO");


    }


    @Test
    public void createUser() {
        ResponseEntity<?> userResponseEntity = rt.postForEntity(baseURL + port + "/api/user/", u1, User.class);
        Assertions.assertThat(userResponseEntity.getBody()).isEqualTo(u1);
    }

    @Test
    public void createRole() {
        ResponseEntity<?> roleResponseEntity = rt.postForEntity(baseURL + port + "/api/role/", r1, Role.class);
        Assertions.assertThat(roleResponseEntity.getBody()).isEqualTo(r1);
    }

    @Test
    public void getAllUsers() {
        ResponseEntity<?> userResponseEntity = rt.postForEntity(baseURL + port + "/api/user/", u1, User.class);
        ResponseEntity<List<User>> response = rt.exchange(baseURL + port + "/api/user/all/", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        });
        Assertions.assertThat(response.getBody()).contains(u1);
    }

    @Test
    public void assigneRole() {
        ResponseEntity<?> userResponseEntity = rt.postForEntity(baseURL + port + "/api/user/", u1, User.class);
        ResponseEntity<?> roleResponseEntity = rt.postForEntity(baseURL + port + "/api/role/", r1, Role.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, Long> map= new LinkedMultiValueMap<>();

        map.add("user-id", u1.getId());
        map.add("role-id", r1.getId());

        HttpEntity<MultiValueMap<String, Long>> request = new HttpEntity<MultiValueMap<String, Long>>(map, headers);
        ResponseEntity<String> responseEntity = rt.postForEntity(baseURL + port + "/api/assign-role/",request,String.class);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo("User " + u1.getFirstName() + " " + u1.getLastName() + " assigned the role " + r1.getName());
    }

    @Test
    public void getAllRoles() {
        ResponseEntity<?> roleResponseEntity = rt.postForEntity(baseURL + port + "/api/role/", r1, Role.class);
        ResponseEntity<List<Role>> response = rt.exchange(baseURL + port + "/api/role/all/", HttpMethod.GET, null, new ParameterizedTypeReference<List<Role>>() {
        });
        Assertions.assertThat(response.getBody()).contains(r1);
    }
}
