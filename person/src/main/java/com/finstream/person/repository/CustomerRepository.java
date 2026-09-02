package com.finstream.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.finstream.person.domain.customer.Customer;

@NoRepositoryBean
public interface CustomerRepository<T extends Customer>
        extends JpaRepository<T, Long> {
}