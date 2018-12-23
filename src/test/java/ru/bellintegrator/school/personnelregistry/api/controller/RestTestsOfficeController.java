package ru.bellintegrator.school.personnelregistry.api.controller;

import org.springframework.core.ParameterizedTypeReference;
import ru.bellintegrator.school.personnelregistry.api.view.wrapper.Data;
import ru.bellintegrator.school.personnelregistry.api.view.OfficeView;
import ru.bellintegrator.school.personnelregistry.api.model.Office;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Итеграционный rest-тест контроллера - OfficeController
 * */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestTestsOfficeController {

    private static final String HOST = "http://localhost";
    private static final String API_PATH = "/api/office/";
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
     * тестирует метод getList(), строка http запроса - /api/Office/list
     * @exception URISyntaxException при синтаксической ошибки адреса uri
     * @see Data
     * @see OfficeView
     * */
    @Test
    public void getListTest() throws URISyntaxException {
        final URI URI_LIST = new URI(HOST + ":" + port + API_PATH + "list");

        OfficeView filterOfficeView = new OfficeView();
        filterOfficeView.setName("Институт электронной техники и машиностроения");
        filterOfficeView.setAddress("г.Саратов");
        filterOfficeView.setPhone("89061112364");
        filterOfficeView.setIsActive(true);
        filterOfficeView.setOrgId(1);

        HttpEntity<OfficeView> httpEntity = new HttpEntity<>(filterOfficeView, headers);
        ResponseEntity<Data<List<OfficeView>>> response
            = rest.exchange(URI_LIST,
                            HttpMethod.POST,
                            httpEntity,
                            new ParameterizedTypeReference<Data<List<OfficeView>>>(){});

        Data<List<OfficeView>> data =  response.getBody();
        List<OfficeView> countries = data.getData();

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
     * @see OfficeView
     * */
    @Test
    public void getByIdTest() throws URISyntaxException {
        final int ID = 1;
        URI URI_ID = new URI(HOST + ":" + port + API_PATH + ID);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<Data<OfficeView>> response
                = rest.exchange(URI_ID, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<Data<OfficeView>>(){});

        Assert.assertEquals(200, response.getStatusCodeValue());

        OfficeView officeView = response.getBody().getData();

        String queryString = String.format("SELECT off FROM %s off WHERE off.id = :id ", Office.class.getSimpleName());
        TypedQuery<Office> query = em.createQuery(queryString, Office.class);
        query.setParameter("id", ID);
        Office office = query.getSingleResult();

        Assert.assertEquals(office.getId(), officeView.getId());
        Assert.assertEquals(office.getName(), officeView.getName());
        Assert.assertEquals(office.getAddress(), officeView.getAddress());
        Assert.assertEquals(office.getPhone(), officeView.getPhone());
        Assert.assertEquals(office.getIsActive(), officeView.getIsActive());
        Assert.assertEquals(office.getOrganization().getId(), officeView.getOrgId());
    }

    /**
     * тестирует метод create(), строка http запроса - /api/Office/save
     * @exception URISyntaxException при синтаксической ошибки адреса uri
     * @see OfficeView
     */
    @Test
    public void createTest() throws URISyntaxException {
        final URI URI_SAVE = new URI(HOST + ":" + port + API_PATH + "save");

        OfficeView newOfficeView = new OfficeView();
        newOfficeView.setName("Институт социального и производственного менеджмента");
        newOfficeView.setAddress("г.Саратов");
        newOfficeView.setPhone("89061112364");
        newOfficeView.setIsActive(true);
        newOfficeView.setOrgId(1);

        HttpEntity<OfficeView> httpEntity = new HttpEntity<>(newOfficeView, headers);
        ResponseEntity<String> response
                = rest.exchange(URI_SAVE, HttpMethod.POST, httpEntity, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertTrue(response.getBody().contains("result"));
        Assert.assertTrue(response.getBody().contains("success"));

        String queryString = String.format(
                "SELECT off " +
                        "FROM %s off  " +
                        "left join fetch off.organization as org " +
                        "WHERE " +
                        "off.name = :name " +
                        "AND off.address = :address " +
                        "AND off.phone = :phone " +
                        "AND off.isActive = :isActive " +
                        "AND org.id = :OrgId ",
                Office.class.getSimpleName()
        );
        TypedQuery<Office> query = em.createQuery(queryString, Office.class);
        query.setParameter("name", newOfficeView.getName());
        query.setParameter("address", newOfficeView.getAddress());
        query.setParameter("phone", newOfficeView.getPhone());
        query.setParameter("isActive", newOfficeView.getIsActive());
        query.setParameter("OrgId", newOfficeView.getOrgId());
        Assert.assertNotNull(query.getSingleResult());
    }

    /**
     * тестирует метод update(), строка http запроса - /api/Office/update
     * @exception URISyntaxException при синтаксической ошибки адреса uri
     * @see OfficeView
     * */
    @Test
    public void updateTest() throws URISyntaxException {
        final URI URI_UPDATE = new URI(HOST + ":" + port + API_PATH + "update");

        OfficeView upOfficeView = new OfficeView();
        upOfficeView.setId(5);
        upOfficeView.setName("UpИнститут урбанистики, архитектуры и строительства");
        upOfficeView.setAddress("Up г.Саратов");
        upOfficeView.setPhone("899999999");
        upOfficeView.setIsActive(false);

        HttpEntity<OfficeView> httpEntity = new HttpEntity<>(upOfficeView, headers);
        ResponseEntity<String> response
                = rest.exchange(URI_UPDATE, HttpMethod.POST, httpEntity, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertTrue(response.getBody().contains("result"));
        Assert.assertTrue(response.getBody().contains("success"));

        String queryString = String.format(
            "SELECT off " +
            "FROM %s off  " +
            "WHERE " +
                "off.id = :id " +
                "AND off.name = :name " +
                "AND off.address = :address " +
                "AND off.phone = :phone " +
                "AND off.isActive = :isActive ",
            Office.class.getSimpleName()
        );
        TypedQuery<Office> query = em.createQuery(queryString, Office.class);
        query.setParameter("id", upOfficeView.getId());
        query.setParameter("name", upOfficeView.getName());
        query.setParameter("address", upOfficeView.getAddress());
        query.setParameter("phone", upOfficeView.getPhone());
        query.setParameter("isActive", upOfficeView.getIsActive());
        Assert.assertNotNull(query.getSingleResult());
    }
}
