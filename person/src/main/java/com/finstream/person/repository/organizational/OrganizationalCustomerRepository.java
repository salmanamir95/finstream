package com.finstream.person.repository.organizational;

import org.springframework.data.repository.NoRepositoryBean;

import com.finstream.person.domain.customer.organizationalCustomer.OrganizationalCustomer;
import com.finstream.person.repository.CustomerRepository;

@NoRepositoryBean
public interface OrganizationalCustomerRepository
        extends CustomerRepository {

}