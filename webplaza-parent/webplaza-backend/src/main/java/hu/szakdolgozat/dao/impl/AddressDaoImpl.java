package hu.szakdolgozat.dao.impl;

import hu.szakdolgozat.dao.AddressDao;
import hu.szakdolgozat.entity.Address;

import javax.ejb.Stateless;

@Stateless
public class AddressDaoImpl extends JpaCommonEntityDaoImpl<Address> implements AddressDao {
    @Override
    protected Class<Address> getManagedClass() {
        return Address.class;
    }
}
