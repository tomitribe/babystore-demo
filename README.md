# Trapease Demo

Build the trapease-demo project with all modules.

## Run the web app with the rest resources

```
cd war && mvn tomee:run
```

## CLI
```
cd ../cli/target
./cli --url http://localhost:8080/trapease movie create --director "James Cameron" --title "The Terminator"
./cli --url http://localhost:8080/trapease movie read 1
```
