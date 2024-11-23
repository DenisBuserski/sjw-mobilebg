package com.mobilebg.model.mapper;

import com.mobilebg.model.dto.AddOfferDTO;
import com.mobilebg.model.entity.OfferEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    OfferEntity addOfferDtoToOfferEntity(AddOfferDTO addOfferDTO);
}
