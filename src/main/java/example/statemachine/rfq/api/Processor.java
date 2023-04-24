package example.statemachine.rfq.api;

import example.statemachine.rfq.exception.ProcessException;

public interface Processor {
    public ProcessData process(ProcessData data) throws ProcessException;
}