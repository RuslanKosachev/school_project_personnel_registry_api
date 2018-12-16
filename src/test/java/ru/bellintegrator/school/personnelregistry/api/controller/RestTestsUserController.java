package ru.bellintegrator.school.personnelregistry.api.controller;

import org.springframework.core.ParameterizedTypeReference;
import ru.bellintegrator.school.personnelregistry.api.view.UserView;

import org.junit.Ignore;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import ru.bellintegrator.school.personnelregistry.api.view.wrapper.Data;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

/**
 * Итеграционный rest-тест контроллера - UserController
* */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestTestsUserController {

    private static final String HOST = "http://localhost";
    private static final String API_PATH = "/api/user/";
    private static final RestTemplate rest = new RestTemplate();

    @LocalServerPort
    private int port;

    /**
     * тестирует метод getList(), строка http запроса - /api/user/list
     * @exception URISyntaxException при синтаксической ошибки адреса uri
     * @see Data
     * @see UserView
     * */
    @Ignore
    @Test
    public void getListTest() throws URISyntaxException {}

    /**
     * тестирует метод getById(), строка http запроса - /api/user/{{id:[\d]+}}
     * @exception URISyntaxException при синтаксической ошибки адреса uri
     * @see UserView
     * */
    @Test
    public void getByIdTest() throws URISyntaxException {
        int testId = 22;
        URI uri = new URI(HOST + ":" + port + API_PATH + testId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Data<UserView>> response = rest.exchange(uri, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<Data<UserView>>(){});

        UserView view = response.getBody().getData();

        Assert.assertEquals(200, response.getStatusCodeValue());
        if (Objects.nonNull(view)) {
            assertThat(view.getId(), is(testId));
            assertThat(view.getFirstName(), is("Виктор"));
            assertThat(view.getPosition(), is("Ассистент"));
            assertThat(view.getOfficeId(), is(2));
        } else {
            fail();
        }
    }

    /**
     * тестирует метод create(), строка http запроса - /api/user/save
     * @exception URISyntaxException при синтаксической ошибки адреса uri
     * @see UserView
     */
    @Test
    public void createTest() throws URISyntaxException {
        URI uri = new URI(HOST + ":" + port + API_PATH + "save");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UserView expectedUser = new UserView();
        expectedUser.setOfficeId(2);
        expectedUser.setFirstName("Виктор");
        expectedUser.setPosition("Ассистент");
        HttpEntity<UserView> httpEntity = new HttpEntity<>(expectedUser, headers);

        ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, httpEntity, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertTrue(response.getBody().contains("result"));
        Assert.assertTrue(response.getBody().contains("success"));
    }

    /**
     * тестирует метод update(), строка http запроса - /api/user/update
     * @see UserView
     * */
    @Ignore
    @Test
    public void updateTest() throws URISyntaxException {}
}
