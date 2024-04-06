package hu.szakdolgozat.service;

import hu.szakdolgozat.entity.Plaza;

public interface PlazaService extends JpaCommonEntityService<Plaza>{
    Plaza getPlazaByName(String name);
}
