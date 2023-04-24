package example.statemachine.rfq.service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import example.statemachine.rfq.exception.ProcessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import example.statemachine.rfq.api.ProcessData;
import example.statemachine.rfq.api.abstracts.AbstractStateTransitionsManager;
import example.statemachine.rfq.enums.RFQEvent;
import example.statemachine.rfq.enums.RFQState;
import example.statemachine.rfq.impl.RFQData;
import example.statemachine.rfq.exception.RFQException;

/**
 * This class manages various state transitions based on the api The
 * superclass AbstractStateTransitionsManager calls the two methods
 * initializeState and processStateTransition in that order
 */
@Service
public class RFQStateTransitionsManager extends AbstractStateTransitionsManager {

	private static final Logger log = LoggerFactory.getLogger(RFQStateTransitionsManager.class);

	@Autowired
	private ApplicationContext context;

	@Autowired
	private RFQDbService dbService;

	@Override
	protected ProcessData processStateTransition(ProcessData processData) throws ProcessException {

		RFQData data = (RFQData) processData;

		try {
			log.info("Pre-api: " + data.getEvent().toString());
			data = (RFQData) this.context.getBean(data.getEvent().nextStepProcessor(data.getEvent())).process(data);
			log.info("Post-api: " + data.getEvent().toString());
			dbService.getStates().put(data.getQuoteId(), (RFQState) data.getEvent().nextState(data.getEvent()));
			log.info("Final state: " + dbService.getStates().get(data.getQuoteId()).name());
			log.info("=======================");

		} catch (RFQException e) {
			log.info("Post-api: " + ((RFQEvent) data.getEvent()).name());
			dbService.getStates().put(data.getQuoteId(), (RFQState) data.getEvent().nextState(data.getEvent()));
			log.info("Final state: " + dbService.getStates().get(data.getQuoteId()).name());
			log.info("=======================");
			throw new RFQException(((RFQEvent) data.getEvent()).name(), e);
		}
		return data;
	}

	private RFQData checkStateForReturningCustomers(RFQData data) throws RFQException {
		// returning customers must have a state
		if (data.getQuoteId() != null) {
			if (this.dbService.getStates().get(data.getQuoteId()) == null) {
				throw new RFQException("No state exists for quoteId=" + data.getQuoteId());
			} else if (this.dbService.getStates().get(data.getQuoteId()) == RFQState.BOOKING_COMPLETED) {
				throw new RFQException("Booking is already completed for quoteId=" + data.getQuoteId());
			} else {
				log.info("Initial state: " + dbService.getStates().get(data.getQuoteId()).name());
			}
		}
		return data;
	}

	@Override
	protected ProcessData initializeState(ProcessData processData) throws RFQException {

		RFQData data = (RFQData) processData;

		if (data.getQuoteId() != null) {
			return checkStateForReturningCustomers(data);
		}

		String quoteId = UUID.randomUUID().toString();
		data.setQuoteId(quoteId);
		dbService.getStates().put(quoteId, RFQState.INIT);

		log.info("Initial state: " + dbService.getStates().get(data.getQuoteId()).name());
		return data;
	}

	public ConcurrentHashMap<String, RFQState> getStates() {
		return dbService.getStates();
	}
}