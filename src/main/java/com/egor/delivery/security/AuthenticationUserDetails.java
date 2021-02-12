package com.egor.delivery.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

@AllArgsConstructor
public class AuthenticationUserDetails implements UserDetails {
    @Getter
    private Long id;

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Returns the username used to authenticate the User. Cannot return <code>null</code>.
     *
     * @return - String
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password used to authenticate the User
     *
     * @return - String
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the authorities granted to the User
     *
     * @return - the authorities, sorted by key
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be authenticated.
     *
     * @return - <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthenticationUserDetails)) return false;
        AuthenticationUserDetails that = (AuthenticationUserDetails) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(username, that.username) &&
                Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), username, getPassword());
    }
}
