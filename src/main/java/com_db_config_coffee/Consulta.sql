-- SELECT CB.IBAN, CB.SALDO, B.NOMBRE as NOMBRE_BANCO, C.NOMBRE, C.APELLIDOS 
-- FROM BANCOS B, CLIENTES C, CUENTAS_BANCARIAS CB 
-- WHERE B.ID_BANCO = C.ID_BANCO AND C.ID_CLIENTE = CB.ID_CLIENTE;

-- SELECT CB.IBAN, CB.SALDO, B.NOMBRE as NOMBRE_BANCO, C.NOMBRE, C.APELLIDOS 
-- FROM((CLIENTES C INNER JOIN BANCOS B ON B.ID_BANCO = C.ID_BANCO) 
-- INNER JOIN CUENTAS_BANCARIAS CB ON C.ID_CLIENTE = CB.ID_CLIENTE); 