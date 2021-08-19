# PROJET 6

PayMyBuddy est un projet de cours OpenClassrooms :

C'est le back-end d'une application qui permet de payer/rembourser ses amis


## DATABASE Schema

![diagram](https://user-images.githubusercontent.com/33994110/129045435-8f0952ff-b9f9-4b56-90e8-fdf5e25da054.png)

## UML Diagram

![diagra](https://user-images.githubusercontent.com/33994110/129046060-3153c03f-e0ad-446f-a9d5-5b7a81961eb8.png)


## Run API

Default Controller URL : http://localhost:8080/.

## Endpoints

Authentication

### POST

* /api/register
Exemple du Json Body :
`{
    "firstName": "Cristiano",
    "lastName": "Ronaldo",
    "username": "cr7@gmail.com",
    "password": "123456",
    "repassword": "123456"
}`

* /paymybuddy/login
- Retourne une reponse json avec JWT (Json Web Token)
