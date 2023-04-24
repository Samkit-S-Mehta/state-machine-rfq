package example.statemachine.rfq.api.abstracts;

import example.statemachine.rfq.api.ProcessData;
import example.statemachine.rfq.api.StateTransitionsManager;
import example.statemachine.rfq.exception.ProcessException;

public abstract class AbstractStateTransitionsManager implements StateTransitionsManager {
    
	protected abstract ProcessData initializeState(ProcessData data) throws ProcessException;
    protected abstract ProcessData processStateTransition(ProcessData data) throws ProcessException;

    @Override
    public ProcessData processEvent(ProcessData data) throws ProcessException {
    	initializeState(data);
        return processStateTransition(data);
    }
}