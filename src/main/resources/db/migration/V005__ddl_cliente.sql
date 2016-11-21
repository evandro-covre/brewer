create table cliente (
  cli_id  BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  end_id  BIGINT(20),
  cli_nm_razao VARCHAR(100)         not null,
  cli_tp_pessoa CHAR(1)              null,
  cli_cnpj VARCHAR(20)          not null,
  cli_email VARCHAR(100)         not null,
  cli_fone1 VARCHAR(14)          null,
  FOREIGN KEY (end_id) REFERENCES endereco(end_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;