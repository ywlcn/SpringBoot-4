package com.example.grpc.security.internal;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Collections;

public class SampleUser extends User {

    @Setter
    @Getter
    private String normalPassword = "";

    public SampleUser(String username, String normalPassword, @Nullable String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, true, true, true, true, authorities);
        this.normalPassword = normalPassword;
    }

    public SampleUser(String username, String normalPassword, @Nullable String password, boolean enabled, boolean accountNonExpired,
                      boolean credentialsNonExpired, boolean accountNonLocked,
                      Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.normalPassword = normalPassword;
    }


}
