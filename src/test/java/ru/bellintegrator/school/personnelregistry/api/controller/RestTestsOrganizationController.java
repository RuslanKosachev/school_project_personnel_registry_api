package ru.bellintegrator.school.personnelregistry.api.controller;

import ru.bellintegrator.school.personnelregistry.api.view.OrganizationView;
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
public class RestTestsOrganizationController {

    @LocalServerPort
    int port;

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void getByIdTest() throws URISyntaxException {
        int testId = 777;
        String url = "http://localhost:" + port + "/api/organization/" + testId;
        URI uri = new URI(url);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Data> result = restTemplate.exchange(uri, HttpMethod.GET, entity, Data.class);
        HashMap view = (HashMap) result.getBody().getData();

        Assert.assertEquals(200, result.getStatusCodeValue());
        assertThat(view.get("id"), is(testId));
        assertThat(view.get("name"), is("СГТУ имени Гагарина Ю.А."));
        assertThat(view.get("fullName"), is("ФГБОУ ВО Саратовский государственный технический университет"));
        assertThat(view.get("inn"), is("8894103143"));
        assertThat(view.get("kpp"), is("794561321"));
        assertThat(view.get("address"), is("г.Саратов"));
    }

    @Test
    public void createTest() throws URISyntaxException {
        String url = "http://localhost:" + port + "/api/organization/save";
        URI uri = new URI(url);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        OrganizationView sampleOrganization = new OrganizationView();
        sampleOrganization.setName("СГТУ имени Гагарина Ю.А.");
        sampleOrganization.setFullName("ФГБОУ ВО Саратовский государственный технический университет");
        sampleOrganization.setInn("8894103143");
        sampleOrganization.setKpp("794561321");
        sampleOrganization.setAddress("г.Саратов");

        HttpEntity<OrganizationView> entity = new HttpEntity<>(sampleOrganization, headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertTrue(result.getBody().contains("result"));
        Assert.assertTrue(result.getBody().contains("success"));
    }
}
