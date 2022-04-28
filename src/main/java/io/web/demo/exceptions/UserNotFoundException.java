package io.web.demo.exceptions;


public class UserNotFoundException extends BaseException{
    public static final String errorCode = "USER_NOT_FOUND";
    private Long userId;
    public UserNotFoundException(Long userId) {
        super(errorCode,"User "+userId+" not found in system");
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
