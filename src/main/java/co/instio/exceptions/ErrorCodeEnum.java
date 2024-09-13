package co.instio.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCodeEnum implements IErrorCode {



   LOST_FOUND_NOT_FOUND(HttpStatus.NOT_FOUND, 100100,"Lost and found not found"),

    ;

    private final HttpStatus httpStatus;

    private final int code;

    private final String message;

    ErrorCodeEnum(HttpStatus httpStatus, int code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}