package hu.szakdolgozat.service.impl;

import hu.szakdolgozat.dao.PlazaDao;
import hu.szakdolgozat.entity.Plaza;
import hu.szakdolgozat.service.PlazaService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PlazaServiceImpl extends JpaCommonEntityServiceImpl<Plaza> implements PlazaService {



    @Inject
    private PlazaDao plazaDao;


    @Override
    public Plaza getPlazaByName(String name) {
        return plazaDao.getPlazaByName(name);
    }
}
