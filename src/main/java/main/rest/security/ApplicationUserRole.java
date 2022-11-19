package main.rest.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static main.rest.security.ApplicationUserPermission.USERS_READ;
import static main.rest.security.ApplicationUserPermission.USERS_WRITE;

public enum ApplicationUserRole {
    GOOD_MAN(Sets.newHashSet(
        USERS_WRITE, USERS_READ
    )),
    BAD_MAN(Sets.newHashSet(
        USERS_READ
    ));


    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(HashSet<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(applicationUserPermission ->
                        new SimpleGrantedAuthority(applicationUserPermission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }
}
