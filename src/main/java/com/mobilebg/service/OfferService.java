package com.mobilebg.service;

import com.mobilebg.model.dto.AddOfferDTO;
import com.mobilebg.model.dto.BrandDTO;
import com.mobilebg.model.entity.ModelEntity;
import com.mobilebg.model.entity.OfferEntity;
import com.mobilebg.model.entity.UserEntity;
import com.mobilebg.model.mapper.OfferMapper;
import com.mobilebg.repository.ModelRepository;
import com.mobilebg.repository.OfferRepository;
import com.mobilebg.repository.UserRepository;
import com.mobilebg.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;
    private final CurrentUser currentUser;
    private final OfferMapper offerMapper;

    @Autowired
    public OfferService(OfferRepository offerRepository,
                        UserRepository userRepository,
                        ModelRepository modelRepository,
                        CurrentUser currentUser,
                        OfferMapper offerMapper) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.currentUser = currentUser;
        this.offerMapper = offerMapper;
    }

    public void addOffer(AddOfferDTO addOfferDTO) {
        OfferEntity newOffer = offerMapper.addOfferDtoToOfferEntity(addOfferDTO);

        UserEntity seller = this.userRepository.findByEmail(this.currentUser.getEmail()).orElseThrow();

        ModelEntity model = this.modelRepository.findById(addOfferDTO.getModelId()).orElseThrow();

        newOffer.setModel(model);
        newOffer.setSeller(seller);

        this.offerRepository.save(newOffer);

    }




}
