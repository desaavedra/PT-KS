# Readme

La aplicación está en Java SpringBoot versión 2.7.8

Java version: 1.8

Maven: 4.0

## Postman

En el siguiente link podrán encontrar una colección de Postman con cada uno de los endpoint expuestos. [Postman click acá](https://app.getpostman.com/join-team?invite_code=b5f92e627ec567c89752bfa8dfdacdea&target_code=b34caf467719d0e42e4cb2998029491b)

Se encontrarán los siguientes endpoint:

![Untitled](Readme%2084e9c8d6be894503abc6acc8c0da77d6/Untitled.png)

### Get By Name

A través del parámetro pName en el Query traerá aquellos que contengan el nombre que se envía por parámetro:

[http://localhost:8080/api/flores?pName=amapola](http://localhost:8080/api/flores?pName=amapola)

### Gety By Price

A través del parámetro pPrice en el query traerá aquellos que estén sobre ese precio incluyendo aquellos que sean mayores a 20 cuando pPrice=20.

[http://localhost:8080/api/flores?pPrice=20.0](http://localhost:8080/api/flores?pPrice=20.0)

### Get floreskometsales

Trae las flores ordenadas alfabéticamente descendente y solo retorna los campos name y price

[http://localhost:8080/api/floreskometsales](http://localhost:8080/api/floreskometsales)

### Create Flores

Se le envía un JSON con las flores y este las procesará para guardarlas en la variable estática

[http://localhost:8080/api/flores](http://localhost:8080/api/flores)

Ejemplo del JSON

```json
[
    {
        "id": 1,
        "name": "amapola",
        "price": 13.0
    },
    {
        "id": 2,
        "name": "bmapola",
        "price": 12.0
    },
    {
        "id": 3,
        "name": "gmapola",
        "price": 13.0
    },
    {
        "id": 4,
        "name": "Lobelia",
        "price": 13.0
    }
]
```

### Eliminar una flor

Elimina la flor del ID pasado en la URL

[http://localhost:8080/api/flores/1](http://localhost:8080/api/flores/1)