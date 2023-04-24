package example.statemachine.rfq.exception;

import example.statemachine.rfq.exception.RFQException;

public class BookingException extends RFQException  {

    private static final long serialVersionUID = -4582470401926451120L;

    public BookingException(String message) {
        super(message);
    }

    public BookingException(String message, Throwable e) {
        super(message, e);
    }
}