package com.finstream.person.repository.organizational.business;

import org.springframework.data.repository.NoRepositoryBean;

import com.finstream.person.domain.customer.organizationalCustomer.businessCustomer.BusinessCustomer;
import com.finstream.person.repository.organizational.OrganizationalCustomerRepository;

@NoRepositoryBean
public interface BusinessCustomerRepository<T extends BusinessCustomer>
        extends OrganizationalCustomerRepository<T> {

}