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
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "users")
public class User {

    @Id
    private UUID id;
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

    public User(UserInputDTO dto){
        this.name = dto.name();
        this.email = dto.email();
        this.phone = dto.phone();
    }

    @PrePersist
    private void generateUUIDWhenPersists(){
        if(this.id == null){
            this.id = UUID.randomUUID();
        }
    }
}
