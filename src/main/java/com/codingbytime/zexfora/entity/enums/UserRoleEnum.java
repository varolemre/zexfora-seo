package com.codingbytime.zexfora.entity.enums;

public enum UserRoleEnum {
    ROLE_ADMIN("ROLE_ADMIN"), ROLE_USER("ROLE_USER");
    private final String value;

    UserRoleEnum(String role) {
        this.value = role;
    }
}
