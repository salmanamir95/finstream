package com.finstream.person.mapper;

import com.finstream.person.domain.customer.organizationalCustomer.GovernmentCustomer;
import com.finstream.person.dto.organizational.GovernmentCustomerDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GovernmentCustomerMapper {

    GovernmentCustomerDto toDto(GovernmentCustomer customer);

    GovernmentCustomer toEntity(GovernmentCustomerDto dto);
}