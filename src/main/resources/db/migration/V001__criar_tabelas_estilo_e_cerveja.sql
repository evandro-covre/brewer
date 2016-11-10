CREATE TABLE "public"."estilo"(
  codigo BIGINT NOT NULL PRIMARY KEY,
  nome VARCHAR(50) NOT NULL
);

CREATE TABLE "public"."cerveja"(
  codigo BIGINT NOT NULL PRIMARY KEY ,
  sku VARCHAR(50) NOT NULL,
  nome VARCHAR(50) NOT NULL,
  descricao TEXT NOT NULL,
  valor DECIMAL(10, 2) NOT NULL,
  teor_alcolico DECIMAL(10, 2) NOT NULL,
  comissao DECIMAL(10, 2),
  sabor VARCHAR(50),
  origem VARCHAR(50),
  codigo_estilo BIGINT,
  FOREIGN KEY (codigo_estilo) REFERENCES estilo(codigo)
);

insert into estilo VALUES (1, 'Amber Lager');
insert into estilo VALUES (2, 'Dark Lager');
insert into estilo VALUES (3, 'Pale Lager');
insert into estilo VALUES (4, 'Pilsener');