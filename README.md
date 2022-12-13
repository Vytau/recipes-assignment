# Introduction

Technical assignment about recipes. Application stores recipes in in-memory H2 database which loads all predefined recipes on start. This application includes Rest API which allows recipes to be retrieved from the database.

In addition to that, APIs allow you to filter recipe results through a multiple possible filtration parameters which are described below.

## Usage

In order to run the application successfully you must use required java version in your local environment.

After you configure it you can navigate to RecipesApplication.java and run the main method within your IDE (tested and developed with IntelliJ).

Alternative way to run application by executing command in your terminal ``` ./mvnw spring-boot:run```

## Testing APIs

Project supports swagger-ui which can be accessed at ```localhost:8080/swagger-ui```

## Technical requirements:

* Java 17

# API documentation

## Available APIs

```http
GET /api/recipe/ping
```
```http
GET /api/recipe/all
```
```http
GET /api/recipe/vegetarian
```
```http
GET /api/recipe/search?query='rice'
```
| Parameter | Type     | Description                                    |
|:----------|:---------|:-----------------------------------------------|
| `query`   | `string` | Search text for ingredient or preparation tool |
```http
GET /api/recipe/search-detail?vegetarian=false&nrOfServings=1&includedIngredient=&excludedIngredient=peper&instruction=oven
```
| Parameter            | Type      | Default | Description                                           |
|:---------------------|:----------|---------|:------------------------------------------------------|
| `vegetarian`         | `boolean` | False   | Parameter for searching (not) only vegetarian recipes |
| `nrOfServings`       | `int`     | 0       | Filter on how many servings recipe must have          |
| `includedIngredient` | `string`  | ""      | Ingredient which must be included in the dish         |
| `excludedIngredient` | `string`  | Nothing | Ingredient which must be excluded from the dish       |
| `instruction`        | `string`  | ""      |  Preparation tool which must be in the recipe |

## Status Codes

Recipe application returns the following status codes in its API:

| Status Code | Description             |
|:------------|:------------------------|
| 200         | `OK`                    |
| 400         | `BAD REQUEST`           |
| 404         | `NOT FOUND`             |
| 500         | `INTERNAL SERVER ERROR` |

