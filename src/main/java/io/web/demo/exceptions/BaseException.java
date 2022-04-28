package io.web.demo.exceptions;


public class BaseException extends Exception
{
    String errorCode;
    String description;

    String errorFormat = "CODE: %s \\n Description: %s";
    public BaseException(String errorCode,String description){
        this.errorCode = errorCode;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format(errorFormat,errorCode,description);
    }
}
