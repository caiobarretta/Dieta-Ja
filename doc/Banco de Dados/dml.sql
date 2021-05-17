-- DML - DietaJá;

USE DietaJa;
-- Cria Usuários - 1 Administrador, 1 - Nutricionista, 3 - Pacientes:
-- DISCLAIMER - Usuário Ativo 1 - Não Ativo 0;
-- ENUM TipoUsuario: 1 - Administrador, 2 - Nutricionista, 3 - Paciente;
INSERT INTO DietaJa.Usuario (Login, Senha, TipoUsuario, Descricao, Nome, Ativo) 
VALUES ("nutri", "12345", 2, "Nutricionista do IFSP", "NutricionistaIFSP", true);

INSERT INTO DietaJa.Usuario (Login, Senha, TipoUsuario, Descricao, Nome, Ativo) 
VALUES ("admin", "@1234", 1, "Admin do DietaJá", "Administrador do Sistema", true);

INSERT INTO DietaJa.Usuario (Login, Senha, TipoUsuario, Descricao, Nome, Ativo) 
VALUES ("mizial", "6789", 3, "Mízia Lima, paciente do Nutricionista do IFSP", "Mízia Lima", false);

INSERT INTO DietaJa.Usuario (Login, Senha, TipoUsuario, Descricao, Nome, Ativo) 
VALUES ("caiob", "@@23", 3, "Caio Barreta, paciente do Nutricionista do IFSP", "Caio Barreta", true);

INSERT INTO DietaJa.Usuario (Login, Senha, TipoUsuario, Descricao, Nome, Ativo) 
VALUES ("gabileo", "@@45", 3, "Gabi Leonel, paciente do Nutricionista do IFSP", "Gabi Leonel", true);

-- Cadastra PorcaoDeAlimento - Quando a nutricionista cadastra as porcoes de alimento, essa informacao
-- é única, uma vez que esse campo é somente pra insercao do alimento no banco, exemplo abaixo,
-- estamos no inserindo no banco alimentos porcionados para uma dieta low-carb:
INSERT INTO DietaJa.PorcaoDeAlimento (Nome, Descricao, Ativo) 
VALUES ("Frango - LowCarb", "100G Frango Cozido", true);

INSERT INTO DietaJa.PorcaoDeAlimento (Nome, Descricao, Ativo) 
VALUES ("Folhas Verdes", "Todo vegetal verde escuro pode consumir a vontade", true);

INSERT INTO DietaJa.PorcaoDeAlimento (Nome, Descricao, Ativo) 
VALUES ("Patinho - LowCarb", "100G Patinho Bife/Moído", true);

INSERT INTO DietaJa.PorcaoDeAlimento (Nome, Descricao, Ativo) 
VALUES ("Frutas", "Qualquer fruta vermelha de baixa caloria", true);

INSERT INTO DietaJa.PorcaoDeAlimento (Nome, Descricao, Ativo) 
VALUES ("Frutas Cítricas - LowCarb", "Consumir o fruto in natura ou batidas/suco", true);

INSERT INTO DietaJa.PorcaoDeAlimento (Nome, Descricao, Ativo) 
VALUES ("Suco Detox - LowCarb", "Couve + Abacaxi + Limão + Gengibre", true);

INSERT INTO DietaJa.PorcaoDeAlimento (Nome, Descricao, Ativo) 
VALUES ("Arroz Integral - LowCarb", "Consumir 100G arroz integral", true);

-- Cadastra Dieta
-- Cadastrando uma Dieta específica - LowCarb
INSERT INTO DietaJa.Dieta (Nome, Descricao, Ativo) 
VALUES ("Dieta LowCarb", "Dieta para perda de peso / baixo carboidrato", true);

-- Cadastra PorcaoDeAlimentoDieta
-- Associacao Dieta x PorcaoDeAlimento
INSERT INTO DietaJa.PorcaoDeAlimentoDieta (ID_PorcaoAlimento, ID_Dieta) VALUES (3, 1);
INSERT INTO DietaJa.PorcaoDeAlimentoDieta (ID_PorcaoAlimento, ID_Dieta) VALUES (2, 1);
INSERT INTO DietaJa.PorcaoDeAlimentoDieta (ID_PorcaoAlimento, ID_Dieta) VALUES (7, 1);
INSERT INTO DietaJa.PorcaoDeAlimentoDieta (ID_PorcaoAlimento, ID_Dieta)  VALUES (1, 1);

-- Cadastra PorcaoDeAlimentoDiasDaSemana
-- Associação: Quando o paciente irá consumir aquela determinada porção de alimento da dieta
-- ENUM DiaDaSemana 1 - Segunda, 2 - Terca, 3 - Quarta, 4 - Quinta, 5 - Sexta, 6 - Sabado, 7 - Domingo;
INSERT INTO DietaJa.PorcaoDeAlimentoDiasDaSemana (ID_PorcaoAlimento, DiaDaSemana) 
VALUES (7, 1);
INSERT INTO DietaJa.PorcaoDeAlimentoDiasDaSemana (ID_PorcaoAlimento, DiaDaSemana) 
VALUES (2, 1);
INSERT INTO DietaJa.PorcaoDeAlimentoDiasDaSemana (ID_PorcaoAlimento, DiaDaSemana) 
VALUES (2, 2);
INSERT INTO DietaJa.PorcaoDeAlimentoDiasDaSemana (ID_PorcaoAlimento, DiaDaSemana) 
VALUES (1, 4);

-- Cadastra RegistroDeAtivade
-- ENUM Refeicao: 1 - Café da manhã, 2 - Brunch, 3 - Almoço, 4 - Lanche, 5 - Jantar, 6 - Ceia;
-- ENUM Sentimentos: 1 - Muito Satisfeito, 2 - Satisfeito, 3 - Razoavelmente satisfeito, 
-- 4 - Um pouco insatisfeito, 5 - Insatisfeito, 6 - Totalmente insatisfeito;
INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 1, 4, "2021-04-21", 1, "Iniciei hoje a dieta, espero dar certo.", 1, 1);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 3, 4, "2021-04-21", 2, "Almoço interessante, estou me sentindo bem.", 2, 1);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 2, 4, "2021-04-21", 4, "Comi um lanchinho fora da dieta.", 4, 1);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 6, 4, "2021-04-21", 5, "Consegui fazer o jantar certinho vamos para a ceia.", 2, 1);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 1, 4, "2021-04-21", 6, "Fim do dia, consegui finalizar o dia bem.", 1, 1);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 5, 4, "2021-04-22", 3, "Consegui fazer o jantar certinho vamos para a ceia.", 2, 1);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 3, 4, "2021-04-22", 2, "Fim do dia, consegui finalizar o dia bem.", 1, 1);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 5, 4, "2021-04-22", 3, "Consegui fazer o jantar certinho vamos para a ceia.", 2, 1);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 2, 4, "2021-04-23", 2, "Fim do dia, consegui finalizar o dia bem.", 3, 2);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 4, 4, "2021-04-25", 3, "Finalizei o jantar e me sinto bem .", 3, 2);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 1, 4, "2021-04-25", 2, "Estou tentando finalizar o processo corretamente.", 5, 2);

-- Construir mais casos de teste para popular o BD