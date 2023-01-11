package org.cfc.redm.framework.security.userdetails;

import org.cfc.redm.framework.security.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public class CurrentUser extends User {

    private final Set<GrantedAuthority> authorities;

    private final boolean accountNonExpired;

    private final boolean accountNonLocked;

    private final boolean credentialsNonExpired;

    private final boolean enabled;

    public CurrentUser(Set<GrantedAuthority> authorities, boolean accountNonExpired,
                            boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }
}