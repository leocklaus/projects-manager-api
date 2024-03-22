package io.github.leocklaus.projectsmanager.domain.model;

public enum Authorities {
    USER(1L, "ROLE_USER"),
    ADMIN(2L, "ROLE_ADMIN");

    private Long roleId;
    private String authority;


    Authorities(Long roleId, String authority) {
        this.roleId = roleId;
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public Long getRoleId(){
        return roleId;
    }
}
