create table cliente (
  cli_id               INT4                 not null,
  end_id               INT8                 not null,
  cli_nm_razao         VARCHAR(100)         not null,
  cli_tp_pessoa        CHAR(1)              null,
  cli_cnpj             VARCHAR(20)          not null,
  cli_email            VARCHAR(100)         not null,
  cli_fone1            VARCHAR(14)          null,
  constraint pk_cliente primary key (cli_id)
);

alter table cliente
  add constraint fk_cliente_endereco foreign key (end_id)
references endereco (end_id)
on delete restrict on update restrict;

create sequence "cliente_seq" increment 1 start 1 cache 1;