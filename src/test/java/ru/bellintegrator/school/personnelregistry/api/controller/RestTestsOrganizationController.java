package ru.bellintegrator.school.personnelregistry.api.controller;

import ru.bellintegrator.school.personnelregistry.api.model.Organization;
import ru.bellintegrator.school.personnelregistry.api.view.wrapper.Data;
import ru.bellintegrator.school.personnelregistry.api.view.OrganizationView;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Итеграционный rest-тест контроллера - OrganizationController
 * */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestTestsOrganizationController {

    private static final String HOST = "http://localhost";
    private static final String API_PATH = "/api/organization/";
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
     * тестирует метод getList(), строка http запроса - /api/organization/list
     * @exception URISyntaxException при синтаксической ошибки адреса uri
     * @see Data
     * @see OrganizationView
     * */
    @Test
    public void getListTest() throws URISyntaxException {
        final URI URI_LIST = new URI(HOST + ":" + port + API_PATH + "list");

        OrganizationView filterOrgView = new OrganizationView();
        filterOrgView.setName("СГТУ имени Гагарина Ю.А.");
        filterOrgView.setFullName("Федеральное государственное бюджетное образовательное учреждение высшего образования «Саратовский государственный технический университет имени Гагарина Ю.А.»");
        filterOrgView.setInn("889410314311");
        filterOrgView.setKpp("794561321");
        filterOrgView.setAddress("г.Саратов");
        filterOrgView.setIsActive(true);
        filterOrgView.setPhone("89061112355");

        HttpEntity<OrganizationView> httpEntity = new HttpEntity<>(filterOrgView, headers);
        ResponseEntity<Data<List<OrganizationView>>> response
            = rest.exchange(URI_LIST,
                            HttpMethod.POST,
                            httpEntity,
                            new ParameterizedTypeReference<Data<List<OrganizationView>>>(){});

        Data<List<OrganizationView>> data =  response.getBody();
        List<OrganizationView> countries = data.getData();

        assertEquals(200, response.getStatusCodeValue());
        if (!countries.isEmpty()) {
            assertEquals(1, countries.size());
        } else {
            fail();
        }
    }

    /**
     * тестирует метод getById(), строка http запроса - /api/organization/{{id:[\d]+}}
     * @exception URISyntaxException при синтаксической ошибки адреса uri
     * @see OrganizationView
     * */
    @Test
    public void getByIdTest() throws URISyntaxException {
        final int ID = 1;
        URI URI_ID = new URI(HOST + ":" + port + API_PATH + ID);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<Data<OrganizationView>> response
                = rest.exchange(URI_ID, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<Data<OrganizationView>>(){});

        Assert.assertEquals(200, response.getStatusCodeValue());

        OrganizationView orgView = response.getBody().getData();

        String queryString = String.format("SELECT org FROM %s org WHERE org.id = :id ",
            Organization.class.getSimpleName());
        TypedQuery<Organization> query = em.createQuery(queryString, Organization.class);
        query.setParameter("id", ID);
        Organization organization = query.getSingleResult();

        Assert.assertEquals(organization.getId(), orgView.getId());
        Assert.assertEquals(organization.getName(), orgView.getName());
        Assert.assertEquals(organization.getFullName(), orgView.getFullName());
        Assert.assertEquals(organization.getInn(), orgView.getInn());
        Assert.assertEquals(organization.getKpp(), orgView.getKpp());
        Assert.assertEquals(organization.getPhone(), orgView.getPhone());
        Assert.assertEquals(organization.getIsActive(), orgView.getIsActive());
        Assert.assertEquals(organization.getAddress(), orgView.getAddress());
    }

    /**
     * тестирует метод create(), строка http запроса - /api/organization/save
     * @exception URISyntaxException при синтаксической ошибки адреса uri
     * @see OrganizationView
     */
    @Test
    public void createTest() throws URISyntaxException {
        final URI URI_SAVE = new URI(HOST + ":" + port + API_PATH + "save");

        OrganizationView newOrgView = new OrganizationView();
        newOrgView.setName("СГАУ");
        newOrgView.setFullName("ФГБОУ ВО Саратовский ГАУ");
        newOrgView.setInn("8994103143");
        newOrgView.setKpp("784561321");
        newOrgView.setAddress("г.Саратов");
        newOrgView.setPhone("89061112364");
        newOrgView.setIsActive(true);

        HttpEntity<OrganizationView> httpEntity = new HttpEntity<>(newOrgView, headers);

        ResponseEntity<String> response
                = rest.exchange(URI_SAVE, HttpMethod.POST, httpEntity, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertTrue(response.getBody().contains("result"));
        Assert.assertTrue(response.getBody().contains("success"));

        String queryString = String.format(
            "SELECT org " +
                "FROM %s org  " +
                "WHERE " +
                "org.name = :name " +
                "AND org.fullName = :fullName " +
                "AND org.inn = :inn " +
                "AND org.kpp = :kpp " +
                "AND org.address = :address " +
                "AND org.phone = :phone " +
                "AND org.isActive = :isActive ",
            Organization.class.getSimpleName()
        );
        TypedQuery<Organization> query = em.createQuery(queryString, Organization.class);
        query.setParameter("name", newOrgView.getName());
        query.setParameter("fullName", newOrgView.getFullName());
        query.setParameter("inn", newOrgView.getInn());
        query.setParameter("kpp", newOrgView.getKpp());
        query.setParameter("address", newOrgView.getAddress());
        query.setParameter("phone", newOrgView.getPhone());
        query.setParameter("isActive", newOrgView.getIsActive());
        Assert.assertNotNull(query.getSingleResult());
    }

    /**
     * тестирует метод update(), строка http запроса - /api/organization/update
     * @exception URISyntaxException при синтаксической ошибки адреса uri
     * @see OrganizationView
     * */
    @Test
    public void updateTest() throws URISyntaxException {
        final URI URI_UPDATE = new URI(HOST + ":" + port + API_PATH + "update");

        OrganizationView upOrgView = new OrganizationView();
        upOrgView.setId(1);
        upOrgView.setName("Up СГТУ");
        upOrgView.setFullName("Up ФГБОУ ВО Саратовский ГТУ");
        upOrgView.setInn("888888888888");
        upOrgView.setKpp("711111111");
        upOrgView.setAddress("Up г.Саратов");
        upOrgView.setPhone("89061111111");
        upOrgView.setIsActive(false);

        HttpEntity<OrganizationView> httpEntity = new HttpEntity<>(upOrgView, headers);

        ResponseEntity<String> response
                = rest.exchange(URI_UPDATE, HttpMethod.POST, httpEntity, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertTrue(response.getBody().contains("result"));
        Assert.assertTrue(response.getBody().contains("success"));

        String queryString = String.format(
            "SELECT org " +
                "FROM %s org  " +
                "WHERE " +
                "org.id = :id " +
                "AND org.fullName = :fullName " +
                "AND org.inn = :inn " +
                "AND org.kpp = :kpp " +
                "AND org.address = :address " +
                "AND org.phone = :phone " +
                "AND org.isActive = :isActive ",
            Organization.class.getSimpleName()
        );
        TypedQuery<Organization> query = em.createQuery(queryString, Organization.class);
        query.setParameter("id", upOrgView.getId());
        query.setParameter("fullName", upOrgView.getFullName());
        query.setParameter("inn", upOrgView.getInn());
        query.setParameter("kpp", upOrgView.getKpp());
        query.setParameter("address", upOrgView.getAddress());
        query.setParameter("phone", upOrgView.getPhone());
        query.setParameter("isActive", upOrgView.getIsActive());
        Assert.assertNotNull(query.getSingleResult());
    }
}
