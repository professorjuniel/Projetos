﻿select descricao as produto, codigoncm as ncm, 
CASE unidadedevenda
WHEN 0 THEN 'KG'
WHEN 1 THEN 'L'
WHEN 2 THEN 'UN'
WHEN 3 THEN 'CX'
WHEN 4 THEN 'M2'
WHEN 5 THEN 'M3'
WHEN 6 THEN 'PR'
WHEN 7 THEN 'T'
WHEN 8 THEN 'QUIL'
WHEN 9 THEN 'BR'
WHEN 10 THEN 'CNT'
WHEN 12 THEN 'EST'
WHEN 13 THEN 'FRA'
WHEN 14 THEN 'FRD'
WHEN 15 THEN 'GL'
WHEN 16 THEN 'JG'
WHEN 17 THEN 'AM'
WHEN 18 THEN 'BD'
WHEN 19 THEN 'BIS'
WHEN 20 THEN 'CTE'
WHEN 21 THEN 'MÇ'
WHEN 22 THEN 'PAST'
WHEN 23 THEN 'PCT'
WHEN 24 THEN 'POTE'
WHEN 25 THEN 'RO'
WHEN 26 THEN 'SER'
WHEN 27 THEN 'VAR'
WHEN 28 THEN 'KIT'
ELSE 'Sem Unidade'
END as unidade, valordevenda	
from produto;