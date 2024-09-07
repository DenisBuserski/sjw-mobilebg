package com.mobilebg.model.entity;

import com.mobilebg.model.entity.enums.EngineEnum;
import com.mobilebg.model.entity.enums.TransmissionEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "offers")
@Getter
@Setter
public class OfferEntity extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EngineEnum engine;

    private String imageUrl;

    private int mileage;

    @Column(nullable = false)
    private BigDecimal price;

    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransmissionEnum transmission;

    private int year;

    @ManyToOne
    private ModelEntity model;

    @ManyToOne
    private UserEntity seller;

    @Override
    public String toString() {
        return "OfferEntity{" +
               "engine=" + engine +
               ", imageUrl='" + imageUrl + '\'' +
               ", mileage=" + mileage +
               ", price=" + price +
               ", description='" + description + '\'' +
               ", transmission=" + transmission +
               ", year=" + year +
               ", model=" + model +
               ", seller=" + seller +
               '}';
    }
}
