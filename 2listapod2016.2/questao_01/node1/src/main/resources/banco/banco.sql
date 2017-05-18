-- postgresql
CREATE TABLE pessoa(
    controle SERIAL NOT NULL, 
    id INTEGER NOT NULL,    
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);


