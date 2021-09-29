USE DietaJa;

-- TRIGGERS
-- Trigger para criar LOG após INSERT do Usuário;
DROP TRIGGER IF EXISTS Tgr_TabelaUsuario_Insert;
DELIMITER $$
CREATE TRIGGER Tgr_TabelaUsuario_Insert AFTER INSERT
ON Usuario
FOR EACH ROW
BEGIN
INSERT INTO Log (LogContent, LogDate, LogType) 
    VALUES(JSON_OBJECT(
			"ID_Usuario", NEW.ID_Usuario,
            "Login", NEW.Login,
            "Senha", NEW.Senha,
            "TipoUsuario", NEW.TipoUsuario,
            "Descricao", NEW.Descricao,
            "Nome", NEW.Nome,
            "ID_Dieta", NEW.ID_Dieta,
            "Ativo", NEW.Ativo), 
            NOW(), 
			'INSERT');
END 
DELIMITER $$ ;

-- Trigger para criar LOG após UPDATE do Usuário;
DROP TRIGGER IF EXISTS Tgr_TabelaUsuario_Update;
DELIMITER $$
CREATE TRIGGER Tgr_TabelaUsuario_Update AFTER UPDATE
ON Usuario
FOR EACH ROW
BEGIN
INSERT INTO Log (LogContent, LogDate, LogType) 
    VALUES(JSON_OBJECT(
			"ID_Usuario", OLD.ID_Usuario,
            "Login", OLD.Login,
            "Senha", OLD.Senha,
            "TipoUsuario", OLD.TipoUsuario,
            "Descricao", OLD.Descricao,
            "Nome", OLD.Nome,
            "ID_Dieta", OLD.ID_Dieta,
            "Ativo", OLD.Ativo), 
            NOW(), 
            'UPDATE');
END 
$$ 

SHOW TRIGGERS;

-- TESTE Insere Usuário
INSERT INTO Usuario (Login, Senha, TipoUsuario, Descricao, Nome, Ativo)
VALUES ("@testetrigger", "@123455", 3, "Trigger Teste Insert", "Trigger Teste", true);

INSERT INTO Usuario (Login, Senha, TipoUsuario, Descricao, Nome, Ativo)
VALUES ("@aquialfumacoisa", "@2121343", 2, "trestigrestristes ", "Trigger com Procedure", true);

SELECT * FROM Log;

-- TESTE Update Usuário
UPDATE Usuario SET Ativo = false WHERE ID_Usuario = 1;
SELECT * FROM Log;