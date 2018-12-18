package ru.bellintegrator.school.personnelregistry.api.controller;

import ru.bellintegrator.school.personnelregistry.api.view.wrapper.Data;
import ru.bellintegrator.school.personnelregistry.api.view.OfficeView;

import org.junit.Ignore;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Итеграционный rest-тест контроллера - OfficeController
 * */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestTestsOfficeController {

    private static final String HOST = "http://localhost";
    private static final String API_PATH = "/api/Office/";
    private static final RestTemplate rest = new RestTemplate();

    @LocalServerPort
    private int port;

    /**
     * тестирует метод getList(), строка http запроса - /api/Office/list
     * @exception URISyntaxException при синтаксической ошибки адреса uri
     * @see Data
     * @see OfficeView
     * */
    @Ignore
    @Test
    public void getListTest() throws URISyntaxException {}

    /**
     * тестирует метод getById(), строка http запроса - /api/organization/{{id:[\d]+}}
     * @exception URISyntaxException при синтаксической ошибки адреса uri
     * @see OfficeView
     * */
    @Test
    public void getByIdTest() throws URISyntaxException {
        int testId = 555;
        URI uri = new URI(HOST + ":" + port + API_PATH + testId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Data> response = rest.exchange(uri, HttpMethod.GET, httpEntity, Data.class);
        HashMap view = (HashMap) response.getBody().getData();

        Assert.assertEquals(200, response.getStatusCodeValue());
        assertThat(view.get("id"), is(testId));
        assertThat(view.get("name"), is("Институт электронной техники и машиностроения"));
        assertThat(view.get("address"), is("г.Саратов"));
        assertThat(view.get("orgId"), is(1));
    }

    /**
     * тестирует метод create(), строка http запроса - /api/Office/save
     * @exception URISyntaxException при синтаксической ошибки адреса uri
     * @see OfficeView
     */
    @Test
    public void createTest() throws URISyntaxException {
        URI uri = new URI(HOST + ":" + port + API_PATH + "save");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        OfficeView sampleOffice = new OfficeView();
        sampleOffice.setName("Институт энергетики и транспортных систем");
        sampleOffice.setAddress("г.Саратов");
        sampleOffice.setOrgId(1);

        HttpEntity<OfficeView> httpEntity = new HttpEntity<>(sampleOffice, headers);

        ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, httpEntity, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertTrue(response.getBody().contains("result"));
        Assert.assertTrue(response.getBody().contains("success"));
    }

    /**
     * тестирует метод update(), строка http запроса - /api/Office/update
     * @exception URISyntaxException при синтаксической ошибки адреса uri
     * @see OfficeView
     * */
    @Ignore
    @Test
    public void updateTest() throws URISyntaxException {}
}
