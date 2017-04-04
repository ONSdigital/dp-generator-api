package dp.generatorapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Failed to convert json from datastore")
public class JsonToObjectException extends RuntimeException {

    public JsonToObjectException(Exception e) {
        super(e);
    }
}
