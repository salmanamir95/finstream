package com.finstream.person.mapper;

import org.mapstruct.Mapper;

import com.finstream.person.domain.customer.organizationalCustomer.businessCustomer.MediumBusinessCustomer;
import com.finstream.person.dto.organizational.business.MediumBusinessCustomerDto;

@Mapper(componentModel = "spring")
public interface MediumBusinessCustomerMapper {
    MediumBusinessCustomerDto toDto(MediumBusinessCustomer customer);

    MediumBusinessCustomer toEntity(MediumBusinessCustomerDto dto);
}