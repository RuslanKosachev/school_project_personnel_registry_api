package ru.bellintegrator.school.personnelregistry.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import ru.bellintegrator.school.personnelregistry.api.view.CountryCatalogView;
import ru.bellintegrator.school.personnelregistry.api.view.wrapper.Data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import javax.persistence.EntityManager;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Итеграционный rest-тест контроллера - CountryController
 * */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestTestsCountryController {

    private static final String HOST = "http://localhost";
    private static final String API_PATH = "/api/countries";
    private static final RestTemplate rest;
    private static final HttpHeaders headers;

    @Autowired
    private EntityManager em;

    static {
        rest = new RestTemplate();
        headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @LocalServerPort
    private int port;

    /**
     * тестирует метод getList(), строка http запроса - /api/countries
     * @exception URISyntaxException при синтаксической ошибки адреса uri
     * @see CountryCatalogView
     * @see Data
     * */
    @Test
    public void getLisTest() throws URISyntaxException {
        final URI URI_LIST = new URI(HOST + ":" + port + API_PATH);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Data<List<CountryCatalogView>>> response
            = rest.exchange(URI_LIST,
                            HttpMethod.POST,
                            httpEntity,
                            new ParameterizedTypeReference<Data<List<CountryCatalogView>>>(){});

        Data<List<CountryCatalogView>> data =  response.getBody();
        List<CountryCatalogView> countries = data.getData();

        assertEquals(200, response.getStatusCodeValue());
        if (!countries.isEmpty()) {
            assertEquals(12, countries.size());
        } else {
            fail();
        }
    }
}
