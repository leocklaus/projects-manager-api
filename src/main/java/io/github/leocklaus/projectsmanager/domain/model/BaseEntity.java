package io.github.leocklaus.projectsmanager.domain.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity extends Auditable implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @PrePersist
    private void generateUUIDWhenPersists(){
        if(this.id == null){
            this.id = UUID.randomUUID();
        }
    }

}
