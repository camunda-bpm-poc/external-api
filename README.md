# external-api

curl --location --request POST 'http://localhost:8081/payment-process/post-payment' \
--header 'message: Muthu' \
--header 'Content-Type: application/json' \
--data-raw '{
    "ssn": "083-93-3828",
    "accountNumber": "123498765",
    "paymentAmount": "100",
    "paymentDate": "12/28/2020"
}'
