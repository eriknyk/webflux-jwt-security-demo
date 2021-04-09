Spring Webflux JWT Security Demo
================================

The motivation for this demo, is just because it is very difficult to find a complete implementation
of spring webflux + security + jwt + r2db all in one functional implementation, all that you can find in internet are incomplete, not functional 
or very older examples, I didn't find any complete example like this from official spring examples neither.
That's why I did it and wanted to share it to anybody that is needing it.

Any improvement, fix, contribution are welcome.  

Happy codding!!

## What this demo has?
- Spring webflux
- Spring security implemented with JWT + validation layer
- User register demo endpoint
- User authentication endpoint
- Model to dto mapping (using mapstruct)
- User R2db with Postgresql repository impl
- User validation in spring security layer, according to the user record in db 

## Data base setup
- If you don't have installed Postgresql locally you can just run it with docker

```bash
docker-compose -f src/main/docker/docker-compose.yml up -d
```

## Create db and Users table
just execute the sql script located in `src/resources/schema/database.sql` or copy teh following sentences:

```sql
CREATE DATABASE "webflux-security";

CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR(64),
    password   VARCHAR(64),
    roles      TEXT[],
    first_name VARCHAR(64),
    last_name  VARCHAR(64),
    enabled    BOOLEAN,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
```


## Create demo user

```bash
curl http://localhost:9000/public/demo-user \
  -X POST \
  -H 'Content-Type: application/json' \
  -d '{
    "username": "admin",
    "password": "admin",
    "first_name": "John",
    "last_name": "Doe" 
  }'
```

## Authenticate and get a valid JWT token
```bash
curl http://localhost:9000/login \
  -X POST \
  -H 'Content-Type: application/json' \
  -d '{
    "username": "admin",
    "password": "admin"
  }' | json_pp
```

API Response
```json
{
   "issuedAt" : "2021-04-09T18:48:04.052+00:00",
   "userId" : 1,
   "expiresAt" : "2021-04-10T02:48:04.052+00:00",
   "token" : "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6WyJST0xFX1VTRVIiXSwiaXNzIjoiYWRtaW4iLCJleHAiOjE2MTgwMjI4ODQsImlhdCI6MTYxNzk5NDA4NCwianRpIjoiODUzNTAwNDUtYjNjNy00MTA3LWIyZjUtOGEwNDUyNjVmZWM5In0.okhxY7BsK3S3ABNMJlm1WhGdjssy676d6bNkZ3ybN34"
}
```

## Make an authenticated request
(!) Use jwt token obtained previously 

```bash
curl http://localhost:9000/user \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6WyJST0xFX1VTRVIiXSwiaXNzIjoiYWRtaW4iLCJleHAiOjE2MTgwMjI4ODQsImlhdCI6MTYxNzk5NDA4NCwianRpIjoiODUzNTAwNDUtYjNjNy00MTA3LWIyZjUtOGEwNDUyNjVmZWM5In0.okhxY7BsK3S3ABNMJlm1WhGdjssy676d6bNkZ3ybN34' | json_pp
```

API Response
```json
{
   "enabled" : true,
   "id" : 1,
   "first_name" : "John",
   "username" : "admin",
   "last_name" : "Doe"
}
```

# Last notes
- The default JWT token expiration is 28800 seconds = 8 hours, you can configure this and other jwt params in `src/resources/application.yml`
- If you update the user record in db, updating the enabled column to false, and try to fetch `GET /user` once again api will return an error 401  

# License
MIT