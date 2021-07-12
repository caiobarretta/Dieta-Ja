USE DietaJa;
-- VIEWS

-- Base para Relatórios espelhados no sistema; 
-- Views que interagem entre Sentimento, Refeição, Dia da semana e Dieta.
DROP VIEW IF EXISTS SentimentoRefeicaoDiaDaSemanaDieta;
CREATE VIEW SentimentoRefeicaoDiaDaSemanaDieta AS
SELECT  usr.Nome AS "Usuário", ra.Registro, dt.Nome,
CONVERT_ENUM_SENTIMENTO_STR(ra.Sentimento) AS "Sentimento",
CONVERT_ENUM_REFEICAO_STR(ra.Refeicao) AS "Refeição",
CONVERT_ENUM_DIA_DA_SEMANA_STR(ra.DiaDaSemana) AS "DiaDaSemana"
FROM DietaJa.RegistroDeAtividade AS ra
JOIN RegistroDeAtividade tb2 ON ra.ID_RegistroDeAtividade = tb2.ID_RegistroDeAtividade
INNER JOIN Dieta dt ON ra.ID_Dieta = dt.ID_Dieta
INNER JOIN DietaJa.Usuario AS usr ON ra.ID_Usuario = usr.ID_Usuario;

-- Consulta quais usuários estão ativos ou inativos na plataforma;
DROP VIEW IF EXISTS AtivoInativo;
CREATE VIEW AtivoInativo AS
SELECT tab1.Nome,
CASE
	WHEN tab1.Ativo = true THEN "Ativo"
    WHEN tab1.Ativo = false THEN "Inativo"
END AS "Ativo/Inativo"
FROM DietaJa.Usuario tab1
ORDER BY Nome;

