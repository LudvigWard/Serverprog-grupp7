# Serverprogrammering Grupp 7
En server som fungerar som en backend för en onlineaffär för musikinstrument.
Bygg projektet med ```mvn package``` och kör sedan ```java -jar target/Serverprog-grupp7-0.0.1-SNAPSHOT.jar``` för att starta servern.

## Rest Web Services
Till servern finns ett api med ett antal endpoints:
### POST
Man kan göra en POST request till tre endpoints:
#### http://localhost:8080/addProducts
exempel (JSON):
```javascript
[
  {
    "productName": "namnPåInstrument",
    "price": 3000
  },
  {
    "productName": "namnPåInstrument2",
    "price": 3002
  }
]
```
#### http://localhost:8080/addCustomers
customerId är kundens personnummer.
exempel (JSON):
```javascript
[
  {
    "customerId": 12345678,
    "customerName": "exempel exempelsson",
    "email": "exempel@exempel.com",
    "mobileNum": "098776543"
  },
  {
    (fler kunder här)
  }
]
```
#### http://localhost:8080/addCompleteOrder
Man kan inte lägga flera beställningar samtidigt, men man kan beställa flera produkter i en beställning.
exempel (JSON):
```javascript
{
    "customerId": 12345678,
    "productNames": [
      "namnPåInstrument",
      "namnPåInstrument2"]
}
```

### GET
Man kan få info om alla tabeller med dessa Url-er i ett GET-anrop:
#### http://localhost:8080/customers
#### http://localhost:8080/customerorders
#### http://localhost:8080/orderProducts
#### http://localhost:8080/products

### Tabell UI
Man kan även kika på tabellerna i ett UI online via dessa länkar:
#### http://localhost:8080/website/store/customers.html
#### http://localhost:8080/website/store/customerOrders.html
#### http://localhost:8080/website/store/orderProducts.html
#### http://localhost:8080/website/store/products.html
