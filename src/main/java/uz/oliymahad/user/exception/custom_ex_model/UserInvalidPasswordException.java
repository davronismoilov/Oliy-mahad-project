package uz.oliymahad.user.exception.custom_ex_model;

public class UserInvalidPasswordException extends UserAuthenticationException {
    public UserInvalidPasswordException(String message) {
        super(message);
    }
}
