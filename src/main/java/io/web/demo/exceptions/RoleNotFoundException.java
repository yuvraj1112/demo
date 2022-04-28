package io.web.demo.exceptions;

public class RoleNotFoundException extends BaseException {
    Long roleId;
    public static String errorCode = "ROLE_NOT_FOUND";

    public RoleNotFoundException(Long roleId) {
        super(errorCode,"Role "+roleId+" not found in system");
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return roleId;
    }
}
