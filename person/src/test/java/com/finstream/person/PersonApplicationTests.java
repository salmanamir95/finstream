package com.finstream.person;

import com.finstream.person.repository.IndividualCustomerRepository;
import com.finstream.person.repository.organizational.FinancialInstitutionCustomerRepository;
import com.finstream.person.repository.organizational.business.LargeBusinessCustomerRepository;
import com.finstream.person.repository.organizational.business.SmallBusinessCustomerRepository;
import com.finstream.person.repository.organizational.InstitutionalCustomerRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonApplicationTests {

	@Autowired
	private IndividualCustomerRepository individualCustomerRepository;

	@Autowired
	private FinancialInstitutionCustomerRepository financialInstitutionCustomerRepository;

	@Autowired
	private LargeBusinessCustomerRepository largeBusinessCustomerRepository;

	@Autowired
	private InstitutionalCustomerRepository institutionalCustomerRepository;

	@Autowired
	private SmallBusinessCustomerRepository smallBusinessCustomerRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void inheritedRepositoryFragmentsExecuteAcrossTheHierarchy() {
		individualCustomerRepository.findByEmail("missing@example.com");
		financialInstitutionCustomerRepository.findByEmail("missing@example.com");
		financialInstitutionCustomerRepository.findByRegistrationNumber("missing");
		largeBusinessCustomerRepository.findByEmail("missing@example.com");
		largeBusinessCustomerRepository.findByRegistrationNumber("missing");
		largeBusinessCustomerRepository.findByTaxIdentificationNumber("missing");
		institutionalCustomerRepository.findByStudentOrMemberCountGreaterThan(0);
		smallBusinessCustomerRepository.findByPrimaryProductOrServiceContainingIgnoreCase("missing");
	}

}
