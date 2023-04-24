package example.statemachine.rfq.impl;

import example.statemachine.rfq.api.ProcessData;
import example.statemachine.rfq.api.ProcessEvent;

public class RFQData implements ProcessData {
	private double payment;
	private ProcessEvent event;
	private String quoteId;

	@Override
	public ProcessEvent getEvent() {
		return this.event;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public String getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	public void setEvent(ProcessEvent event) {
		this.event = event;
	}

}