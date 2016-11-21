create table estado (
   est_id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
   est_sigla VARCHAR(4),
   est_nome VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table cidade (
   cid_id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
   est_id BIGINT(20),
   cid_descr VARCHAR(100) not null,
   FOREIGN KEY (est_id) REFERENCES estado(est_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table endereco (
   end_id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
   cid_id BIGINT(20),
   end_logradouro VARCHAR(100) not null,
   end_numero VARCHAR(20) not null,
   end_cep VARCHAR(10) not null,
   end_complto VARCHAR(30) null,
   end_bairro VARCHAR(50) not null,
   FOREIGN KEY (cid_id) REFERENCES cidade(cid_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;