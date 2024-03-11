package io.github.leocklaus.projectsmanager.domain.exception;

public class UserNotAuthorizedException extends UserException{
    public UserNotAuthorizedException() {
        super("Usuário não autorizado para esta ação");
    }

    public UserNotAuthorizedException(String msg) {
        super(msg);
    }
}
