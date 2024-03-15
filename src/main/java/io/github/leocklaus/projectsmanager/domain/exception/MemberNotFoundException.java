package io.github.leocklaus.projectsmanager.domain.exception;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException(String msg){
        super(msg);
    }

    public MemberNotFoundException(){
        this("Membro não encontrado");
    }
}
