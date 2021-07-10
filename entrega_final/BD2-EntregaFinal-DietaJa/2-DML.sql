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
INSERT INTO DietaJa.PorcaoDeAlimento (Nome, Descricao, Ativo, Quantidade, TipoMedida) 
VALUES ("Frango", "Frango Cozido", true, 100, 1);

INSERT INTO DietaJa.PorcaoDeAlimento (Nome, Descricao, Ativo, Quantidade, TipoMedida) 
VALUES ("Folhas Verdes", "Todo vegetal verde escuro pode consumir a vontade", true, 250, 1);

INSERT INTO DietaJa.PorcaoDeAlimento (Nome, Descricao, Ativo, Quantidade, TipoMedida) 
VALUES ("Patinho", "Patinho Bife/Moído", true, 250, 1);

INSERT INTO DietaJa.PorcaoDeAlimento (Nome, Descricao, Ativo, Quantidade, TipoMedida) 
VALUES ("Frutas", "Qualquer fruta vermelha de baixa caloria", true, 100, 1);

INSERT INTO DietaJa.PorcaoDeAlimento (Nome, Descricao, Ativo, Quantidade, TipoMedida) 
VALUES ("Frutas Cítricas", "Consumir o fruto in natura ou batidas/suco", true, 2, 3);

INSERT INTO DietaJa.PorcaoDeAlimento (Nome, Descricao, Ativo, Quantidade, TipoMedida) 
VALUES ("Suco Detox", "Couve + Abacaxi + Limão + Gengibre", true, 180, 2);

INSERT INTO DietaJa.PorcaoDeAlimento (Nome, Descricao, Ativo, Quantidade, TipoMedida) 
VALUES ("Arroz Integral", "Cozido", true, 150, 1);

INSERT INTO DietaJa.PorcaoDeAlimento (Nome, Descricao, Ativo, Quantidade, TipoMedida) 
VALUES ("Frutas", "Qualquer fruta vermelha de baixa caloria", true, 2, 3);

INSERT INTO DietaJa.PorcaoDeAlimento (Nome, Descricao, Ativo, Quantidade, TipoMedida) 
VALUES ("Pão Integral", "Consumir como torradas ou com manteiga", true, 2, 3);

INSERT INTO DietaJa.PorcaoDeAlimento (Nome, Descricao, Ativo, Quantidade, TipoMedida) 
VALUES ("Queijo", "Queijos de baixa caloria (minal frescal)", true, 2, 3);

INSERT INTO DietaJa.PorcaoDeAlimento (Nome, Descricao, Ativo, Quantidade, TipoMedida) 
VALUES ("Iorgute Natural", "Consumir porção, preferível integral", true, 180, 2);

INSERT INTO DietaJa.PorcaoDeAlimento (Nome, Descricao, Ativo, Quantidade, TipoMedida) 
VALUES ("Oleaginosas", "Nuts - castanha do pará, amêndoas", true, 3, 3);

INSERT INTO DietaJa.PorcaoDeAlimento (Nome, Descricao, Ativo, Quantidade, TipoMedida) 
VALUES ("Ovos", "Consumir (mexidos, fritos, omelete)", true, 2, 3);

INSERT INTO DietaJa.PorcaoDeAlimento (Nome, Descricao, Ativo, Quantidade, TipoMedida) 
VALUES ("Legumes e verduras", "Abobrinha, chuchu, espinafre, couve-flor", true, 180, 1);

INSERT INTO DietaJa.PorcaoDeAlimento (Nome, Descricao, Ativo, Quantidade, TipoMedida) 
VALUES ("Quinoa Integral", "Consumir quinoa integral ou arroz integral", true, 100, 1);

-- Cadastra Dieta
-- Cadastrando uma Dieta específica - LowCarb
INSERT INTO DietaJa.Dieta (Nome, Descricao, Ativo) 
VALUES ("Dieta LowCarb", "Dieta para perda de peso / baixo carboidrato", true);

INSERT INTO DietaJa.Dieta (Nome, Descricao, Ativo) 
VALUES ("Dieta Massa Magra", "Dieta para ganho de massa muscular", true);

INSERT INTO DietaJa.Dieta (Nome, Descricao, Ativo) 
VALUES ("Dieta DASH", "Dieta prevenção e tratamento de hipertensão", true);

-- Cadastra PorcaoDeAlimentoDiasDaSemanaDietaRefeicao
-- Associação: Quando o paciente irá consumir aquela determinada porção de alimento da dieta
-- ENUM DiaDaSemana 1 - Segunda, 2 - Terca, 3 - Quarta, 4 - Quinta, 5 - Sexta, 6 - Sabado, 7 - Domingo;
-- ENUM Refeicao: 1 - Café da manhã, 2 - Brunch, 3 - Almoço, 4 - Lanche, 5 - Jantar, 6 - Ceia;
INSERT INTO DietaJa.PorcaoDeAlimentoDiasDaSemanaDietaRefeicao (ID_PorcaoDeAlimento, ID_Dieta, DiaDaSemana, Refeicao) 
VALUES (7, 1, 1, 1);
INSERT INTO DietaJa.PorcaoDeAlimentoDiasDaSemanaDietaRefeicao (ID_PorcaoDeAlimento, ID_Dieta, DiaDaSemana, Refeicao)
VALUES (2, 1, 2, 1);
INSERT INTO DietaJa.PorcaoDeAlimentoDiasDaSemanaDietaRefeicao (ID_PorcaoDeAlimento, ID_Dieta, DiaDaSemana, Refeicao)
VALUES (2, 2, 3, 5);
INSERT INTO DietaJa.PorcaoDeAlimentoDiasDaSemanaDietaRefeicao (ID_PorcaoDeAlimento, ID_Dieta, DiaDaSemana, Refeicao)
VALUES (1, 3, 2, 3);
INSERT INTO DietaJa.PorcaoDeAlimentoDiasDaSemanaDietaRefeicao (ID_PorcaoDeAlimento, ID_Dieta, DiaDaSemana, Refeicao) 
VALUES (5, 2, 1, 4);
INSERT INTO DietaJa.PorcaoDeAlimentoDiasDaSemanaDietaRefeicao (ID_PorcaoDeAlimento, ID_Dieta, DiaDaSemana, Refeicao)
VALUES (3, 2, 5, 1);
INSERT INTO DietaJa.PorcaoDeAlimentoDiasDaSemanaDietaRefeicao (ID_PorcaoDeAlimento, ID_Dieta, DiaDaSemana, Refeicao)
VALUES (2, 2, 3, 4);
INSERT INTO DietaJa.PorcaoDeAlimentoDiasDaSemanaDietaRefeicao (ID_PorcaoDeAlimento, ID_Dieta, DiaDaSemana, Refeicao) 
VALUES (5, 3, 2, 2);

-- Cadastra RegistroDeAtivade
-- ENUM Refeicao: 1 - Café da manhã, 2 - Brunch, 3 - Almoço, 4 - Lanche, 5 - Jantar, 6 - Ceia;
-- ENUM Sentimentos: 1 - Muito Satisfeito, 2 - Satisfeito, 3 - Razoavelmente satisfeito, 
-- 4 - Um pouco insatisfeito, 5 - Insatisfeito, 6 - Totalmente insatisfeito;
-- Paciente Caio Barreta
INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoDeAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 1, 4, "2021-04-21", 1, "Iniciei hoje a dieta, espero dar certo.", 1, 1);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoDeAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 3, 4, "2021-04-21", 2, "Almoço interessante, estou me sentindo bem.", 2, 1);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoDeAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 2, 4, "2021-04-21", 4, "Comi um lanchinho fora da dieta.", 4, 1);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoDeAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 6, 4, "2021-04-21", 5, "Consegui fazer o jantar certinho vamos para a ceia.", 2, 1);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoDeAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 1, 4, "2021-04-21", 6, "Fim do dia, consegui finalizar o dia bem.", 1, 1);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoDeAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 5, 4, "2021-04-22", 3, "Consegui fazer o jantar certinho vamos para a ceia.", 2, 1);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoDeAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 3, 4, "2021-04-22", 2, "Fim do dia, consegui finalizar o dia bem.", 1, 1);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoDeAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 5, 4, "2021-04-22", 3, "Consegui fazer o jantar certinho vamos para a ceia.", 2, 1);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoDeAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 2, 4, "2021-04-23", 2, "Fim do dia, consegui finalizar o dia bem.", 3, 2);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoDeAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 4, 4, "2021-04-25", 3, "Finalizei o jantar e me sinto bem .", 3, 2);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoDeAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 2, 4, "2021-04-26", 1, "Comi alguns ovos e tapioca.", 5, 1);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoDeAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 1, 4, "2021-04-26", 2, "Frutinhas no lanche: morango, banana e chica.", 4, 1);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoDeAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 2, 4, "2021-04-26", 3, "Almoço com folhas e frango grelhado.", 5, 2);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoDeAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 2, 4, "2021-04-27", 4, "Jantar: Frango com Batata.", 3, 3);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoDeAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (1, 1, 4, "2021-04-25", 5, "Inicio o dia com ovos + presunto, comi um doce pós café.", 5, 5);

-- Paciente Gabi Leonel
INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoDeAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (3, 1, 5, "2021-06-01", 1, "Inicio o dia com ovos + presunto, comi um doce pós café.", 5, 5);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoDeAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (3, 2, 5, "2021-06-02", 2, "Almoço com batatas, banana, arroz, frango.", 5, 5);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoDeAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (3, 3, 5, "2021-06-03", 3, "Jantar com algumas folhas + batatas.", 3, 5);

INSERT INTO DietaJa.RegistroDeAtividade (ID_Dieta, ID_PorcaoDeAlimento, ID_Usuario, Registro, 
Refeicao, Comentarios, Sentimento, DiaDaSemana) 
VALUES (3, 4, 5, "2021-06-04", 5, "Ceia com purê de frutas + porcao de Whey.", 5, 5);