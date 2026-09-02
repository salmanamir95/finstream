package com.finstream.person.mapper;

import com.finstream.person.domain.customer.organizationalCustomer.NonProfitCustomer;
import com.finstream.person.dto.organizational.NonProfitCustomerDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NonProfitCustomerMapper {

    NonProfitCustomerDto toDto(NonProfitCustomer customer);

    NonProfitCustomer toEntity(NonProfitCustomerDto dto);
}