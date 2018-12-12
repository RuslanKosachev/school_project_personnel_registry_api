package ru.bellintegrator.school.personnelregistry.api.controller;

import ru.bellintegrator.school.personnelregistry.api.view.OfficeView;
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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestTestsOfficeController {

    @LocalServerPort
    int port;

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void getByIdTest() throws URISyntaxException {
        int testId = 555;
        String url = "http://localhost:" + port + "/api/office/" + testId;
        URI uri = new URI(url);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Data> result = restTemplate.exchange(uri, HttpMethod.GET, entity, Data.class);
        HashMap view = (HashMap) result.getBody().getData();

        Assert.assertEquals(200, result.getStatusCodeValue());
        assertThat(view.get("id"), is(testId));
        assertThat(view.get("name"), is("Институт электронной техники и машиностроения"));
        assertThat(view.get("address"), is("г.Саратов"));
        assertThat(view.get("orgId"), is(1));
    }

    @Test
    public void createTest() throws URISyntaxException {
        String url = "http://localhost:" + port + "/api/office/save";
        URI uri = new URI(url);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        OfficeView sampleOffice = new OfficeView();
        sampleOffice.setName("Институт энергетики и транспортных систем");
        sampleOffice.setAddress("г.Саратов");
        sampleOffice.setOrgId(1);

        HttpEntity<OfficeView> entity = new HttpEntity<>(sampleOffice, headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertTrue(result.getBody().contains("result"));
        Assert.assertTrue(result.getBody().contains("success"));
    }
}
