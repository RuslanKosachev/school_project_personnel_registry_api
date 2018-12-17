package ru.bellintegrator.school.personnelregistry.api.service;

import ru.bellintegrator.school.personnelregistry.api.view.CountryCatalogView;

import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class CountryCatalogServiceImpl implements CountryCatalogServiceI {

    /**
     * {@inheritDoc}
     */
    public List<CountryCatalogView> getList() {
        CountryCatalogView sampleCountry1 = new CountryCatalogView();
        sampleCountry1.setId(1);
        sampleCountry1.setCode("643");
        sampleCountry1.setName("Российская Федерация");

        CountryCatalogView sampleCountry2 = new CountryCatalogView();
        sampleCountry2.setId(2);
        sampleCountry2.setCode("36");
        sampleCountry2.setName("Австралия");

        CountryCatalogView sampleCountry3 = new CountryCatalogView();
        sampleCountry3.setId(3);
        sampleCountry3.setCode("40");
        sampleCountry3.setName("Австрия");

        List<CountryCatalogView> listCountries = new LinkedList<>();
        listCountries.add(0, sampleCountry1);
        listCountries.add(1, sampleCountry2);
        listCountries.add(2, sampleCountry3);

        return listCountries;
    }
}

