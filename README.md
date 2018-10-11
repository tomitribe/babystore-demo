# BabyStore Demo

Build the babystore-demo project with all modules.

## Run the web app with the rest resources

```
cd war && mvn tomee:run
```

## CLI
```
cd ../cli/target
```
Get Movies
```
./baby --url http://localhost:8080/baby/api movies get-movies
```
Find Movie with id 1
```
./baby --url http://localhost:8080/baby/api movies find 1
```
Add Movie
```
./baby --url http://localhost:8080/baby/api movies add-movie --year 1984 --genre Action --rating 9 --title "The Terminator" --director Ivan
```
Delete Movie
```
./baby --url http://localhost:8080/baby/api movies delete-movie 1
```
Update Movie
```
./baby --url http://localhost:8080/baby/api movies update-movie 2 --director David
```
Count number of Movies
```
./baby --url http://localhost:8080/baby/api movies count 
```

Call with signature authentication
If you add --key-id, it will look for the key in ~/.ssh/key_id
Replace the <url> with the route path.
  
```
./baby --verbose --key-id id_rsa --url <url> movies get-movies
```
