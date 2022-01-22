package com.learning.springdatajpademo.mapper;

import com.learning.springdatajpademo.dto.AddressDto;
import com.learning.springdatajpademo.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface AddressMapper {

    @Mapping(target = "person", ignore = true)
    Address mapToEntity(AddressDto addressDto);

    AddressDto mapToDto(Address address);

    List<Address> mapToEntity(List<AddressDto> addressDtos);

    List<AddressDto> mapToDto(List<Address> addresses);
}
