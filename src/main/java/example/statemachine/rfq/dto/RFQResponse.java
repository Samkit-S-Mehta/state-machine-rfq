package example.statemachine.rfq.dto;

import example.statemachine.rfq.enums.RFQEvent;

public class RFQResponse {
    String quoteId;
    RFQEvent event;

    public RFQResponse(String quoteId, RFQEvent event) {
        this.quoteId = quoteId;
        this.event = event;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public RFQEvent getEvent() {
        return event;
    }

    public void setEvent(RFQEvent event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "RFQResponse{" +
                "quoteId='" + quoteId + '\'' +
                ", event=" + event +
                '}';
    }
}