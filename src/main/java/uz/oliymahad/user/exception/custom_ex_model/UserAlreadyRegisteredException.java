package uz.oliymahad.user.exception.custom_ex_model;

public class UserAlreadyRegisteredException extends Exception{
    public UserAlreadyRegisteredException(String message) {
        super(message);
    }
}

