package ru.bellintegrator.school.personnelregistry.api.controller;

import ru.bellintegrator.school.personnelregistry.api.view.wrapper.Data;
import ru.bellintegrator.school.personnelregistry.api.view.OrganizationView;

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
 * Итеграционный rest-тест контроллера - OrganizationController
 * */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestTestsOrganizationController {

    private static final String HOST = "http://localhost";
    private static final String API_PATH = "/api/organization/";
    private static final RestTemplate rest = new RestTemplate();

    @LocalServerPort
    private int port;

    /**
     * тестирует метод getList(), строка http запроса - /api/organization/list
     * @exception URISyntaxException при синтаксической ошибки адреса uri
     * @see Data
     * @see OrganizationView
     * */
    @Ignore
    @Test
    public void getListTest() throws URISyntaxException {}

    /**
     * тестирует метод getById(), строка http запроса - /api/organization/{{id:[\d]+}}
     * @exception URISyntaxException при синтаксической ошибки адреса uri
     * @see OrganizationView
     * */
    @Test
    public void getByIdTest() throws URISyntaxException {
        int testId = 777;
        URI uri = new URI(HOST + ":" + port + API_PATH + testId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Data> response = rest.exchange(uri, HttpMethod.GET, httpEntity, Data.class);
        HashMap view = (HashMap) response.getBody().getData();

        Assert.assertEquals(200, response.getStatusCodeValue());
        assertThat(view.get("id"), is(testId));
        assertThat(view.get("name"), is("СГТУ имени Гагарина Ю.А."));
        assertThat(view.get("fullName"), is("ФГБОУ ВО Саратовский государственный технический университет"));
        assertThat(view.get("inn"), is("8894103143"));
        assertThat(view.get("kpp"), is("794561321"));
        assertThat(view.get("address"), is("г.Саратов"));
    }

    /**
     * тестирует метод create(), строка http запроса - /api/organization/save
     * @exception URISyntaxException при синтаксической ошибки адреса uri
     * @see OrganizationView
     */
    @Test
    public void createTest() throws URISyntaxException {
        URI uri = new URI(HOST + ":" + port + API_PATH + "save");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        OrganizationView sampleOrganization = new OrganizationView();
        sampleOrganization.setName("СГТУ имени Гагарина Ю.А.");
        sampleOrganization.setFullName("ФГБОУ ВО Саратовский государственный технический университет");
        sampleOrganization.setInn("8894103143");
        sampleOrganization.setKpp("794561321");
        sampleOrganization.setAddress("г.Саратов");

        HttpEntity<OrganizationView> httpEntity = new HttpEntity<>(sampleOrganization, headers);

        ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, httpEntity, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertTrue(response.getBody().contains("result"));
        Assert.assertTrue(response.getBody().contains("success"));
    }

    /**
     * тестирует метод update(), строка http запроса - /api/organization/update
     * @exception URISyntaxException при синтаксической ошибки адреса uri
     * @see OrganizationView
     * */
    @Ignore
    @Test
    public void updateTest() throws URISyntaxException {}
}
