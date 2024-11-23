package com.mobilebg.model.entity;

import com.mobilebg.model.enums.UserRoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum userRoleEnum;

    @Override
    public String toString() {
        return "UserRoleEntity{" +
               "id=" + id +
               ", userRoleEnum=" + userRoleEnum +
               '}';
    }
}
