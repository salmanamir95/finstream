package com.finstream.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.finstream.account.domain.Account;

@NoRepositoryBean
public interface AccountRepository<T extends Account>
        extends JpaRepository<T, Long> {

}