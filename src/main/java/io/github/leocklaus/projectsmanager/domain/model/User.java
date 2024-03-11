package io.github.leocklaus.projectsmanager.domain.model;

import io.github.leocklaus.projectsmanager.api.dto.UserInputDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity{


    @NotNull
    @Column(nullable = false)
    private String name;
    @NotNull
    @Column(nullable = false, unique = true)
    private String email;
    @NotNull
    @Column(nullable = false, unique = true)
    private String phone;
    @NotNull
    @Column(nullable = false)
    private String password;

    public User(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public User(UserInputDTO dto){
        this.name = dto.name();
        this.email = dto.email();
        this.phone = dto.phone();
    }

}
