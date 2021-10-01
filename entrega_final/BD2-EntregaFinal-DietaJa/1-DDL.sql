-- DDL - DietaJá;
-- Cria banco de dados DietaJá
-- DROP DATABASE DietaJa;
CREATE DATABASE IF NOT EXISTS DietaJa;

-- Usa o banco de dados
USE DietaJa;

-- Cria tabelas a serem utilizadas no banco
CREATE TABLE Dieta (
	ID_Dieta int auto_increment not null,
    Nome varchar(50) not null,
    Descricao varchar(50) null,
    Ativo bit not null,
    CONSTRAINT PK_Dieta
    PRIMARY KEY (ID_Dieta));

CREATE TABLE PorcaoDeAlimento (
	ID_PorcaoDeAlimento int auto_increment not null,
    Nome varchar(50) not null,
    Descricao varchar(50) null,
    Ativo bit not null,
    Quantidade int not null,
    TipoMedida int not null,
    CONSTRAINT PK_PorcaoDeAlimento
    PRIMARY KEY (ID_PorcaoDeAlimento));

CREATE TABLE PorcaoDeAlimentoDiasDaSemanaDietaRefeicao (
    ID_PorcaoDeAlimento int not null,
    ID_Dieta int not null,
    DiaDaSemana int not null,
    Refeicao int not null,
    CONSTRAINT PK_PDADSDR
    PRIMARY KEY (ID_PorcaoDeAlimento, ID_Dieta, DiaDaSemana, Refeicao));

CREATE TABLE RegistroDeAtividade (
	ID_RegistroDeAtividade int auto_increment not null,
    ID_Dieta int not null,
    ID_PorcaoDeAlimento int not null,
    ID_Usuario int not null,
    Registro datetime not null,
    Comentarios  varchar(140) null,
    Sentimento int not null,
    Refeicao int not null,
    DiaDaSemana int not null,

    CONSTRAINT PK_RegistroDeAtividade
    PRIMARY KEY (ID_RegistroDeAtividade));

CREATE TABLE Usuario (
	ID_Usuario int auto_increment not null,
    Login varchar(50) not null,
    Senha varchar(20) not null,
    TipoUsuario int not null,
    Descricao varchar(50) null,
    Nome varchar(50) not null,
    ID_Dieta int null,
    Ativo bit not null,
    CONSTRAINT PK_Usuario
    PRIMARY KEY (ID_Usuario));

CREATE TABLE Log (
	ID_Log INT AUTO_INCREMENT NOT NULL,
    LogContent VARCHAR(3000),
    LogDate DATETIME,
    LogType VARCHAR(10),
    PRIMARY KEY (ID_Log));

-- Adiciona referências estrangeiras nas tabelas necessárias;
ALTER TABLE DietaJa.RegistroDeAtividade ADD CONSTRAINT FK_RegistroDeAtividade_Dieta
FOREIGN KEY (ID_Dieta) REFERENCES DietaJa.Dieta (ID_Dieta);

ALTER TABLE DietaJa.RegistroDeAtividade ADD CONSTRAINT FK_RegistroDeAtividade_PorcaoDeAlimento
FOREIGN KEY (ID_PorcaoDeAlimento) REFERENCES DietaJa.PorcaoDeAlimento (ID_PorcaoDeAlimento);

ALTER TABLE DietaJa.RegistroDeAtividade ADD CONSTRAINT FK_RegistroDeAtividade_Usuario
FOREIGN KEY (ID_Usuario) REFERENCES DietaJa.Usuario (ID_Usuario);

ALTER TABLE DietaJa.PorcaoDeAlimentoDiasDaSemanaDietaRefeicao ADD CONSTRAINT FK_PorcaoDeAlimento_PDADSDR
FOREIGN KEY (ID_PorcaoDeAlimento) REFERENCES DietaJa.PorcaoDeAlimento (ID_PorcaoDeAlimento);

ALTER TABLE DietaJa.PorcaoDeAlimentoDiasDaSemanaDietaRefeicao ADD CONSTRAINT FK_Dieta_PDADSDR
FOREIGN KEY (ID_Dieta) REFERENCES DietaJa.Dieta (ID_Dieta);

ALTER TABLE DietaJa.Usuario ADD CONSTRAINT FK_Dieta_usr
FOREIGN KEY (ID_Dieta) REFERENCES DietaJa.Dieta (ID_Dieta);