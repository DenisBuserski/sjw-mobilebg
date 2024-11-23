package com.mobilebg.model.entity;

import com.mobilebg.model.enums.CategoryEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "models")
@Getter
@Setter
public class ModelEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @Column(nullable = false)
    private String imageUrl;

    private int startYear;

    private Long endYear;

    @ManyToOne
    private BrandEntity brand;

    @Override
    public String toString() {
        return "ModelEntity{" +
               "name='" + name + '\'' +
               ", category=" + category +
               ", imageUrl='" + imageUrl + '\'' +
               ", startYear=" + startYear +
               ", endYear=" + endYear +
               ", brand=" + (brand !=  null ? brand.getName() : null) +
               '}';
    }
}
