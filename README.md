# Financas-api
API-REST with Spring Boot -System for registration of financial transactions.


## 1. Running

1. Import Maven Projects (IDE Eclipse ou STS);

2. Maven Build: clean install

3. Run as SpringBoot Application

4. Access:
* http://localhost:8080/{endpoint}
* http://localhost:8080//swagger-ui.html#/

## 2. URLs Heroku
* https://financas-api-marcus.herokuapp.com/{endpoint}
* https://financas-api-marcus.herokuapp.com/swagger-ui.html#/

## 3. Endpoints

**Basic Authentication:**
* **user:** admin@financas.com
* **pass:** admin

### a) Category
**1. GET /category** - List with all categories.

**2. GET /category/{id}** - Detail the category with id equal to {id}

**3. DELETE /category/{id}** - Delete the category with id equal to {id}. Only categories unused can be deleted.

**4. POST /category** - Create a category.
*RequestBody:*
```
{
  "name": "string"
}
```

### b) Person
**1. GET /person** - List with all people.

**2. GET /person/{id}** - Detail the person with id equal to {id}

**3. DELETE /category/{id}** - Delete the person with id equal to {id}. Only person unused can be deleted.

**4. POST /category** - Create a category.
*RequestBody:*
```
{
  "name": "string",
  "active": true,
  "address": {
    "city": "string",
    "complement": "string",
    "country": "string",
    "neighborhood": "string",
    "number": "string",
    "postalCode": "string",
    "state": "string",
    "street": "string"
  },
}
```
**4. PUT /person/{id}** - Update a person with id equal to {id}.
*RequestBody:*
```
{
  "name": "string",
  "active": true,
  "address": {
    "city": "string",
    "complement": "string",
    "country": "string",
    "neighborhood": "string",
    "number": "string",
    "postalCode": "string",
    "state": "string",
    "street": "string"
  },
}
```

**5. PUT /person/{id}** - Enable/disable a person.
*RequestBody:*
```
true | false
```

### c) Entry
**1. GET /entry** - Search entries:

*QueryParams:*
* resume
* description
* expirationDateInitial
* expirationDateFinal
* size
* page

**2. GET /entry/{id}** - Detail the entry with id equal to {id}

**3. DELETE /entry/{id}** - Delete the entry with id equal to {id}

**4. POST /entry** - update a entry with id equal to {id}.
*RequestBody:*
```
{
  "description": "string",
  "expirationDate": "string",
  "note": "string",
  "payDate": "string",
  "type": "DEBIT",
  "value": 0
  "category": {
    "id": 0,
  },
  "person": {
    "id": 0,
  }
}
```

**5. PUT /entry/{id}**
*RequestBody:*
```
{
  "description": "string",
  "expirationDate": "string",
  "note": "string",
  "payDate": "string",
  "type": "DEBIT",
  "value": 0
  "category": {
    "id": 0,
  },
  "person": {
    "id": 0,
  }
}
```
