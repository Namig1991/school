CREATE TABLE driverTable (
    id SERIAL,
    age INTEGER CHECK (age > 18),
    name TEXT NOT NULL PRIMARY KEY ,
    driverLicense BOOLEAN,
    cars_id TEXT REFERENCES carsTable (id)

);

CREATE TABLE carsTable (
    id SERIAL,
    name TEXT NOT NULL PRIMARY KEY,
    model TEXT NOT NULL,
    price MONEY
)