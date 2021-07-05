-- DQL - DietaJá;
-- Consultas (Op Lógicos, Between, Like), Join, Subconsultas, Group By,
-- funções agregação e Views.

USE DietaJa;

-- CONSULTAS
-- Consulta registro de atividades de pacientes no 2 TRIM/2021.
SELECT * FROM DietaJa.RegistroDeAtividade AS ra WHERE ID_Usuario IN
(SELECT ID_Usuario FROM DietaJa.Usuario as usr WHERE TipoUsuario = 3) 
AND Registro BETWEEN "2021-04-01" AND "2021-06-30";

-- Consulta Porção de Alimento e sua descrição correlacionando ao tipo de dieta e a descrição da dieta
SELECT porc.Nome AS "Porção", porc.Descricao, dieta.Nome AS "Dieta", dieta.Descricao AS "Descricao Dieta"
FROM PorcaoDeAlimento AS porc
INNER JOIN PorcaoDeAlimentoDieta AS porcDieta 
	ON porc.ID_PorcaoAlimento = porcDieta.ID_PorcaoDeAlimentoDieta
INNER JOIN Dieta AS dieta ON porcDieta.ID_Dieta = dieta.ID_Dieta;

-- Consulta Porção de Alimento para o Paciente x Dieta x PorcaoDeAlimentoDiasDaSemana;
SELECT dieta.Nome AS "Dieta", porc.Nome AS "Porção", 
CASE
	WHEN porcDias.DiaDaSemana = 1 THEN "Segunda-Feira"
    WHEN porcDias.DiaDaSemana = 2 THEN "Terça-Feira"
    WHEN porcDias.DiaDaSemana = 3 THEN "Quarta-Feira"
    WHEN porcDias.DiaDaSemana = 4 THEN "Quinta-Feira"
    WHEN porcDias.DiaDaSemana = 5 THEN "Sexta-Feira"
    WHEN porcDias.DiaDaSemana = 6 THEN "Sábado"
    WHEN porcDias.DiaDaSemana = 7 THEN "Domingo"
END AS "Dia da Semana"
FROM PorcaoDeAlimento AS porc
INNER JOIN PorcaoDeAlimentoDieta AS porcDieta 
	ON porc.ID_PorcaoAlimento = porcDieta.ID_PorcaoDeAlimentoDieta
INNER JOIN Dieta AS dieta ON porcDieta.ID_Dieta = dieta.ID_Dieta
INNER JOIN PorcaoDeAlimentoDiasDaSemana as porcDias
	ON porc.ID_PorcaoAlimento = porcDias.ID_PorcaoAlimento;

-- Consulta quais usuários estão ativos ou inativos na plataforma;
SELECT tab1.Nome,
CASE
	WHEN tab1.Ativo = true THEN "Ativo"
    WHEN tab1.Ativo = false THEN "Inativo"
    WHEN tab2.Ativo = true THEN "Ativo"
    WHEN tab2.Ativo = false THEN "Inativo"
END AS "Ativo/Inativo"
FROM DietaJa.Usuario tab1
JOIN DietaJa.Usuario tab2 ON tab1.ID_Usuario = tab2.ID_Usuario
ORDER BY Nome;

-- Consulta relação entre Sentimento e a Refeição sendo realizada pelo usuário
SELECT 
CASE
	WHEN ra.Sentimento = 1 THEN "Muito Satisfeito"
    WHEN ra.Sentimento = 2 THEN "Satisfeito"
    WHEN ra.Sentimento = 3 THEN "Razoavelmente satisfeito"
    WHEN ra.Sentimento = 4 THEN "Um pouco insatisfeito"
    WHEN ra.Sentimento = 5 THEN "Insatisfeito"
    WHEN ra.Sentimento = 6 THEN "Totalmente insatisfeito"
END AS "Sentimento",
CASE
	WHEN ra.Refeicao = 1 THEN "Café da manhã"
    WHEN ra.Refeicao = 2 THEN "Brunch"
    WHEN ra.Refeicao = 3 THEN "Almoço"
    WHEN ra.Refeicao = 4 THEN "Lanche"
    WHEN ra.Refeicao = 5 THEN "Jantar"
    WHEN ra.Refeicao = 6 THEN "Ceia"
END AS "Refeição"
FROM DietaJa.RegistroDeAtividade ra
JOIN DietaJa.Usuario usr ON ra.ID_Usuario = usr.ID_Usuario
GROUP BY Refeicao, Sentimento;

-- VIEWS
-- VIEW de registros de atividades dos usuários:
CREATE VIEW view_registroDeAtividadesDosUsuarios AS
SELECT usr.Nome AS "Usuário", dieta.Nome AS "Dieta", porc.Nome, registro.comentarios AS "Comentários",
CASE
	WHEN registro.DiaDaSemana = 1 THEN "Segunda-Feira"
    WHEN registro.DiaDaSemana = 2 THEN "Terça-Feira"
    WHEN registro.DiaDaSemana = 3 THEN "Quarta-Feira"
    WHEN registro.DiaDaSemana = 4 THEN "Quinta-Feira"
    WHEN registro.DiaDaSemana = 5 THEN "Sexta-Feira"
    WHEN registro.DiaDaSemana = 6 THEN "Sábado"
    WHEN registro.DiaDaSemana = 7 THEN "Domingo"
END AS "Dia da Semana",
CASE
	WHEN registro.Sentimento = 1 THEN "Muito Satisfeito"
    WHEN registro.Sentimento = 2 THEN "Satisfeito"
    WHEN registro.Sentimento = 3 THEN "Razoavelmente satisfeito"
    WHEN registro.Sentimento = 4 THEN "Um pouco insatisfeito"
    WHEN registro.Sentimento = 5 THEN "Insatisfeito"
    WHEN registro.Sentimento = 6 THEN "Totalmente insatisfeito"
END AS "Sentimento",
CASE
	WHEN registro.Refeicao = 1 THEN "Café da manhã"
    WHEN registro.Refeicao = 2 THEN "Brunch"
    WHEN registro.Refeicao = 3 THEN "Almoço"
    WHEN registro.Refeicao = 4 THEN "Lanche"
    WHEN registro.Refeicao = 5 THEN "Jantar"
    WHEN registro.Refeicao = 6 THEN "Ceia"
END AS "Refeição"
FROM DietaJa.RegistroDeAtividade AS registro
INNER JOIN DietaJa.Dieta AS dieta ON registro.ID_Dieta = dieta.ID_Dieta
INNER JOIN DietaJa.PorcaoDeAlimento as porc ON registro.ID_PorcaoAlimento = porc.ID_PorcaoAlimento
INNER JOIN DietaJa.Usuario as usr ON registro.ID_Usuario = usr.ID_Usuario;

-- VIEW - Usuários ativos e com dieta em execução
CREATE VIEW view_usuariosAtivosComDietaEComentarios AS
SELECT usr.Nome AS "Usuário", diet.Nome AS "Dieta", registro.Comentarios AS "Comentários"
FROM DietaJa.RegistroDeAtividade AS registro
INNER JOIN DietaJa.Usuario AS usr ON registro.ID_Usuario = usr.ID_Usuario
INNER JOIN DietaJa.Dieta AS diet ON registro.ID_Dieta = diet.ID_Dieta
WHERE usr.Ativo = true;