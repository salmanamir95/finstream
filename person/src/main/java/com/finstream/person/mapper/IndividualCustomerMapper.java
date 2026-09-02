package com.finstream.person.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.finstream.person.domain.customer.IndividualCustomer;
import com.finstream.person.dto.IndividualCustomerDto;

@Mapper(componentModel = "spring")
public interface IndividualCustomerMapper {

    IndividualCustomerDto toDto(IndividualCustomer customer);

    IndividualCustomer toEntity(IndividualCustomerDto dto);

    void updateEntity(
            IndividualCustomerDto dto,
            @MappingTarget IndividualCustomer customer
    );
}