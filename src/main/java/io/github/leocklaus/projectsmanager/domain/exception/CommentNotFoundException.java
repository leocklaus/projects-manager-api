package io.github.leocklaus.projectsmanager.domain.exception;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException(){
        super("Comment not Found");
    }
}
