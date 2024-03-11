package io.github.leocklaus.projectsmanager.domain.exception;

public class UserNotMatchingPasswordsException extends UserException{
    public UserNotMatchingPasswordsException(String msg) {
        super(msg);
    }

    public UserNotMatchingPasswordsException(){
        super("Current and New Password do not match");
    }
}
