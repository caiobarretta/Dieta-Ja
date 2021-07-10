USE DietaJa;
-- DQL - Consultas básicas e complexas

-- Consulta registro de atividades de pacientes no 2 TRIM/2021.
SELECT Registro, Comentarios, CONVERT_ENUM_SENTIMENTO_STR(Sentimento) AS "Sentimento"
FROM DietaJa.RegistroDeAtividade
WHERE ID_Usuario
	IN (SELECT ID_Usuario FROM DietaJa.Usuario as usr WHERE TipoUsuario = 3)
AND Registro 
	BETWEEN "2021-04-01" AND "2021-06-30";

-- Filtro para tela listagem de porções de alimento x dia da semana x refeições da tela principal
SELECT porc.Nome, 
CONVERT_ENUM_DIA_DA_SEMANA_STR(DiaDaSemana) AS "Dia da Semana", 
CONVERT_ENUM_REFEICAO_STR(Refeicao) AS "Refeição"
FROM PorcaoDeAlimentoDiasDaSemanaDietaRefeicao AS padsr
INNER JOIN PorcaoDeAlimento AS porc ON padsr.ID_PorcaoDeAlimento = porc.ID_PorcaoDeAlimento
INNER JOIN Dieta AS dt ON padsr.ID_Dieta = dt.ID_Dieta
WHERE dt.ID_Dieta = 1 AND porc.Ativo = true;

-- Filtro com Distinct para listagem de Porções de Alimento no Registro de Atividades;
SELECT DISTINCT porc.Nome
FROM PorcaoDeAlimentoDiasDaSemanaDietaRefeicao AS padsr
INNER JOIN PorcaoDeAlimento AS porc ON padsr.ID_PorcaoDeAlimento = porc.ID_PorcaoDeAlimento
INNER JOIN Dieta AS dt ON padsr.ID_Dieta = dt.ID_Dieta
WHERE dt.ID_Dieta = 1 AND porc.Ativo = true
GROUP BY porc.Nome;