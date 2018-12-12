package ru.bellintegrator.school.personnelregistry.api.controller;

import ru.bellintegrator.school.personnelregistry.api.view.UserView;
import ru.bellintegrator.school.personnelregistry.api.view.wrapper.Data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestTestsUserController {

    @LocalServerPort
    int port;

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void getByIdTest() throws URISyntaxException {
        int testId = 22;
        String url = "http://localhost:" + port + "/api/user/" + testId;
        URI uri = new URI(url);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Data> result = restTemplate.exchange(uri, HttpMethod.GET, entity, Data.class);
        HashMap view = (HashMap) result.getBody().getData();

        Assert.assertEquals(200, result.getStatusCodeValue());
        assertThat(view.get("id"), is(testId));
        assertThat(view.get("firstName"), is("Виктор"));
        assertThat(view.get("position"), is("Ассистент"));
        assertThat(view.get("officeId"), is(2));
    }

    @Test
    public void createTest() throws URISyntaxException {
        String url = "http://localhost:" + port + "/api/user/save";
        URI uri = new URI(url);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UserView sampleUser = new UserView();
        sampleUser.setOfficeId(2);
        sampleUser.setFirstName("Виктор");
        sampleUser.setPosition("Ассистент");

        HttpEntity<UserView> entity = new HttpEntity<>(sampleUser, headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertTrue(result.getBody().contains("result"));
        Assert.assertTrue(result.getBody().contains("success"));
    }
}
