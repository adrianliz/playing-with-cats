# Playing with cats

Kotlin API that allows you to create random questions about cat breeds

## Running the application

```bash
 ./gradlew bootRun --args='--THE_CAT_API_KEY=your_api_key'
```

## Endpoints

### Create a new question

```bash
curl -X POST "http://localhost:8080/questions" -H "Accept: application/json" -H "Content-Type: application/json"
```

It will return a JSON with the cat image and a list of possible answers

```json
{
  "id": "0261db91-87c7-468b-9af0-b806a1de1a67",
  "breeds": [
    {
      "id": "snow",
      "name": "Snowshoe"
    },
    {
      "id": "awir",
      "name": "American Wirehair"
    },
    {
      "id": "kora",
      "name": "Korat"
    }
  ],
  "cat": {
    "id": "Fcy0D9oI1",
    "imageUrl": "https://cdn2.thecatapi.com/images/Fcy0D9oI1.jpg"
  }
}
```

### Solve a question

```bash
curl -X POST "http://localhost:8080/questions/0261db91-87c7-468b-9af0-b806a1de1a67/solve" -H "Accept: application/json" -H "Content-Type: application/json" -d "{\"breedId\":\"snow\"}"
```

### Get a question by id

```bash
curl -X GET "http://localhost:8080/questions/0261db91-87c7-468b-9af0-b806a1de1a67" -H "Accept: application/json"
```

It will return a JSON with the question status

```json
{
  "id": "0261db91-87c7-468b-9af0-b806a1de1a67",
  "status": "CREATED"
}
```

If you FAILED or SOLVED the question, it will return the expected breed

```json
{
  "id": "0261db91-87c7-468b-9af0-b806a1de1a67",
  "expectedBreed": {
    "id": "snow",
    "name": "Snowshoe",
    "infoUrl": "https://en.wikipedia.org/wiki/Snowshoe_(cat)"
  },
  "status": "SOLVED"
}
```
