package ru.bellintegrator.school.personnelregistry.api.dao;

import ru.bellintegrator.school.personnelregistry.api.Application;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


import ru.bellintegrator.school.personnelregistry.api.model.Office;
import ru.bellintegrator.school.personnelregistry.api.model.Organization;
import ru.bellintegrator.school.personnelregistry.api.service.OrganizationServiceI;
import ru.bellintegrator.school.personnelregistry.api.view.OrganizationView;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional(readOnly = true)
@DirtiesContext
public class OrganizationTest {

    //@Autowired
    //private HouseDao houseDao;

    @Autowired
    private  EntityManager em;

    @Autowired
    private  OrganizationServiceI serv;

    @Test
    public void test() {
        //Organization org = new Organization();

        //Set<Person> list = new HashSet<>();
        //org.setAddress("Address");
        //Person person = new Person("One", 1);
        //person.addHouse(house);
        //house.setPersons(list);
        //list.add(person);
        //houseDao.save(house);

        //List<House> houses = houseDao.all();
        //Assert.assertNotNull(houses);

        //TypedQuery<Organization> query = em.createQuery("SELECT h FROM Organization h", Organization.class);

        //System.out.println(query.getResultList().get(0).getFullName());

        Office of = new Office();
        of.setName("Tttttttttt");
        of.setAddress("ujuj88888888888");
        //of.setOrganization(query.getResultList().get(0));

        em.persist(of);
      /// em.merge(of);

        System.out.println(of.getId() + "-------------------------------------------");

        //em.flush();

        //@Resource
        //EntityTransaction utx = em.getTransaction();


            //utx.begin();
       // em.persist(of);


            //utx.commit();

        //em.close();

        //serv.create(new OrganizationView());
    }
}