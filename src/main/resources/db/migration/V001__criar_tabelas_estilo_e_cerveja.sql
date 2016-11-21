CREATE TABLE estilo(
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE cerveja(
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  sku VARCHAR(50) NOT NULL,
  nome VARCHAR(80) NOT NULL,
  descricao TEXT NOT NULL,
  valor DECIMAL(10, 2) NOT NULL,
  teor_alcolico DECIMAL(10, 2) NOT NULL,
  comissao DECIMAL(10, 2),
  sabor VARCHAR(50),
  origem VARCHAR(50),
  codigo_estilo BIGINT(20) NOT NULL,
  FOREIGN KEY (codigo_estilo) REFERENCES estilo(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into estilo VALUES (1, 'Amber Lager');
insert into estilo VALUES (2, 'Dark Lager');
insert into estilo VALUES (3, 'Pale Lager');
insert into estilo VALUES (4, 'Pilsener');