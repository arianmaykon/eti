select e."ENome", d."DNome", e."Salario", e."Salario"*1.15 as Novo_Sal
from eempresa.empregado e, eempresa.departamento d
where e."CDep" = d."Codigo"


select * 
from eempresa.departamento
where "DNome" like '%e%'


(select e."CDep"
from eempresa.empregado e)
EXCEPT
(select "Codigo" 
from eempresa.departamento)

(select "Codigo" 
from eempresa.departamento)
UNION
(select e."CDep"
from eempresa.empregado e)


select e."CDep"
from eempresa.empregado e
order by e."CDep" desc

select count(distinct "Salario")
from eempresa.empregado e

select "CDep", count(*) as qtd_emp
from eempresa.empregado
where "Sexo" = 'F'
group by "CDep"
having count(*) > 1
order by "CDep"

select * 
from eempresa.empregado


select "Salario"
from eempresa.empregado e
order by "Salario"

where "Salario">10000 and "Salario"<20000


select d."DNome", max(e."Salario") as max_sal, min(e."Salario") as min_sal
from eempresa.empregado e, eempresa.departamento d
where e."CDep" = d."Codigo"
group by d."DNome"

select d."DNome", tab.max_sal, tab.min_sal
from (
	select e."CDep", max(e."Salario") as max_sal, min(e."Salario") as min_sal
	from eempresa.empregado e
	group by e."CDep"
	) as tab, eempresa.departamento d
where tab."CDep" = d."Codigo"


select e."CDep" 
from eempresa.empregado e
where e."CDep" is not null

select e."ENome"
from eempresa.empregado e
where e."Salario" >= all (
			select "Salario"
			from eempresa.empregado
			)

select e."ENome"
from eempresa.empregado e
where e."Salario" =  (
			select max("Salario")
			from eempresa.empregado
			)

select * 
from eempresa.dunidade

select d."DNome", (select count(*) from eempresa.dunidade du where du."DCodigo" = d."Codigo")as qtd_unidade
from eempresa.departamento d


select d."DNome", count(*) as qtd_unidade
from eempresa.departamento d, eempresa.dunidade du
where d."Codigo" ^= du."DCodigo"
group by d."DNome"