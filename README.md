# Conversions

Conversions is a CRUD REST API that Persist data from Purchase Transactions and uses Fiscal Data API to convert currency from dollar to other currencies.
Example of use: https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/rates_of_exchange?fields=country_currency_desc,exchange_rate,record_date

This project contains a REST API created in Java with Spring Boot WebFlux + OpenAPI (Swagger v3) 
Uses LOMBOK 
Database in DEV H2 Memory
PROD PlanetScale MYSQL (DBaaS Provider)

Swagger on the API: /swagger-ui.html

Endpoints CRUD Purchase Transaction are:
</br>Return all the Persisted Purchase Transactions:<b>GET /api/v1/transaction/ </b>
</br>Return that Specific Purchase Transactions: <b>GET /api/v1/transaction/{id} </b>
</br>Create a new Purchase Transactions: <b>POST /api/v1/transaction/{id} </b>
</br>Update that Specific Purchase Transactions: <b>PATCH /api/v1/transaction/{id} </b>
</br>Delete that Specific Purchase Transactions: <b>DELETE /api/v1/transaction/{id} </b>


Endpoint to Convert that Purchase Transaction to all available currencies on <b> Fiscal Data API </b> at a valid time frame of Six months is: 
</br><b>GET /api/v1/conversion{id} </b>

Endpoint to Convert that Purchase Transaction to all available currencies on <b> Fiscal Data API </b> at a valid time frame of Six months is:
</br><b>GET /api/v1/conversion-functional/{id} </b>


