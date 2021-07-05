USE DietaJa;
SET GLOBAL log_bin_trust_function_creators = 1;

-- ACESSOS
-- USER: dietaja, PASS: dietaja -  full permission - criar pra ter acesso total
-- Abaixo o usuário do banco com total acesso e permissão full para inserir, deletar, alterar e etc.
CREATE USER IF NOT EXISTS 'dietaja'@'localhost' IDENTIFIED BY 'dietaja'
WITH max_queries_per_hour 50
PASSWORD EXPIRE INTERVAL 90 DAY;
GRANT ALL ON *.* TO 'dietaja'@'localhost';
SHOW GRANTS FOR 'dietaja'@'localhost';

-- User Nutri - com full access ao banco DietaJa;
CREATE USER IF NOT EXISTS 'nutri'@'localhost' IDENTIFIED BY '12345'
PASSWORD EXPIRE INTERVAL 180 DAY;
GRANT ALL ON dietaJa.*TO'nutri'@'localhost';
SHOW GRANTS FOR 'nutri'@'localhost';

-- Usando o REVOKE - no usuário NUTRI
REVOKE ID_Log, ID_Usuario, Nome_Tabela, Operacao_Log, Conteudo_Log
ON DietaJa.DietaJa_Log FROM 'nutri'@'localhost';