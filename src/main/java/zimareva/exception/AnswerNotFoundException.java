package zimareva.exception;

import java.text.MessageFormat;

public class AnswerNotFoundException extends RuntimeException {
    public AnswerNotFoundException(Long id){
        super(MessageFormat.format("Could not find answer with id: {0} ", id));
    }
}
