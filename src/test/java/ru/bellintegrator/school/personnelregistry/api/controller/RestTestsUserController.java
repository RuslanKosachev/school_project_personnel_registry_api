package ru.bellintegrator.school.personnelregistry.api.controller;

import org.springframework.core.ParameterizedTypeReference;
import ru.bellintegrator.school.personnelregistry.api.view.UserView;
import ru.bellintegrator.school.personnelregistry.api.model.Employee;
import ru.bellintegrator.school.personnelregistry.api.view.wrapper.Data;

import org.springframework.beans.factory.annotation.Autowired;
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
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Итеграционный rest-тест контроллера - UserController
* */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestTestsUserController {

    private static final String HOST = "http://localhost";
    private static final String API_PATH = "/api/user/";
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
     * тестирует метод getList(), строка http запроса - /api/user/list
     * @exception URISyntaxException при синтаксической ошибки адреса uri
     * @see Data
     * @see UserView
     * */
    @Test
    public void getListTest() throws URISyntaxException {
        final URI URI_LIST = new URI(HOST + ":" + port + API_PATH + "list");

        UserView filterUserView = new UserView();
        filterUserView.setOfficeId(2);
        filterUserView.setFirstName("Виктор");
        filterUserView.setSecondName("Прокопенко");
        filterUserView.setMiddleName("Прокопьевич");
        filterUserView.setPosition("Ассистент");
        filterUserView.setDocCode("21");
        filterUserView.setCitizenshipCode("643");

        HttpEntity<UserView> httpEntity = new HttpEntity<>(filterUserView, headers);
        ResponseEntity<Data<List<UserView>>> response
            = rest.exchange(URI_LIST,
                            HttpMethod.POST,
                            httpEntity,
                            new ParameterizedTypeReference<Data<List<UserView>>>(){});

        Data<List<UserView>> data =  response.getBody();
        List<UserView> countries = data.getData();

        assertEquals(200, response.getStatusCodeValue());
        if (!countries.isEmpty()) {
            assertEquals(1, countries.size());
        } else {
            fail();
        }
    }

    /**
     * тестирует метод getById(), строка http запроса - /api/user/{id}
     * @exception URISyntaxException при синтаксической ошибки адреса uri
     * @see UserView
     * */
    @Test
    public void getByIdTest() throws URISyntaxException {
        final int ID = 1;
        final URI URI_ID = new URI(HOST + ":" + port + API_PATH + ID);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<Data<UserView>> response
                = rest.exchange(URI_ID, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<Data<UserView>>(){});

        UserView userView = response.getBody().getData();

        Assert.assertEquals(200, response.getStatusCodeValue());
        if (Objects.nonNull(userView)) {
            assertThat(userView.getId(), is(ID));
            assertThat(userView.getFirstName(), is("Виктор"));
            assertThat(userView.getSecondName(), is("Прокопенко"));
            assertThat(userView.getPosition(), is("Ассистент"));
            assertThat(userView.getDocCode(), is("21"));
            assertThat(userView.getDocName(), is("паспорт"));
            assertThat(userView.getDocNameCatalog(), is("Паспорт гражданина Российской Федерации"));
            assertThat(userView.getDocNumber(), is("78946"));
            assertEquals(userView.getDocDate(), LocalDate.of(1985,12,4));
            assertThat(userView.getCitizenshipName(), is("Российская Федерация"));
            assertThat(userView.getCitizenshipCode(), is("643"));
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
        final URI URI_SAVE = new URI(HOST + ":" + port + API_PATH + "save");

        UserView newUserView = new UserView();
        newUserView.setOfficeId(3);
        newUserView.setFirstName("createTest");
        newUserView.setSecondName("Викторов");
        newUserView.setMiddleName("Викторович");
        newUserView.setPosition("Ассистент");
        newUserView.setIsIdentified(true);
        newUserView.setPhone("89053215687");
        newUserView.setDocCode("21");
        newUserView.setDocName("паспорту");
        newUserView.setDocDate(LocalDate.of(1985,7,25));
        newUserView.setDocNumber("757575");
        newUserView.setCitizenshipCode("36");

        HttpEntity<UserView> httpEntity = new HttpEntity<>(newUserView, headers);
        ResponseEntity<String> response =
                rest.exchange(URI_SAVE, HttpMethod.POST, httpEntity, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertTrue(response.getBody().contains("result"));
        Assert.assertTrue(response.getBody().contains("success"));

        String queryString = String.format(
            "SELECT e " +
            "FROM %s e  " +
            "left join fetch e.offices as off " +
            "left join fetch e.employeeDocument as e_d " +
            "left join fetch e_d.documentCatalog as i_d_c " +
            "left join fetch e.country as c_c " +
            "WHERE " +
                "e.firstName = :firstName " +
                "AND e.secondName = :secondName " +
                "AND e.middleName = :middleName " +
                "AND e.position = :position " +
                "AND e.isIdentified = :isIdentified " +
                "AND e.phone = :phone " +
                "AND off.id = :officeId " +
                "AND e_d.name = :employeeDocumentName " +
                "AND e_d.number = :employeeDocumentNumber " +
                "AND e_d.date = :employeeDocumentDate " +
                "AND i_d_c.code = :identificationDocumentCatalogCode " +
                "AND c_c.code = :countryCatalogCode ",
            Employee.class.getSimpleName()
        );
        TypedQuery<Employee> query = em.createQuery(queryString, Employee.class);
        query.setParameter("firstName", newUserView.getFirstName());
        query.setParameter("secondName", newUserView.getSecondName());
        query.setParameter("middleName", newUserView.getMiddleName());
        query.setParameter("position", newUserView.getPosition());
        query.setParameter("isIdentified", newUserView.getIsIdentified());
        query.setParameter("phone", newUserView.getPhone());
        query.setParameter("officeId", newUserView.getOfficeId());
        query.setParameter("employeeDocumentName", newUserView.getDocName());
        query.setParameter("employeeDocumentNumber", newUserView.getDocNumber());
        query.setParameter("employeeDocumentDate", newUserView.getDocDate());
        query.setParameter("identificationDocumentCatalogCode", newUserView.getDocCode());
        query.setParameter("countryCatalogCode", newUserView.getCitizenshipCode());
        Assert.assertNotNull(query.getSingleResult());
    }

    /**
     * тестирует метод update(), строка http запроса - /api/user/update
     * @see UserView
     * */
    @Test
    public void updateTest() throws URISyntaxException {
        final URI URI_UPDATE = new URI(HOST + ":" + port + API_PATH + "update");

        UserView exUserView = new UserView();
        exUserView.setId(2);
        exUserView.setFirstName("АнтонUp");
        exUserView.setSecondName("КрукUp");
        exUserView.setMiddleName("КурковичUp");
        exUserView.setPosition("ДоцентUp");
        exUserView.setIsIdentified(false);
        exUserView.setPhone("89093215687");
        exUserView.setDocCode("91");
        exUserView.setDocName("паспортуUp");
        exUserView.setDocDate(LocalDate.of(1985,7,26));
        exUserView.setDocNumber("777777");
        exUserView.setCitizenshipCode("36");

        HttpEntity<UserView> httpEntity = new HttpEntity<>(exUserView, headers);
        ResponseEntity<String> response =
                rest.exchange(URI_UPDATE, HttpMethod.POST, httpEntity, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertTrue(response.getBody().contains("result"));
        Assert.assertTrue(response.getBody().contains("success"));

        String queryString = String.format(
            "SELECT e " +
            "FROM %s e  " +
            "left join fetch e.employeeDocument as e_d " +
            "left join fetch e_d.documentCatalog as i_d_c " +
            "left join fetch e.country as c_c " +
            "WHERE " +
                "e.id = :id " +
                "AND e.firstName = :firstName " +
                "AND e.secondName = :secondName " +
                "AND e.middleName = :middleName " +
                "AND e.position = :position " +
                "AND e.isIdentified = :isIdentified " +
                "AND e.phone = :phone " +
                "AND e_d.name = :employeeDocumentName " +
                "AND e_d.number = :employeeDocumentNumber " +
                "AND e_d.date = :employeeDocumentDate " +
                "AND i_d_c.code = :identificationDocumentCatalogCode " +
                "AND c_c.code = :countryCatalogCode ",
            Employee.class.getSimpleName()
        );
        TypedQuery<Employee> query = em.createQuery(queryString, Employee.class);
        query.setParameter("id", exUserView.getId());
        query.setParameter("firstName", exUserView.getFirstName());
        query.setParameter("secondName", exUserView.getSecondName());
        query.setParameter("middleName", exUserView.getMiddleName());
        query.setParameter("position", exUserView.getPosition());
        query.setParameter("isIdentified", exUserView.getIsIdentified());
        query.setParameter("phone", exUserView.getPhone());
        query.setParameter("employeeDocumentName", exUserView.getDocName());
        query.setParameter("employeeDocumentNumber", exUserView.getDocNumber());
        query.setParameter("employeeDocumentDate", exUserView.getDocDate());
        query.setParameter("identificationDocumentCatalogCode", exUserView.getDocCode());
        query.setParameter("countryCatalogCode", exUserView.getCitizenshipCode());
        Assert.assertNotNull(query.getSingleResult());
    }
}
