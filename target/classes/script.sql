CREATE TABLE pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_controle VARCHAR(255) UNIQUE NOT NULL,
    data_cadastro DATETIME,
    nome_produto VARCHAR(255) NOT NULL,
    valor_unitario DOUBLE NOT NULL,
    quantidade INT,
    codigo_cliente INT NOT NULL,
    valor_total DOUBLE
);
