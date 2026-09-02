package com.finstream.person.mapper;

import com.finstream.person.domain.customer.organizationalCustomer.FinancialInstitutionCustomer;
import com.finstream.person.dto.organizational.FinancialInstitutionCustomerDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FinancialInstitutionCustomerMapper {

    FinancialInstitutionCustomerDto toDto(
            FinancialInstitutionCustomer customer
    );

    FinancialInstitutionCustomer toEntity(
            FinancialInstitutionCustomerDto dto
    );
}