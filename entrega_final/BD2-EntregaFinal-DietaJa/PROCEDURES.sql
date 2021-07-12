-- PROCEDURES
USE DietaJa;
SET GLOBAL log_bin_trust_function_creators = 1;
SELECT @@GLOBAL.sql_mode;

-- STORED PROCEDURES
-- PROCEDURE 1 - Associar Porção de Refeições - utilizando func de split;
DROP PROCEDURE IF EXISTS AssociarPorcaoRefeicoesDietaDiaDaSemana;
DELIMITER $$
CREATE PROCEDURE AssociarPorcaoRefeicoesDietaDiaDaSemana(
IN listIdRefeicao VARCHAR(255),
   listIdDiaDaSemana VARCHAR(255),
   listIdSize INT, 
   porcaoDeAlimentoID INT,
   dietaID INT)
BEGIN
DECLARE counter INT DEFAULT 1;
DECLARE idRefeicao VARCHAR(200);
DECLARE idDiaDaSemana VARCHAR(200);
DECLARE idDieta INT DEFAULT 1;
DECLARE id INT;
  WHILE counter <= listIdSize DO
		SET idRefeicao = SPLIT_STR(listIdRefeicao, ',', counter);
        SET idDiaDaSemana = SPLIT_STR(listIdDiaDaSemana, ',', counter);
        SET id = INSERE_PORCAO_ALIMENTO_REFEICAO_DIETA_DIA_DA_SEMANA
        (porcaoDeAlimentoID,
         dietaID,
         CONVERT(idDiaDaSemana, SIGNED),
		 CONVERT(idRefeicao, SIGNED));
        SET counter = counter + 1;
    END WHILE;
END $$
DELIMITER ;

-- Teste inserindo array de refeicoes, array de dias da semana, porcao de alimento e dieta 
CALL DietaJa.AssociarPorcaoRefeicoesDietaDiaDaSemana('1,3,4', '4,3,2', 3, 2, 1);
SELECT * FROM PorcaoDeAlimentoDiasDaSemanaDietaRefeicao;

-- CURSOR e PROCEDURE
DROP PROCEDURE IF EXISTS InsertLogProcedure;
DELIMITER $$
CREATE PROCEDURE InsertLogProcedure(
INOUT jsonObject VARCHAR(3000))
BEGIN
DECLARE LogContent VARCHAR(3000) DEFAULT "";
DECLARE LogDate DATETIME;
DECLARE LogType VARCHAR(10) DEFAULT "";
DECLARE is_done INTEGER DEFAULT 0;
DECLARE cursor1 CURSOR FOR SELECT LogContent, LogDate, LogType FROM DietaJa.Log;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET is_done = TRUE;
OPEN cursor1;
   getJson: LOOP
		FETCH cursor1 
        INTO LogContent, LogDate, LogType;
        INSERT INTO Log(LogContent, LogDate, LogType) 
        VALUES (JSON_OBJECT(
			"ID_Usuario", ID_Usuario,
            "Login", Login,
            "Senha", Senha,
            "TipoUsuario", TipoUsuario,
            "Descricao", Descricao,
            "Nome", Nome,
            "ID_Dieta", ID_Dieta,
            "Ativo", Ativo), 
            NOW(), 
            'INSERT');
		END LOOP getJson;
CLOSE cursor1;
END $$
DELIMITER ;


