package example.statemachine.rfq.service;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import example.statemachine.rfq.enums.RFQState;

@Service
public class RFQDbService {
    
    private final ConcurrentHashMap<String, RFQState> states;
    
    public RFQDbService() {
        this.states = new ConcurrentHashMap<String, RFQState>();
    }

	public ConcurrentHashMap<String, RFQState> getStates() {
        return states;
    }
}
