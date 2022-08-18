package ch.noseryoung.sbdemo01.domain.exceptions;


public class NotFoundException extends RuntimeException {
    public NotFoundException(String stuff) {
        super(stuff + " not found.");
    }
}