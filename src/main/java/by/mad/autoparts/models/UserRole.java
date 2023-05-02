package by.mad.autoparts.models;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ADMIN,
    GUEST;

    @Override
    public String getAuthority() {
        return name();
    }
}
