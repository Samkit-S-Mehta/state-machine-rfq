package example.statemachine.rfq.exception;

public class RFQException extends ProcessException {

    private static final long serialVersionUID = 5587859227419203629L;
    
    public RFQException(String message) {
        super(message);
    }

    public RFQException(String message, Throwable e) {
        super(message, e);
    }
}
