package com.finstream.person.mapper;

import org.mapstruct.Mapper;

import com.finstream.person.domain.customer.organizationalCustomer.businessCustomer.LargeBusinessCustomer;
import com.finstream.person.dto.organizational.business.LargeBusinessCustomerDto;

@Mapper(componentModel = "spring")
public interface LargeBusinessCustomerMapper {
    LargeBusinessCustomerDto toDto(LargeBusinessCustomer customer);

    LargeBusinessCustomer toEntity(LargeBusinessCustomerDto dto);
}