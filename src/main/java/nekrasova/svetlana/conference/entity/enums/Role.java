package nekrasova.svetlana.conference.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    SPEAKER,
    LISTENER;

    @Override
    public String getAuthority() {
        return name();
    }
}
