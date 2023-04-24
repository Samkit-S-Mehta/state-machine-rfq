package example.statemachine.rfq.springbootstatemachine;

import example.statemachine.rfq.dto.RFQResponse;
import example.statemachine.rfq.dto.RfqRequest;
import example.statemachine.rfq.enums.RFQEvent;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class RFQControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testHandleRfq() throws Exception {
        // Given
        RfqRequest rfqRequest = new RfqRequest("EUR", "USD", 10000);
        HttpEntity<RfqRequest> request = new HttpEntity<>(rfqRequest);

        // When
        ResponseEntity<RFQResponse> response = restTemplate.postForEntity("/v1/rfq", request, RFQResponse.class);

        // Then
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(response.getBody().getQuoteId());

        // Given
        String quoteId = response.getBody().getQuoteId();
        double payment = 1000.0;

        // When
        ResponseEntity<String> bookingresponse = restTemplate.getForEntity("/v1/book?payment=" + payment + "&quoteId=" + quoteId, String.class);

        // Then
        assertThat(bookingresponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(bookingresponse.getBody()).contains(RFQEvent.bookingSuccess.name());
    }
}