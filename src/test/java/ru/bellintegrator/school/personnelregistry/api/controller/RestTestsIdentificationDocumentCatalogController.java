package ru.bellintegrator.school.personnelregistry.api.controller;

import ru.bellintegrator.school.personnelregistry.api.view.wrapper.Data;
import ru.bellintegrator.school.personnelregistry.api.view.IdentificationDocumentCatalogView;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

/**
 * Итеграционный rest-тест контроллера - IdentificationDocumentCatalogController
 * */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestTestsIdentificationDocumentCatalogController {

    private static final String HOST = "http://localhost";
    private static final String API_PATH = "/api/docs";
    private static final RestTemplate rest = new RestTemplate();

    @LocalServerPort
    private int port;

    /**
     * тестирует метод getList(), строка http запроса - /api/docs
     * @exception URISyntaxException при синтаксической ошибки адреса uri
     * @see IdentificationDocumentCatalogView
     * @see Data
     * */
    @Test
    public void getLisTest() throws URISyntaxException {
        URI uri = new URI(HOST + ":" + port + API_PATH);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Data<List<IdentificationDocumentCatalogView>>> response
            = rest.exchange(uri,
                            HttpMethod.POST,
                            httpEntity,
                            new ParameterizedTypeReference<Data<List<IdentificationDocumentCatalogView>>>(){});

        Data<List<IdentificationDocumentCatalogView>> data =  response.getBody();
        List<IdentificationDocumentCatalogView> documents = data.getData();

        Assert.assertEquals(200, response.getStatusCodeValue());
        if (!documents.isEmpty()) {
            Assert.assertEquals(3, documents.size());
            assertThat(documents.get(2).getId(), is(10));
            assertThat(documents.get(2).getCode(), is("21"));
            assertThat(documents.get(2).getName(), is("Паспорт гражданина Российской Федерации"));
        } else {
            fail();
        }
    }
}
