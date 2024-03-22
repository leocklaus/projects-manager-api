package io.github.leocklaus.projectsmanager.domain.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public Long getRoleId(){
        return roleId;
    }

    public Role(){

    }

    public Role(Authorities authorities){
        this.roleId = authorities.getRoleId();
        this.authority = authorities.getAuthority();
    }
}
