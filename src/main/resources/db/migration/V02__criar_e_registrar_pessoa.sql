CREATE TABLE pessoa(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(20) NOT NULL,
    logradouro VARCHAR(20),
    numero VARCHAR(5),
    complemento VARCHAR(20),
    bairro VARCHAR(20),
    cep VARCHAR(20),
    cidade VARCHAR(20),
    estado VARCHAR(20),
    ativo BOOLEAN
)ENGINE=InnoDB DEFAULT CHARSET=utf8;