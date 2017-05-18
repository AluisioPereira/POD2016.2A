-- postgresql
CREATE TABLE notification(
    controle SERIAL NOT NULL,
    fromm VARCHAR(100) NOT NULL,
    text VARCHAR(100) NOT NULL,
    list VARCHAR ARRAY, 
    PRIMARY KEY(fromm)
);


