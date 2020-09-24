create table promocao(
   id serial primary key not null,
   dataInicio date not null,
   dataFim date not null,
   descricao varchar(50),
   id_servico integer not null references servico(id) ON DELETE CASCADE
<<<<<<< HEAD:src/main/resources/db/migration/V6_inserindo_tabela_promocao.sql
);
=======
);

alter table agendamento alter id_cliente drop not null;
>>>>>>> fcbb3c5b593a41ab7dedbebac99b99e2488f576b:src/main/resources/db/migration/V6__inserindo_tabela_promocao.sql
