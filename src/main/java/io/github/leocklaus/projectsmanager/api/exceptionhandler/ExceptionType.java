package io.github.leocklaus.projectsmanager.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ExceptionType {

    USER_NOT_FOUND("Usuário não encontrado", "/user-not-found"),
    USER_EXCEPTION("Erro ao buscar usuário", "/user-exception"),
    USER_NOT_AUTHORIZED("Usuário não autorizado", "/user-not-authorized"),
    USER_NOT_MATCHING_PASSWORD("As senhas não coincidem", "/password-not-matching"),
    TASK_EXCEPTION("Erro ao buscar tarefa", "/task-exception"),
    TASK_NOT_FOUND("Tarefa não encontrada", "/task-not-found"),
    PROJECT_EXCEPTION("Erro ao buscar project", "/project-exception"),
    PROJECT_NOT_FOUND("Projeto não encontrado", "/project-not-found"),
    MEMBER_NOT_FOUND("Membro não encontrado", "/member-not-found"),
    NOTIFICATION_NOT_FOUND("Notificação não encontrada", "/notification-not-found"),
    COMMENT_NOT_FOUND("Comentário não encontrado", "/comment-not-found"),
    INVALID_DATA("Dados inválidos", "/invalid-data"),
    RESOURCE_NOT_FOUND("Recurso não encontrado", "/resource-not-found");

    private final String title;
    private final String URI;

    ExceptionType(String title, String URI){
        this.title = title;
        this.URI = URI;
    }
}
