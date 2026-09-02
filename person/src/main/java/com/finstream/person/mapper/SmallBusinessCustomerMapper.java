package com.finstream.person.mapper;

import org.mapstruct.Mapper;

import com.finstream.person.domain.customer.organizationalCustomer.businessCustomer.SmallBusinessCustomer;
import com.finstream.person.dto.organizational.business.SmallBusinessCustomerDto;

@Mapper(componentModel = "spring")
public interface SmallBusinessCustomerMapper {
    SmallBusinessCustomerDto toDto(SmallBusinessCustomer customer);

    SmallBusinessCustomer toEntity(SmallBusinessCustomerDto dto);
}