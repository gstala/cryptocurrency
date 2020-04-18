# cryptocurrency

Sample Spring Boot application integrated with [CoinApi](https://www.coinapi.io)

## Requirements
* Java 8 or Docker installed
* CoinAPI key (you can generate it here https://www.coinapi.io/Pricing)

## Quick start
```bash
./mvnw clean package
java -jar -Dio.coinapi.rest.api-key=YOUR_API_KEY ./application/target/cryptocurrency-0.0.1-SNAPSHOT.jar
```
or

```bash
docker build -t app --build-arg apiKey=YOUR_API_KEY .
docker run --rm -p 8080:8080 app
```
Then open in browser <http://localhost:8080/swagger-ui.html>

## Endpoints

### Get cryptocurrency rates
```http
GET /currencies/{baseCurrencyCode*}?filter={quoteCurrencyCode*}
```
*baseCurrencyCode,quoteCurrencyCode can be like "BTC", "USD" (see list of [available values](https://docs.coinapi.io/#list-all-assets))

### Get cryptocurrency exchange forecast
```http
POST /currencies/exchange
{
  "amount": 1,
  "from": "BTC",
  "to": [
    "USD", "ETH"
  ]
}
```
