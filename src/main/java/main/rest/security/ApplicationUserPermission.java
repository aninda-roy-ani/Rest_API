package main.rest.security;

public enum ApplicationUserPermission {
    USERS_READ("user:read"),
    USERS_WRITE("user:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
