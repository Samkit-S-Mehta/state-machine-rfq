package example.statemachine.rfq.controller;

import example.statemachine.rfq.dto.RFQResponse;
import example.statemachine.rfq.dto.RfqRequest;
import example.statemachine.rfq.exception.ProcessException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import example.statemachine.rfq.enums.RFQEvent;
import example.statemachine.rfq.impl.RFQData;
import example.statemachine.rfq.exception.RFQException;
import example.statemachine.rfq.service.RFQStateTransitionsManager;

@RestController
@RequestMapping("/v1")
public class RFQController {

	@Autowired
	private RFQStateTransitionsManager rfqStateTransitionsManager;

	@PostMapping("/rfq")
	@ApiOperation(position = 1, value = "Request for Quote")
	public ResponseEntity<RFQResponse> handleRfq(@RequestBody RfqRequest rfqRequest) throws ProcessException {

		RFQData data = new RFQData();
		data.setEvent(RFQEvent.rfq);
		data = (RFQData) rfqStateTransitionsManager.processEvent(data);
		return ResponseEntity.ok(new RFQResponse(data.getQuoteId(), (RFQEvent) data.getEvent()));
	}

	@ApiOperation(position = 2, value = "Book the Quote by its Id")
	@GetMapping("/book")
	public ResponseEntity<String> handleBookingRequest(@RequestParam double payment, @RequestParam String quoteId) throws Exception {

		RFQData data = new RFQData();
		data.setPayment(payment);
		data.setQuoteId(quoteId);
		data.setEvent(RFQEvent.bookingRequest);
		data = (RFQData) rfqStateTransitionsManager.processEvent(data);

		return ResponseEntity.ok(((RFQEvent) data.getEvent()).name());
	}

	@ExceptionHandler(value = RFQException.class)
	public String handleRFQException(RFQException e) {
		return e.getMessage();
	}
}