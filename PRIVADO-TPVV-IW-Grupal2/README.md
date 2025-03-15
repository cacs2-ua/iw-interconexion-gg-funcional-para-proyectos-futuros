1. Para levantar la base de datos de `PostgreSQL` en un contenedor de `Docker`, ejecutar el siguiente comando:

```sh
docker run --name postgres-tpvv-develop -e POSTGRES_USER=tpvv -e POSTGRES_PASSWORD=tpvv -e POSTGRES_DB=tpvv -p 5462:5432 -d postgres:13
```

2. Para ejecutar la aplicación para que se conecte a la base de datos de PostgreSQL, ejecutar el siguiente comando:

```sh
mvn spring-boot:run -D spring-boot.run.profiles=postgres
```

3. Para levantar la base de datos de `PostgreSQL` para los TESTS en un contenedor de `Docker`, ejecutar el siguiente comando:

```sh
docker run --name postgres-tpvv-test -e POSTGRES_USER=tpvv -e POSTGRES_PASSWORD=tpvv -e POSTGRES_DB=tpvv_test -p 5463:5432 -d postgres:13
```

4. Para ejecutar los tests para que se ejecuten con la base de datos de PostgreSQL, ejecutar el siguiente comando:

```sh
mvn test "-Dspring-boot.run.profiles=postgres"
```