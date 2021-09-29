USE DietaJa;
SET GLOBAL log_bin_trust_function_creators = 1;

-- ACESSOS
-- USER: dietaja, PASS: dietaja -  full permission - criar pra ter acesso total
-- Abaixo o usuário do banco com total acesso e permissão full para inserir, deletar, alterar e etc.
CREATE USER IF NOT EXISTS 'dietaja'@'localhost' IDENTIFIED BY 'dietaja';
GRANT ALL ON *.* TO 'dietaja'@'localhost';
SHOW GRANTS FOR 'dietaja'@'localhost';

-- User Nutri - com full access ao banco DietaJa;
CREATE USER IF NOT EXISTS 'nutri'@'localhost' IDENTIFIED BY '12345'
PASSWORD EXPIRE INTERVAL 180 DAY;
GRANT SELECT, INSERT, UPDATE, DELETE ON *.* TO 'nutri'@'localhost';
SHOW GRANTS FOR 'nutri'@'localhost';

-- User Teste
CREATE USER IF NOT EXISTS 'teste'@'localhost' IDENTIFIED BY '%ˆ&*'
PASSWORD EXPIRE INTERVAL 10 DAY;
GRANT SELECT, UPDATE, INSERT ON DietaJa.* TO 'teste'@'localhost';
SHOW GRANTS FOR 'teste'@'localhost';

-- Usando o REVOKE - no usuário TESTE
REVOKE INSERT, UPDATE
ON DietaJa.*
FROM 'teste'@'localhost';
SHOW GRANTS FOR 'teste'@'localhost';

-- DROP user
DROP USER 'teste'@'localhost';
-- DROP USER 'nutri'@'localhost';
-- DROP USER 'dietaja'@'localhost';