package spring.inventories.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFounException extends RuntimeException {
    public NotFounException(String message){
        super(message);
    }
}
