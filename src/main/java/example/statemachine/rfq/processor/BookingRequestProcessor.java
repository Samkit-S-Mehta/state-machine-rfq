package example.statemachine.rfq.processor;

import example.statemachine.rfq.exception.BookingException;
import example.statemachine.rfq.exception.ProcessException;
import org.springframework.stereotype.Service;

import example.statemachine.rfq.api.ProcessData;
import example.statemachine.rfq.api.Processor;
import example.statemachine.rfq.enums.RFQEvent;
import example.statemachine.rfq.impl.RFQData;

@Service
public class BookingRequestProcessor implements Processor {
    @Override
    public ProcessData process(ProcessData data) throws ProcessException {
        if(((RFQData)data).getPayment() < 1.00) {
        	((RFQData)data).setEvent(RFQEvent.bookingError);
            throw new BookingException(RFQEvent.bookingError.name());
        } else {
            ((RFQData)data).setEvent(RFQEvent.bookingSuccess);
        }
        return data;
    }
}