USE DietaJa;
SET GLOBAL log_bin_trust_function_creators = 1;

-- FUNÇÕES
-- FUNCTION 1 - SPLIT 
DROP FUNCTION IF EXISTS SPLIT_STR;
CREATE FUNCTION SPLIT_STR(
  x VARCHAR(255),
  delim VARCHAR(12),
  pos INT
)
RETURNS VARCHAR(255)
RETURN REPLACE(SUBSTRING(SUBSTRING_INDEX(x, delim, pos),
       LENGTH(SUBSTRING_INDEX(x, delim, pos -1)) + 1),
       delim, '');
       
-- FUNCTION 2 - Inserir Porcao de Alimento conforme refeicao;    
DROP FUNCTION IF EXISTS INSERE_PORCAO_ALIMENTO_REFEICAO;
DELIMITER $$
CREATE FUNCTION INSERE_PORCAO_ALIMENTO_REFEICAO (porcaoDeAlimentoID INT, porcaoDeRefeicaoID INT)
RETURNS INT DETERMINISTIC
BEGIN
	INSERT INTO RefeicaoPorcaoDeAlimento (ID_PorcaoAlimento, Refeicao) VALUES (porcaoDeAlimentoID, porcaoDeRefeicaoID);
	RETURN LAST_INSERT_ID();
END $$

-- STORED PROCEDURES
-- PROCEDURE 1 - Associar Porção de Refeições - utilizando func de split;
DROP PROCEDURE IF EXISTS AssociarPorcaoRefeicoes;
DELIMITER $$
CREATE PROCEDURE AssociarPorcaoRefeicoes(IN listIdRefeicao VARCHAR(255), listIdSize INT, porcaoDeAlimentoID INT)
BEGIN
DECLARE counter INT DEFAULT 1;
DECLARE idRefeicao VARCHAR(200);
DECLARE proc VARCHAR(200);
DECLARE id INT;
  WHILE counter <= listIdSize DO
		SET idRefeicao = SPLIT_STR(listIdRefeicao, ',', counter);
        SET id = INSERE_PORCAO_ALIMENTO_REFEICAO(porcaoDeAlimentoID, CONVERT(idRefeicao, SIGNED));
        SET counter = counter + 1;
    END WHILE;
END $$
DELIMITER ;

-- PROCEDURE 2 - Associar Porcao de Alimento à Dieta - utiliza func split;
DROP PROCEDURE IF EXISTS AssociarPorcaoAlimentoDieta;
DELIMITER $$
CREATE PROCEDURE AssociarPorcaoAlimentoDieta(IN listIdPorcaoAlimento VARCHAR(255), listIdSize INT, idDieta INT)
BEGIN
DECLARE counter INT DEFAULT 1;
DECLARE idPorcaoAlimento VARCHAR(200);
DECLARE proc VARCHAR(200);
DECLARE id INT;
  WHILE counter <= listIdSize DO
		SET idPorcaoAlimento = SPLIT_STR(listIdPorcaoAlimento, ',', counter);
        INSERT PorcaoDeAlimentoDieta(ID_PorcaoAlimento, ID_Dieta) VALUES(idPorcaoAlimento, idDieta);
        SET counter = counter + 1;
    END WHILE;
END $$
DELIMITER ;

-- TESTES PROCEDURES
CALL AssociarPorcaoRefeicoes('1,2,3', 3, 1);
SELECT * FROM RefeicaoPorcaoDeAlimento;

CALL AssociarPorcaoAlimentoDieta('1,2,3', 3, 1);
SELECT * FROM PorcaoDeAlimentoDieta;