-- DDL - DietaJá;

-- Cria banco de dados DietaJá
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
	ID_PorcaoAlimento int auto_increment not null,
    Nome varchar(50) not null,
    Descricao varchar(50) null,
    Ativo bit not null,
    CONSTRAINT PK_PorcaoAlimento
    PRIMARY KEY (ID_PorcaoAlimento));
    
CREATE TABLE PorcaoDeAlimentoDiasDaSemana (
	ID_PorcaoDeAlimentoDiasDaSemana int auto_increment not null,
    ID_PorcaoAlimento int not null,
    DiaDaSemana int not null,
    CONSTRAINT PK_PorcaoDeAlimentoDiasDaSemana
    PRIMARY KEY (ID_PorcaoDeAlimentoDiasDaSemana));

CREATE TABLE PorcaoDeAlimentoDieta (
	ID_PorcaoDeAlimentoDieta int auto_increment not null,
    ID_PorcaoAlimento int not null,
    ID_Dieta int not null,
    CONSTRAINT PK_Rel_Por_Dieta
    PRIMARY KEY (ID_PorcaoDeAlimentoDieta));

CREATE TABLE PorcaoDeAlimentoRegistroDeAtividade (
	ID_PorcaoDeAlimentoRegistroDeAtividade int auto_increment not null,
    ID_PorcaoAlimento int not null,
    ID_RegistroDeAtividades int not null,
    CONSTRAINT PK_Rel_Porc_RegistroDeAtividade
    PRIMARY KEY (ID_PorcaoDeAlimentoRegistroDeAtividade));
    
CREATE TABLE RefeicaoPorcaoDeAlimento (
	ID_RefeicaoPorcaoDeAlimento int auto_increment not null,
	ID_PorcaoAlimento int not null,
    Refeicao int not null,
    CONSTRAINT PK_Rel_Ref_Porc
    PRIMARY KEY (ID_RefeicaoPorcaoDeAlimento));
    
CREATE TABLE RegistroDeAtividade (
	ID_RegistroDeAtividade int auto_increment not null,
    ID_Dieta int not null,
    ID_PorcaoAlimento int not null,
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

-- Adiciona referências estrangeiras nas tabelas necessárias;
ALTER TABLE DietaJa.PorcaoDeAlimentoDiasDaSemana ADD CONSTRAINT FK_Rel_Porc_Dia_PorcaoDeAlimento
FOREIGN KEY (ID_PorcaoAlimento) REFERENCES DietaJa.PorcaoDeAlimento (ID_PorcaoAlimento);

ALTER TABLE DietaJa.PorcaoDeAlimentoDieta ADD CONSTRAINT FK_Rel_Porc_Dieta_Dieta
FOREIGN KEY (ID_Dieta) REFERENCES DietaJa.Dieta (ID_Dieta);

ALTER TABLE DietaJa.PorcaoDeAlimentoDieta ADD CONSTRAINT FK_Rel_Porc_Dieta_PorcaoDeAlimento
FOREIGN KEY (ID_PorcaoAlimento) REFERENCES DietaJa.PorcaoDeAlimento (ID_PorcaoAlimento);

ALTER TABLE DietaJa.PorcaoDeAlimentoRegistroDeAtividade ADD CONSTRAINT FK_PorcaoDeAlimentoRegistroDeAtividade_PorcAlimenRegAtividade
FOREIGN KEY (ID_RegistroDeAtividades) REFERENCES DietaJa.RefeicaoPorcaoDeAlimento (ID_RefeicaoPorcaoDeAlimento);

ALTER TABLE DietaJa.PorcaoDeAlimentoRegistroDeAtividade ADD CONSTRAINT FK_Rel_Porc_RegistroDeAtividade_PorcaoDeAlimento
FOREIGN KEY (ID_PorcaoAlimento) REFERENCES DietaJa.PorcaoDeAlimento (ID_PorcaoAlimento);

ALTER TABLE DietaJa.RefeicaoPorcaoDeAlimento ADD CONSTRAINT FK_Rel_Ref_Porc_PorcaoDeAlimento
FOREIGN KEY (ID_PorcaoAlimento) REFERENCES DietaJa.PorcaoDeAlimento (ID_PorcaoAlimento);

ALTER TABLE DietaJa.RegistroDeAtividade ADD CONSTRAINT FK_RegistroDeAtividade_Dieta
FOREIGN KEY (ID_Dieta) REFERENCES DietaJa.Dieta (ID_Dieta);

ALTER TABLE DietaJa.RegistroDeAtividade ADD CONSTRAINT FK_RegistroDeAtividade_PorcaoDeAlimento
FOREIGN KEY (ID_PorcaoAlimento) REFERENCES DietaJa.PorcaoDeAlimento (ID_PorcaoAlimento);

ALTER TABLE DietaJa.RegistroDeAtividade ADD CONSTRAINT FK_RegistroDeAtividade_Usuario
FOREIGN KEY (ID_Usuario) REFERENCES DietaJa.Usuario (ID_Usuario);