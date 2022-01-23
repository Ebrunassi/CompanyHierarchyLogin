# **Personio - Ever-Changing Hierarchy Problem - Login**

This project was developed in order to authenticate a user an then give access to hierarchy-company system.

The service was developed using Java 11, and it contains endpoints for requests:

Verbo HTTP | Resource Path | Descrição
----|------------------------------------------|--------
GET| http://localhost:8080/personio/login | Will authenticate a submitting a json containing the user's login and password
POST |http://localhost:8080/personio/register | Saves the given user's informations in database


### **[GET] /personio/login**
This endpoint accept the user's login and password in json format:
```json
{
	"username":"personio",
	"password":"personio"
}
``` 

If the given user doesn't exists in database, a `HTTP 403 - Forbidden` status code will be returned.

On the other hand, if the user exists, a JWT token will be returned:

```json
{
	"token_type": "Bearer",
	"access_token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwZXJzb25pbyIsImV4cCI6MTY0Mjk4OTM1OH0.G8ll2TEOYqpNqu_A-FG30Z9cb4dhTiOCauNgSi8fjo8mlPGzAPeIyXBvBTRxHbE8K1rHPKYQ5d5zm0mG3Ntdow",
	"expiration": "24/01/2022 01:55:58"
}
```

`Important:` This JWT token is required for every requests in *company-hierarchy* system. To use the token, just put in the header of each requests:
> Authorization: Bearer *{access_token_value}*
### **[POST] /personio/register**

Receives a JSON containing the username and password of a new user in order to be saved in database:
```json
{
	"username":"personio",
	"password":"personio"
}
```

If there is no user with the same username, the following message will be returned:
```json
{
	"message": "Account registered successfully"
}
```
Otherwise, if there is already a user with the given username, the following message will be returned:
```json
{
	"message": "There is already an account registered with username 'personio'"
}
```

**Password Cryptography**
-----
To ensure more security, the password will be encrypted and saved in database using `RSA Cryptography`.

The public and private key of RSA was created by running a simple RSA keys generation and saved in code, you can access it in Cryptography.java class.


**Unit Tests**
--------
This project was developed using TDD, and there are tests for *repository* layer. It was used `JUnit` and `Mockit` to develop and mock each testing success requests and possible failures that could have for users experience.

**Logs**
-------
Every received requests generate logs. Knowing that saving all the logs is essential for debugging, this projects uses `Log4j2` to generate and save all the logs in files.

All the created log's files will be saved in the `logs` folder, which will contains all this files organized by the creation day.

**Swagger**
---------
For a better user experiencie, it was used `swagger` to be accessed and to have a better knowledge about all the requests in this service.

You can access it in 
> http://localhost:8080/swagger-ui/index.html
