create table estado (
   est_id               INT8                 not null,
   est_sigla            VARCHAR(4)           null,
   est_nome             VARCHAR(50)          null,
   constraint pk_estado primary key (est_id)
);

create table cidade (
   cid_id               INT8                 not null,
   est_id               INT8                 null,
   cid_ddd              VARCHAR(3)           null,
   cid_descr            VARCHAR(100)         not null,
   constraint pk_cidade primary key (cid_id)
);

create table endereco (
   end_id               INT8                 not null,
   cid_id               INT8                 null,
   end_logradouro       VARCHAR(100)         not null,
   end_numero           VARCHAR(20)          not null,
   end_cep              VARCHAR(10)          not null,
   end_complto          VARCHAR(30)          null,
   end_bairro           VARCHAR(50)          not null,
   constraint pk_endereco primary key (end_id)
);

alter table cidade
   add constraint fk_cidade_estado foreign key (est_id)
      references estado (est_id)
      on delete restrict on update restrict;

alter table endereco
   add constraint fk_endereco_cidade foreign key (cid_id)
      references cidade (cid_id)
      on delete restrict on update restrict;

create sequence "estado_seq" increment 1 start 1 cache 1;
create sequence "cidade_seq" increment 1 start 1 cache 1;
create sequence "endereco_seq" increment 1 start 1 cache 1;