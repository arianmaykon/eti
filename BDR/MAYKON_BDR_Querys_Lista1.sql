-- CONSULTAS LISTA 1

 
-- 1. Selecione os empregados que trabalham no departamento D5 e ganham mais do que R$5.000. 
SELECT
    emp.*
FROM
	empregado emp
	INNER JOIN departamento dept ON emp.dept = dept.dnum
WHERE 
	emp.salario > 5000;

--------------------------------------------------------------------------------
-- 2. Liste os nomes dos supervisores dos empregados que ganham mais do que R$5.000.
SELECT
    supervisores.enome SUPERVISOR
FROM
	empregado supervisores
	INNER JOIN empregado emp ON supervisores.cpf = emp.superv
WHERE 
	supervisores.salario > 5000;

--------------------------------------------------------------------------------
-- 3. Liste o nome e salário de todos os empregados que ganham mais que o seu supervisor.  
SELECT
    emp.enome   EMPREGADO_NOME,
	emp.salario EMPREGADO_SALARIO
FROM
	empregado emp
	INNER JOIN empregado sup ON emp.cpf = sup.superv AND emp.salario > sup.salario;

--------------------------------------------------------------------------------
-- 4. Obtenha o nome do gerente de cada projeto. 
SELECT
	ger.enome GERENTE,
	proj.pnome PROJETO
FROM
	empregado ger
	INNER JOIN departamento dep ON ger.cpf = dep.gerente
	INNER JOIN projeto proj ON dep.dnum = proj.numdept;

--------------------------------------------------------------------------------
-- 5.  Liste  os  nomes  dos  projetos  que  têm  um  empregado  chamado  "JOAO  SILVA"  que  trabalha  no projeto ou gerencia o departamento que controla o projeto.  
SELECT 
	proj.pnome PROJETO,
	ger.enome NOME --GERENTE 
FROM
	projeto proj 
	INNER JOIN departamento dep ON proj.numdept = dep.dnum
	INNER JOIN empregado ger ON ger.cpf = dep.gerente 
WHERE 
	ger.enome = 'JOAO SILVA'

UNION

SELECT 
	proj.pnome PROJETO,
	emp.enome NOME --EMPREGADO 
FROM
	projeto proj 
	INNER JOIN trabalha_no workon ON proj.pnum = workon.proj
	INNER JOIN empregado emp ON workon.emp = emp.cpf
WHERE 
	emp.enome = 'JOAO SILVA';

--------------------------------------------------------------------------------
-- 6. CPF dos empregados que não trabalham em nenhum projeto
SELECT 
	emp.cpf
FROM
	empregado emp
WHERE 
	emp.cpf NOT IN (SELECT workon.emp FROM trabalha_no workon);

--------------------------------------------------------------------------------
-- 7. CPF dos empregados que trabalham em pelo menos um projeto.
SELECT 
	emp.cpf
FROM
	empregado emp
WHERE 
	emp.cpf IN (SELECT workon.emp FROM trabalha_no workon);

--------------------------------------------------------------------------------
-- 8. CPF dos empregados que trabalham em todos os projetos.
SELECT 
	emp.cpf
FROM 
	empregado emp
WHERE
	NOT EXISTS (
		SELECT proj.pnum
		FROM projeto proj
		WHERE proj.pnum NOT IN (
			SELECT workon.proj 
			FROM trabalha_no workon
			WHERE workon.emp = emp.cpf
		)
	);

--------------------------------------------------------------------------------
-- 9. Liste os nome e salário de  todos os empregados e no caso de ser um gerente,  liste o departamento que gerencia.
SELECT 
	emp.enome NOME,
	emp.salario,
	dep.dnome DEPARTAMENTO
FROM
	empregado emp
	LEFT JOIN departamento dep ON emp.cpf = dep.gerente;

--------------------------------------------------------------------------------
-- 10. Liste o nome dos empregados no departamento 'TRANSPORTE' que tem o maior salário.
SELECT 
	emp.enome NOME 
FROM
	empregado emp
	LEFT JOIN departamento dep ON emp.dept = dep.dnum 
WHERE 
	dep.dnome = 'TRANSPORTE' AND 
	emp.salario >= (SELECT AVG(emp2.salario) FROM empregado emp2 WHERE emp2.dept = dep.dnum);

--------------------------------------------------------------------------------
-- 11. Qual é a média de salário dos empregados no departamento 'VENDAS'?
SELECT 
	AVG(emp.salario) MEDIA
FROM
	empregado emp
	LEFT JOIN departamento dep ON emp.cpf = dep.gerente 
WHERE 
	dep.dnome = 'VENDAS';

--------------------------------------------------------------------------------
-- 12. Para cada empregado obtenha o número de projetos que ele trabalha e o total de horas que trabalha nestes projetos.
SELECT 
	emp.enome EMPREGADO, 
	COUNT(proj.pnum) QTD_PROJETOS, 
	SUM(workon.horas) HORAS 
FROM
	empregado emp
	INNER JOIN trabalha_no workon ON emp.cpf = workon.emp 
	INNER JOIN projeto proj ON workon.proj = proj.pnum
GROUP BY 
	emp.enome;

--------------------------------------------------------------------------------
-- 13. Quantos empregados trabalham em mais de um projeto?
SELECT 
	emp.enome EMPREGADO, 
	COUNT(proj.pnum) QTD_PROJETOS
FROM
	empregado emp
	INNER JOIN trabalha_no workon ON emp.cpf = workon.emp 
	INNER JOIN projeto proj ON workon.proj = proj.pnum
GROUP BY 
	emp.enome
HAVING 
	COUNT(proj.pnum) > 1;

--------------------------------------------------------------------------------
-- 14.  Para  cada  projeto  obtenha  o  número  de  empregados  que  trabalha  no  projeto  e  o  total  de  horas alocada para o projeto.
SELECT 
	proj.pnome PROJETO, 
	COUNT(workon.proj) QTD_EMPREGADOS,
	SUM(workon.horas) QTD_HORAS
FROM
	projeto proj 
	INNER JOIN trabalha_no workon ON proj.pnum = workon.proj 
GROUP BY 
	proj.pnome;

--------------------------------------------------------------------------------
-- 15.  Para cada projeto que tem mais de 3 empregados alocados, obtenha a média de horas que os empregados trabalham no projeto.
SELECT 
	proj.pnome PROJETO, 
	COUNT(workon.proj) QTD_EMPREGADOS,
	AVG(workon.horas) MEDIA_HORAS
FROM
	projeto proj 
	INNER JOIN trabalha_no workon ON proj.pnum = workon.proj 
GROUP BY 
	proj.pnome
HAVING 
	COUNT(workon.proj) >= 3;

--------------------------------------------------------------------------------
-- 16. Obtenha os projetos que tem mais empregados do que a média dos empregados de todos os projetos e o total de horas alocadas maior do que a média de horas de todos os projetos.


--------------------------------------------------------------------------------
