CREATE DATABASE IF NOT EXISTS agenda_eventos;
USE agenda_eventos;

-- Tabela principal: eventos
CREATE TABLE eventos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_evento VARCHAR(100) NOT NULL,
    local_evento VARCHAR(100) NOT NULL,
    data_evento DATETIME NOT NULL,
    qtd_max_pessoas INT NOT NULL
);

-- Tabela de pessoas (convidados)
CREATE TABLE pessoas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    dt_nascimento DATE NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100),
    evento_id INT,
    FOREIGN KEY (evento_id) REFERENCES eventos(id) 
);

-- Tabela de funcionários
CREATE TABLE funcionarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    funcao VARCHAR(100) NOT NULL,
    salario DECIMAL(10,2) NOT NULL,
    num_banco VARCHAR(20),
    evento_id INT,
    FOREIGN KEY (evento_id) REFERENCES eventos(id) 
);


