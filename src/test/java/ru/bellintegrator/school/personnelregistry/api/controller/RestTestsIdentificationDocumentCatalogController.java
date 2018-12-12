package ru.bellintegrator.school.personnelregistry.api.controller;

import ru.bellintegrator.school.personnelregistry.api.view.wrapper.Data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestTestsIdentificationDocumentCatalogController {

    @LocalServerPort
    int port;

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void getByIdTest() throws URISyntaxException {
        String url = "http://localhost:" + port + "/api/docs";
        URI uri = new URI(url);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Data> result = restTemplate.exchange(uri, HttpMethod.GET, entity, Data.class);

        Data data =  result.getBody();
        List<HashMap> listCountries = (List<HashMap>) data.getData();

        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(3, listCountries.size());
        assertThat(listCountries.get(2).get("id"), is(10));
        assertThat(listCountries.get(2).get("code"), is("21"));
        assertThat(listCountries.get(2).get("name"), is("Паспорт гражданина Российской Федерации"));
    }
}
