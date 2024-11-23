package com.mobilebg.model.dto;

import com.mobilebg.model.enums.EngineEnum;
import com.mobilebg.model.enums.TransmissionEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddOfferDTO {
    @NotNull
    @Min(1)
    private Long modelId;

    @NotNull
    private EngineEnum engine;

    @Positive
    @NotNull
    private Integer price;

    @Min(1900)
    @NotNull
    private Integer year;

    @NotEmpty
    private String description;

    @NotNull
    private TransmissionEnum transmission;

    @NotEmpty
    private String imageUrl;
}
