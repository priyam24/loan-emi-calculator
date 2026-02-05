# Author: Priyam Roy

Overview: This is a backend service using Java 17 and Spring Boot 3 for calculating loan emi. 
The emi calculation logic is as follows -
EMI = P x R x (1+R)^N / [(1+R)^N-1]
where, “P” is the loan amount, “N” is tenure in months, and “R” is the monthly interest
rate.

-----------------------------------

Steps to run this project:

1. Install java 17
2. Install an IDE like STS/eclipse/Intellij
3. Install maven if not included in the IDE  plugins
4. Import this project from github
5. Perform the build at project root folder by running "mvn clean Install" command
6. Run the project by running "java -jar loan-emi-calculator-0.0.1-SNAPSHOT.jar" command
7. Hit the endpoints using a client like Postman.

-----------------------------------

>>>cURL request:

curl --location 'http://localhost:8080/api/loan/emi' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--data '{
    "loanAmount": 100000,
    "annualInterestRate": "12",
    "loanTermInMonths": 30
  }'

>>>Response:
{
    "emiAmount": 3875.0
}