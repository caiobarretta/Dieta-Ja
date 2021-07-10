USE DietaJa_test;
SET GLOBAL log_bin_trust_function_creators = 1;

-- ACESSOS
-- USER: DietaJa_test, PASS: DietaJa_test -  full permission - criar pra ter acesso total
-- Abaixo o usuário do banco com total acesso e permissão full para inserir, deletar, alterar e etc.
CREATE USER IF NOT EXISTS 'DietaJa_test'@'localhost' IDENTIFIED BY 'DietaJa_test'
WITH max_queries_per_hour 50
PASSWORD EXPIRE INTERVAL 90 DAY;
GRANT ALL ON *.* TO 'DietaJa_test'@'localhost';
SHOW GRANTS FOR 'DietaJa_test'@'localhost';

-- User Nutri - com full access ao banco DietaJa_test;
CREATE USER IF NOT EXISTS 'nutri'@'localhost' IDENTIFIED BY '12345'
PASSWORD EXPIRE INTERVAL 180 DAY;
GRANT ALL ON DietaJa_test.*TO'nutri'@'localhost';
SHOW GRANTS FOR 'nutri'@'localhost';

-- Usando o REVOKE - no usuário NUTRI
REVOKE ID_Log, ID_Usuario, Nome_Tabela, Operacao_Log, Conteudo_Log
ON DietaJa_test.DietaJa_test_Log FROM 'nutri'@'localhost';