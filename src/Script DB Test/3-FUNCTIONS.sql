USE DietaJa_test;
SET GLOBAL log_bin_trust_function_creators = 1;

-- FUNCTIONS
-- CONVERT ENUMS: Sentimento, Refeicao, DiaDaSemana
DROP FUNCTION IF EXISTS CONVERT_ENUM_SENTIMENTO_STR;
CREATE FUNCTION CONVERT_ENUM_SENTIMENTO_STR(
  enumerador INT
)
RETURNS VARCHAR(255)
RETURN 
(CASE
	WHEN enumerador = 1 THEN "Muito Satisfeito"
    WHEN enumerador = 2 THEN "Satisfeito"
    WHEN enumerador = 3 THEN "Razoavelmente satisfeito"
    WHEN enumerador = 4 THEN "Um pouco insatisfeito"
    WHEN enumerador = 5 THEN "Insatisfeito"
    WHEN enumerador = 6 THEN "Totalmente insatisfeito"
    END);

DROP FUNCTION IF EXISTS CONVERT_ENUM_REFEICAO_STR;
CREATE FUNCTION CONVERT_ENUM_REFEICAO_STR(
  enumerador INT
)
RETURNS VARCHAR(255)
RETURN 
(CASE
	WHEN enumerador = 1 THEN "Café da manhã"
    WHEN enumerador = 2 THEN "Brunch"
    WHEN enumerador = 3 THEN "Almoço"
    WHEN enumerador = 4 THEN "Lanche"
    WHEN enumerador = 5 THEN "Jantar"
    WHEN enumerador = 6 THEN "Ceia"
END);

DROP FUNCTION IF EXISTS CONVERT_ENUM_DIA_DA_SEMANA_STR;
CREATE FUNCTION CONVERT_ENUM_DIA_DA_SEMANA_STR(
  enumerador INT
)
RETURNS VARCHAR(255)
RETURN 
(CASE
	WHEN enumerador = 1 THEN "Segunda-Feira"
    WHEN enumerador = 2 THEN "Terça-Feira"
    WHEN enumerador = 3 THEN "Quarta-Feira"
    WHEN enumerador = 4 THEN "Quinta-Feira"
    WHEN enumerador = 5 THEN "Sexta-Feira"
    WHEN enumerador = 6 THEN "Sábado"
    WHEN enumerador = 7 THEN "Domingo"
    END);

-- SPLIT 
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

-- TESTES FUNCTIONS
SELECT CONVERT_ENUM_SENTIMENTO_STR(1);
SELECT CONVERT_ENUM_REFEICAO_STR(2);
SELECT CONVERT_ENUM_DIA_DA_SEMANA_STR(3);
SELECT SPLIT_STR("DietaJa_test,podeser", "podeser", 1);


CREATE FUNCTION CONVERT_ENUM_SENTIMENTO_STR(   enumerador INT ) RETURNS VARCHAR(255) RETURN  (CASE  WHEN enumerador = 1 THEN "Muito Satisfeito"     WHEN enumerador = 2 THEN "Satisfeito"     WHEN enumerador = 3 THEN "Razoavelmente satisfeito"     WHEN enumerador = 4 THEN "Um pouco insatisfeito"     WHEN enumerador = 5 THEN "Insatisfeito"     WHEN enumerador = 6 THEN "Totalmente insatisfeito"     END)
