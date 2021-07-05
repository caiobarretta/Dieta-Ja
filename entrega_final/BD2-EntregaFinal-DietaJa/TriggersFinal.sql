USE DietaJa;
-- Trigger para capturar o JSON do user que está sendo inserido no banco de dados
DROP TRIGGER trigger_log_after_insert_new_user;
DELIMITER $$
CREATE TRIGGER trigger_log_after_insert_new_user
AFTER INSERT ON Usuario FOR EACH ROW
BEGIN
    INSERT INTO Log (
        log_id,
        old_row_data,
        new_row_data,
        dml_type,
        dml_timestamp
    )
    VALUES(
        NEW.ID_usuario,
        null,
        JSON_OBJECT(
            "Login", NEW.Login,
            "Senha", NEW.Senha,
            "TipoUsuario", NEW.TipoUsuario,
            "Descricao", NEW.Descricao,
            "Nome", NEW.Nome,
            "ID_Dieta", NEW.ID_Dieta,
            "Ativo", NEW.Ativo
        ),
        'INSERT',
        CURRENT_TIMESTAMP);
END ;
$$

SHOW TRIGGERS;

-- TEST Trigger quando adiciona novo usuário - precisa melhorar;
INSERT INTO Usuario (ID_Usuario, Login, Senha, TipoUsuario, Descricao, Nome, Ativo) 
VALUES (1088, 'trg_teste', '12345%', 3, 'Trigger Teste', 'High-Performance Java Persistence 1st edition', true);

-- TEST 2
INSERT INTO Usuario (ID_Usuario, Login, Senha, TipoUsuario, Descricao, Nome, Ativo) 
VALUES (8987, 'trg_teste_2', '1234522%', 2, 'Trigger Teste 2', 'Log User 2', false);