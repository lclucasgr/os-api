CREATE TABLE ordem_servico (
    id bigint not null AUTO_INCREMENT,
    cliente_id bigint NOT NULL,
    descricao TEXT NOT NULL,
    preco decimal(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    data_abertura datetime NOT NULL,
    data_finalizacao DATETIME,
    
    primary key(id)
);

alter table ordem_servico add CONSTRAINT fk_ordem_servico_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id);