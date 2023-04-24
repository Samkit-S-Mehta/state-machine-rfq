# state-machine-rfq

Simple RFQ State Machine

Used IDE : Intelij Idea

Tech Stack Used : 
Spring Boot Application , Java, Rest Controller, Swagger and State Machine

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
