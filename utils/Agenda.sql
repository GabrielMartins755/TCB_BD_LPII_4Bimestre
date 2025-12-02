create database agendadeevento;
use agendadeevento;

create table evento (
    id_evento int auto_increment primary key,
    nome_evento varchar(100) not null,
    data_evento date not null,
    hora_evento time not null,
    local_evento varchar(100) not null,
    qtd_max_pessoas int not null
);

create table pessoa (
    id_pessoa int auto_increment primary key,
    nome varchar(100) not null,
    dt_nascimento date not null,
    cpf varchar(14) not null unique,
    telefone varchar(20) not null,
    email varchar(100) not null unique

);

create table funcionario (
    id_funcionario int auto_increment primary key,
    id_pessoa int not null,
    funcao varchar(100) not null,
    num_banco varchar(10) not null,
    salario int not null,
    foreign key (id_pessoa) references pessoa(id_pessoa)
     on delete cascade
);

create table convidado (
    id_convidado int auto_increment primary key,
    id_pessoa int not null,
    foreign key (id_pessoa) references pessoa(id_pessoa)
     on delete cascade
);

create table convidado_evento (
    id_convidado int not null,
    id_evento int not null,
    primary key (id_convidado, id_evento),
    foreign key (id_convidado) references convidado(id_convidado),
    foreign key (id_evento) references evento(id_evento)
     on delete cascade
);

create table agenda (
    id int auto_increment primary key,
    id_evento int not null,
    foreign key (id_evento) references evento(id_evento)
     on delete cascade
);

create table funcionario_evento (
    id_funcionario int not null,
    id_evento int not null,
    primary key (id_funcionario, id_evento),
    foreign key (id_funcionario) references funcionario(id_funcionario) on delete cascade,
    foreign key (id_evento) references evento(id_evento) on delete cascade
);
