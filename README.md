# state-machine-rfq

Simple RFQ State Machine

Used IDE : Intelij Idea

Steps to Clone The Repository Application:
Download this Project from Git.
git clone https://github.com/Samkit-S-Mehta/state-machine-rfq.git

Steps to test the application:
To Run this application:
execute command mvn spring-boot:run

swagger url : http://localhost:8080/swagger-ui.html#/

Two End Points  :

POST : /v1/rfq - to request a quote - http://localhost:8080/v1/rfq

command to be executed : curl -X POST "http://localhost:8080/v1/rfq" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"buyCurrency\": \"EUR\", \"buyCurrencyQuantity\": 10000, \"sellCurrency\": \"USD\"}"

![Screenshot 2023-04-24 104029](https://user-images.githubusercontent.com/57173830/233905168-a65eee87-8861-4cac-99c4-877e985d166c.png)

![Screenshot 2023-04-24 104151](https://user-images.githubusercontent.com/57173830/233905314-19c70439-624e-4679-9532-b68317396c41.png)

GET : /v1/book - to book the prevously requested quote by id and payment - http://localhost:8080/v1/book

command to be executed : curl -X GET "http://localhost:8080/v1/book?payment=1000&quoteId=d627a03f-d52c-44e3-ab64-e928212ba5f4" -H "accept: */*"

![Screenshot 2023-04-24 104429](https://user-images.githubusercontent.com/57173830/233905683-5371be11-deeb-48fa-aabf-0787da8b6c17.png)

![Screenshot 2023-04-24 104512](https://user-images.githubusercontent.com/57173830/233905769-41a45faf-d5f4-4f86-b05f-a245ad36fc32.png)

Tech Stack Used : 
Spring Boot Application , Java, Rest Controller, Swagger.

Description: 

we can requesta quote and in the sub sequent request we can book the prviously requested quote. and 

State Machine working is based on the events,

any request trigger the events and each events are associated with the Processor and the next state in that respective state machine and in this flow ProcessData object is used as input and output revolving around the flow. 

 * INIT       -  rfq               -> rfqProcessor()   -> quoteSent   -> QUOTE_SENT
 * QUOTE_SENT -  bookingRequest    -> bookingRequestProcessor() -> bookingError   -> QUOTE_SENT
 * QUOTE_SENT -  bookingRequest    -> bookingRequestProcessor() -> bookingSuccess -> BOOKING_COMPLETED

we just need to crate the processor, hookin to the next event to scale and can add more states and events in this structure.

Limitations:

not used any in memory db instead used the in memory map just to store the data. kept it with minimum states and events with focus on strucutre and design pattern to make it minimal with spring boot app to quickly install, swagger ui to quickly test and rest controller and integartion test structure.
and to focus on states and events logic.

Directory Structure:

![Screenshot 2023-04-24 110054](https://user-images.githubusercontent.com/57173830/233907775-63780a70-abb3-4ac9-b1f5-fbe5ea19ddb2.png)

![Screenshot 2023-04-24 110225](https://user-images.githubusercontent.com/57173830/233907935-fa5028c7-69cc-4222-92e8-21e618f65545.png)
