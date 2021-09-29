USE DietaJa_test;

-- TRIGGERS
-- Triggers para criar LOG após INSERT do Usuário;

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
DELIMITER ;

-- Triggers para criar LOG após UPDATE do Usuário;
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
DELIMITER ;

-- TESTE Insere Usuário
INSERT INTO Usuario (Login, Senha, TipoUsuario, Descricao, Nome, Ativo)
VALUES ("@testetrigger", "@123455", 3, "Trigger Teste Insert", "Trigger Teste", true);

-- TESTE Update Usuário
UPDATE Usuario SET Ativo = false WHERE ID_Usuario = 1;
SELECT * FROM Log;