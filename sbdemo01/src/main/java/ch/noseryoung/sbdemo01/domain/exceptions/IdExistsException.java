package ch.noseryoung.sbdemo01.domain.exceptions;

public class IdExistsException extends RuntimeException{
    public IdExistsException() {
        super("Id exists already, please choose another.");
    }

}
