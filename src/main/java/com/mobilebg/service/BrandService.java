package com.mobilebg.service;

import com.mobilebg.model.dto.BrandDTO;
import com.mobilebg.model.dto.ModelDTO;
import com.mobilebg.model.entity.BrandEntity;
import com.mobilebg.model.entity.ModelEntity;
import com.mobilebg.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {
    private BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<BrandDTO> getAllBrands() {
        return this.brandRepository.findAll()
                .stream()
                .map(this::mapBrand)
                .collect(Collectors.toList());
    }

    private BrandDTO mapBrand(BrandEntity brandEntity) {
        List<ModelDTO> models = brandEntity.getModels()
                .stream()
                .map(this::mapModel)
                .toList();

        return BrandDTO.builder()
                .name(brandEntity.getName())
                .models(models)
                .build();
    }

    private ModelDTO mapModel(ModelEntity modelEntity) {
        return ModelDTO.builder()
                .id(modelEntity.getId())
                .name(modelEntity.getName())
                .build();
    }
}
