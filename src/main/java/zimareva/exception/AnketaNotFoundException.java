package zimareva.exception;

import java.text.MessageFormat;

//todo: почему runtime exception?
public class AnketaNotFoundException extends RuntimeException {
    public AnketaNotFoundException(Long id){
        super(MessageFormat.format("Could not find anketa with id: {0} ", id));
    }
}
