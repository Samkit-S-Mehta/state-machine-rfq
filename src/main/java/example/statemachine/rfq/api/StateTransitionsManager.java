package example.statemachine.rfq.api;

import example.statemachine.rfq.exception.ProcessException;

public interface StateTransitionsManager {
    public ProcessData processEvent(ProcessData data) throws ProcessException;
}