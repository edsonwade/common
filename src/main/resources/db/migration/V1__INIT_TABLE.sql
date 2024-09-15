CREATE TABLE IF NOT EXISTS cars
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    brand_name varchar(100)  not null,
    model_name varchar(100)  not null,
    reg_no     varchar(20)   not null,
    car_type   varchar(20)   not null,
    yr         BIGINT        not NULL,
    kms        BIGINT        not null,
    price      DECIMAL(6, 2) NOT NULL


);