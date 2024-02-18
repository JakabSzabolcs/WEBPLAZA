package hu.szakdolgozat.service.impl;

import hu.szakdolgozat.entity.Address;
import hu.szakdolgozat.service.AddressService;

import javax.ejb.Stateless;

@Stateless
public class AddressServiceImpl extends JpaCommonEntityServiceImpl<Address> implements AddressService {
}
