package example.statemachine.rfq.enums;

import example.statemachine.rfq.api.ProcessState;

/**
 * INIT       -  rfq               -> rfqProcessor()   -> quoteSent   -> QUOTE_SENT
 * QUOTE_SENT -  bookingRequest    -> bookingRequestProcessor() -> bookingError   -> QUOTE_SENT
 * QUOTE_SENT -  bookingRequest    -> bookingRequestProcessor() -> bookingSuccess -> BOOKING_COMPLETED
 */
public enum RFQState implements ProcessState {
    INIT,
    QUOTE_SENT,
    BOOKING_COMPLETED;
}