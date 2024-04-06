package hu.szakdolgozat.dao;

import hu.szakdolgozat.entity.Plaza;

public interface PlazaDao extends JpaCommonEntityDao<Plaza> {
    Plaza getPlazaByName(String name);
}
