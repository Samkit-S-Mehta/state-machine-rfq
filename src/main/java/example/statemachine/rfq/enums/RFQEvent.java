package example.statemachine.rfq.enums;

import example.statemachine.rfq.api.ProcessEvent;
import example.statemachine.rfq.api.ProcessState;
import example.statemachine.rfq.api.Processor;
import example.statemachine.rfq.processor.RFQProcessor;
import example.statemachine.rfq.processor.BookingRequestProcessor;

/**
 * INIT       -  rfq               -> rfqProcessor()   -> quoteSent   -> QUOTE_SENT
 * QUOTE_SENT -  bookingRequest    -> bookingRequestProcessor() -> bookingError   -> QUOTE_SENT
 * QUOTE_SENT -  bookingRequest    -> bookingRequestProcessor() -> bookingSuccess -> BOOKING_COMPLETED
 */
public enum RFQEvent implements ProcessEvent {

    rfq {
        @Override
        public Class<? extends Processor> nextStepProcessor(ProcessEvent event) {
                return RFQProcessor.class;
        }
        
        /**
         * This api has no effect on state so return current state
         */
        @Override
        public ProcessState nextState(ProcessEvent event) {
                return RFQState.INIT;
        }

    },
    quoteSent {
    	/**
    	 * This api does not trigger any process
    	 * So return null 
    	 */
        @Override
        public Class<? extends Processor> nextStepProcessor(ProcessEvent event) {
            return null;
        }
        
        @Override
        public ProcessState nextState(ProcessEvent event) {
                return RFQState.QUOTE_SENT;
        }

    },
    bookingRequest {
        @Override
        public Class<? extends Processor> nextStepProcessor(ProcessEvent event) {
                return BookingRequestProcessor.class;
        }
        
        /**
         * This api has no effect on state so return current state
         */
        @Override
        public ProcessState nextState(ProcessEvent event) {
                return RFQState.QUOTE_SENT;
        }
    },
    bookingSuccess {
    	/**
    	 * This api does not trigger any process
    	 * So return null 
    	 */
        @Override
        public Class<? extends Processor> nextStepProcessor(ProcessEvent event) {
            return null;
        }
        @Override
        public ProcessState nextState(ProcessEvent event) {
                return RFQState.BOOKING_COMPLETED;
        }
    },
    bookingError {
    	/**
    	 * This api does not trigger any process
    	 * So return null 
    	 */
        @Override
        public Class<? extends Processor> nextStepProcessor(ProcessEvent event) {
            return null;
        }
        
        @Override
        public ProcessState nextState(ProcessEvent event) {
                return RFQState.QUOTE_SENT;
        }
    }
}