insert into usuario (masp, nome, sobrenome, senha, codigo, carreira) values ('13522909', 'Rafael', 'Martins Galdino', '$2a$10$I19bzWP0IoZWh/BTyp2xkO/RDFJEx..HWZhLZWcNk0QCEz7sWV1qS', 'DEPP', 'TECNICO');

insert into permissao (permissao) values ('ROLE_CADASTRO_FROTA');
insert into permissao (permissao) values ('ROLE_CADASTRO_PESSOAL');
insert into permissao (permissao) values ('ROLE_CADASTRO_USUARIO');
insert into permissao (permissao) values ('ROLE_RELATORIO_FROTA');
insert into permissao (permissao) values ('ROLE_RELATORIO_PESSOAL');
insert into permissao (permissao) values ('ROLE_RELATORIO_USUARIO');
insert into permissao (permissao) values ('ROLE_RELATORIO_GERAL');

insert into usuario_permissao (usuario_id, permissao_id) values (1,1);
insert into usuario_permissao (usuario_id, permissao_id) values (1,2);
insert into usuario_permissao (usuario_id, permissao_id) values (1,3);
insert into usuario_permissao (usuario_id, permissao_id) values (1,4);
insert into usuario_permissao (usuario_id, permissao_id) values (1,5);
insert into usuario_permissao (usuario_id, permissao_id) values (1,6);
insert into usuario_permissao (usuario_id, permissao_id) values (1,7);

