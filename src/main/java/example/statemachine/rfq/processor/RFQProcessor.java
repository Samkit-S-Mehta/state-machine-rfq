package example.statemachine.rfq.processor;

import example.statemachine.rfq.exception.ProcessException;
import org.springframework.stereotype.Service;

import example.statemachine.rfq.api.ProcessData;
import example.statemachine.rfq.api.Processor;
import example.statemachine.rfq.enums.RFQEvent;
import example.statemachine.rfq.impl.RFQData;

@Service
public class RFQProcessor implements Processor {
    @Override
    public ProcessData process(ProcessData data) throws ProcessException {
        // check the validity of quote and send the quote to the client
        ((RFQData)data).setEvent(RFQEvent.quoteSent);
        return data;
    }
}