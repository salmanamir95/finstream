package com.finstream.person.mapper;

import com.finstream.person.domain.customer.organizationalCustomer.InstitutionalCustomer;
import com.finstream.person.dto.organizational.InstitutionalCustomerDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InstitutionalCustomerMapper {

    InstitutionalCustomerDto toDto(InstitutionalCustomer customer);

    InstitutionalCustomer toEntity(InstitutionalCustomerDto dto);
}