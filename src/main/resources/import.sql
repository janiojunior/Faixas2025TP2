-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into estado (nome, sigla, regiao) values ('Tocantins', 'TO', 2);
insert into estado (nome, sigla, regiao) values ('São Paulo', 'SP', 4);
insert into estado (nome, sigla, regiao) values ('Goiás', 'GO', 1);
insert into estado (nome, sigla, regiao) values ('Rio Grande do Sul', 'RS', 5);
insert into estado (nome, sigla, regiao) values ('Rio de Janeiro', 'RJ',4);

insert into faixa (nome, descricao, preco, estoque, modalidade, nomeImagem) values( 'Faixa Preta', 'Descricao Faixa Preta', 100.0, 10, 1, 'faixa_preta_jiu.png');
insert into faixa (nome, descricao, preco, estoque, modalidade, nomeImagem) values( 'Faixa Preta', 'Descricao Faixa Preta', 100.0, 10, 2, 'faixa_preta_judo.png');
insert into faixa (nome, descricao, preco, estoque, modalidade, nomeImagem) values( 'Faixa Preta', 'Descricao Faixa Preta', 100.0, 10, 3, 'faixa_preta_judo.png');
insert into faixa (nome, descricao, preco, estoque, modalidade, nomeImagem) values( 'Faixa Azul', 'Descricao Faixa Azul', 80.0, 10, 1, 'faixa_azul_jiu.png');
insert into faixa (nome, descricao, preco, estoque, modalidade, nomeImagem) values( 'Faixa Roxa', 'Descricao Faixa Roxa', 85.0, 10, 1, 'faixa_roxa_jiu.png');
insert into faixa (nome, descricao, preco, estoque, modalidade, nomeImagem) values( 'Faixa Marrom', 'Descricao Faixa Marrom', 90.0, 10, 1, 'faixa_marrom_jiu.png');
