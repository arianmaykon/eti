-- CONSULTAS LISTA 2

CREATE LANGUAGE 'PLPGSQL';


-- 2.1. Crie uma stored procedure que receba como parâmetro de entrada três parâmetros a matrícula do empregado(int), o ano(int) e o mês(int). Este procedimento deve inserir uma tupla no banco de horas referente ao empregado e período em questão.
create or replace function inserir_banco_horas(empregado integer, ano integer, mes integer)
returns integer as
$body$
	declare
		vLinhas Integer default 0;
	begin
		insert into banco_horas(emp_matricula, ban_ano, ban_mes, ban_total_horas)
		values (empregado, ano, mes, 0);
		get diagnostics vLinhas = row_count;
		return vLinhas;
	end;
$body$
language 'plpgsql';

--------------------------------------------------------------------------------
-- 2.2. Crie uma stored procedure que receba como parâmetro de entrada dois parâmetros o ano(int) e o mês (int). Este procedimento deve inserir uma tupla no banco de horas para todos os empregados referente período em questão.
CREATE OR REPLACE FUNCTION CRIAR_BANCO_HORAS_POR_PERIODO(ano INTEGER, mes INTEGER)
RETURNS BOOLEAN AS
$BODY$
	DECLARE
		empregado empregado.emp_matricula%TYPE;
	BEGIN
		FOR empregado IN SELECT emp_matricula FROM empregado LOOP
			INSERT INTO banco_horas(emp_matricula, ban_ano, ban_mes, ban_total_horas)
			VALUES (empregado, ano, mes, 0);
		END LOOP;

		RETURN FOUND;
	END;
$BODY$
LANGUAGE 'PLPGSQL';

--------------------------------------------------------------------------------
-- 2.3. Crie uma stored procedure que receba como parâmetro de entrada dois parâmetros o ano(int) e o mês(int). Este procedimento deve retornar a matrícula do empregado com maior número de horas no banco de horas.
CREATE OR REPLACE FUNCTION get_empregado_maior_banco_horas(ano INTEGER, mes INTEGER)
RETURNS banco_horas.emp_matricula%TYPE AS
$BODY$
	DECLARE
		banco banco_horas%ROWTYPE;
		maior_banco_horas banco_horas.ban_total_horas%TYPE DEFAULT 0;
		matricula banco_horas.emp_matricula%TYPE DEFAULT NULL;
	BEGIN
		FOR banco IN
			SELECT * FROM banco_horas WHERE ban_ano = ano AND ban_mes = mes
		LOOP
			IF banco.ban_total_horas > maior_banco_horas THEN
				maior_banco_horas := banco.ban_total_horas;
				matricula := banco.emp_matricula;
			END IF;
		END LOOP;

		RETURN matricula;
	END;
$BODY$
LANGUAGE 'PLPGSQL';

--------------------------------------------------------------------------------
-- 2.4. Crie uma stored procedure que receba como parâmetro de entrada quatro parâmetros a matrícula do empregado(int), o ano(int), o mês(int) e o último dia do mês(int). Este procedimento deve inserir um conjunto de tuplas na relação freqüência referentes ao empregado e período em questão.
CREATE OR REPLACE FUNCTION criar_frequencia(matricula INTEGER, ano INTEGER, mes INTEGER, ultimo_dia_mes INTEGER)
RETURNS INTEGER AS
$BODY$
	DECLARE
		data TEXT;
	BEGIN
		FOR dia IN 1 .. ultimo_dia_mes LOOP
			data := dia || '/' ||  mes || '/' || ano;
			INSERT INTO frequencia(freq_data, emp_matricula)
			VALUES(to_date(data, 'DD/MM/YYYY'), matricula);
		END LOOP;

		RETURN 1;
	END;
$BODY$
LANGUAGE 'PLPGSQL';

--------------------------------------------------------------------------------
-- 2.5. Crie uma stored procedure que receba como parâmetro de entrada três parâmetros a matrícula do empregado(int), o ano(int) e o mês(int). Este procedimento deve atualizar o valor do banco de horas neste mês para o empregado em questão.
CREATE OR REPLACE FUNCTION atualiza_banco_horas(matricula INTEGER, ano INTEGER, mes INTEGER)
RETURNS INTEGER AS
$BODY$
	DECLARE
		qtd_horas INTEGER;
		mes_ano TEXT;
	BEGIN
		IF mes < 10 THEN
			mes_ano = '0' || mes || '/' || ano;
		ELSE
			mes_ano = mes || '/' || ano;
		END IF;

		SELECT INTO qtd_horas
			(date_part('epoch', freq_hora_saida::time - freq_hora_entrada::time)
			/ 3600
			+ freq_horas_excedentes
			+ freq_horas_noturnas)
		FROM frequencia
		WHERE emp_matricula = matricula AND to_char(freq_data, 'MM/YYYY') = mes_ano;

		IF NOT FOUND THEN
		    qtd_horas := 0;
		END IF;

		UPDATE banco_horas
		SET ban_total_horas = qtd_horas
		WHERE emp_matricula = matricula AND BAN_ANO = ano AND BAN_MES = mes;

		RETURN qtd_horas;
	END;
$BODY$
LANGUAGE 'PLPGSQL';

--------------------------------------------------------------------------------
-- 2.6. Crie uma stored procedure que receba como parâmetro de entrada dois parâmetros o ano(int) e o mês (int). Este procedimento deve atualizar o valor do banco de horas neste mês para todos os funcionários.
CREATE OR REPLACE FUNCTION atualiza_banco_horas(ano INTEGER, mes INTEGER)
RETURNS INTEGER AS
$BODY$
	DECLARE
		qtd_horas INTEGER;
		mes_ano TEXT;
		matricula empregado.emp_matricula%TYPE;
	BEGIN
		FOR matricula IN
			SELECT emp_matricula FROM empregado
		LOOP	
			IF mes < 10 THEN
				mes_ano = '0' || mes || '/' || ano;
			ELSE
				mes_ano = mes || '/' || ano;
			END IF;

			SELECT INTO qtd_horas
				(date_part('epoch', freq_hora_saida::time - freq_hora_entrada::time)
				/ 3600
				+ freq_horas_excedentes
				+ freq_horas_noturnas)
			FROM frequencia
			WHERE emp_matricula = matricula AND to_char(freq_data, 'MM/YYYY') = mes_ano;

			IF NOT FOUND THEN
			    qtd_horas := 0;
			END IF;

			UPDATE banco_horas
			SET ban_total_horas = qtd_horas
			WHERE emp_matricula = matricula AND BAN_ANO = ano AND BAN_MES = mes;
		END LOOP;

		RETURN 1;
	END;
$BODY$
LANGUAGE 'PLPGSQL';

--------------------------------------------------------------------------------
-- 2.7. Crie um trigger sobre a relação período com a seguinte finalidade: Quando um período for inserido deve-se inserir também, para todos os empregados, na relação freqüência, uma tupla para cada dia no período em questão. Além disso, deve-se inserir também uma tupla para cada empregado no banco de horas.
CREATE OR REPLACE FUNCTION prd_criar_periodo()
RETURNS TRIGGER AS
$BODY$
	DECLARE
		data TEXT;
		emp empregado%ROWTYPE;
		ultimo_dia_mes INTEGER;
		mes_ano TEXT;
	BEGIN
		FOR emp IN
			SELECT * FROM empregado
		LOOP	
			-- FREQUENCIA
			mes_ano := NEW.per_mes || '/' || NEW.per_ano;
			ultimo_dia_mes := date_part('day', (to_date(mes_ano,'MM/YYYY') + INTERVAL '1 MONTH - 1 day')::date);
			FOR dia IN 1 .. ultimo_dia_mes
			LOOP
				data := dia || '/' || mes_ano;
				INSERT INTO frequencia(freq_data, emp_matricula)
				VALUES(to_date(data, 'DD/MM/YYYY'), emp.emp_matricula);
			END LOOP;

			-- BANCO DE HORAS
			INSERT INTO banco_horas(ban_ano, ban_mes, emp_matricula, ban_total_horas)
			VALUES(NEW.per_ano, NEW.per_mes, emp.emp_matricula, 0);
		END LOOP;

		RETURN NEW;
	END;
$BODY$
LANGUAGE 'PLPGSQL';


CREATE TRIGGER trg_criar_periodo
AFTER INSERT
ON periodo
FOR EACH ROW
EXECUTE PROCEDURE prd_criar_periodo();

--------------------------------------------------------------------------------
-- 2.8. Crie um trigger sobre a tabela freqüência com o objetivo de atualizar o banco de horas sempre a freqüência for atualizada.
CREATE OR REPLACE FUNCTION prd_atualizar_frequencia()
RETURNS TRIGGER AS
$BODY$
	DECLARE
		qtd_horas INTEGER;
		mes_ano TEXT;
		matricula empregado.emp_matricula%TYPE;
		mes INTEGER;
		ano INTEGER;
	BEGIN
		mes := date_part('month', NEW.freq_data);
		ano := date_part('year', NEW.freq_data);
		FOR matricula IN
			SELECT emp_matricula FROM empregado
		LOOP	
			SELECT INTO qtd_horas
				(date_part('epoch', freq_hora_saida::time - freq_hora_entrada::time)
				/ 3600
				+ freq_horas_excedentes
				+ freq_horas_noturnas)
			FROM frequencia
			WHERE emp_matricula = matricula AND to_char(freq_data, 'MM/YYYY') = mes || '/' || ano;

			IF NOT FOUND THEN
			    qtd_horas := 0;
			END IF;

			UPDATE banco_horas
			SET ban_total_horas = qtd_horas
			WHERE emp_matricula = matricula AND BAN_ANO = ano AND BAN_MES = mes;
		END LOOP;

		RETURN NEW;
	END;
$BODY$
LANGUAGE 'PLPGSQL';

CREATE TRIGGER trg_atualizar_frequencia
AFTER UPDATE
ON frequencia
FOR EACH ROW
EXECUTE PROCEDURE prd_atualizar_frequencia();

--------------------------------------------------------------------------------
-- 2.9. Crie um trigger para que ao se excluir um período sejam excluídas também todas as freqüências referentes a este período. Deve-se excluir também todas as tuplas no banco de horas referentes a este período.
CREATE OR REPLACE FUNCTION prd_excluir_periodo()
RETURNS TRIGGER AS
$BODY$
	DECLARE
		data_inicial DATE;
		data_final DATE;
		mes INTEGER;
		ano INTEGER;
	BEGIN
		mes := OLD.per_mes;
		ano := OLD.per_ano;

		data_inicial := to_date('01/' || mes || '/' || ano, 'DD/MM/YYYY');
		data_final := (date_trunc('MONTH', data_inicial) + INTERVAL '1 MONTH - 1 day')::date;

		DELETE FROM frequencia WHERE freq_data BETWEEN data_inicial AND data_final;
		DELETE FROM banco_horas WHERE ban_ano = ano AND ban_mes = mes;

		RETURN OLD;
	END;
$BODY$
LANGUAGE 'PLPGSQL';

CREATE TRIGGER trg_excluir_periodo
BEFORE DELETE
ON periodo
FOR EACH ROW
EXECUTE PROCEDURE prd_excluir_periodo();

--------------------------------------------------------------------------------
-- 2.10. Crie um trigger para manter a seguinte regra de negócio. Cada empregado que trabalha em um dia feriado deve receber seis horas no banco de horas. Além disso, cada hora extra em um feriado vale o dobro no banco de horas. Considere a inserção e a remoção de um feriado.
CREATE OR REPLACE FUNCTION prd_calcular_feriado()
RETURNS TRIGGER AS
$BODY$
	DECLARE
		mes INTEGER;
		ano INTEGER;
		freq frequencia%ROWTYPE;
	BEGIN
		mes := date_part('month', OLD.fer_data);
		ano := date_part('year', OLD.fer_data);

		FOR freq IN
			SELECT *
			FROM frequencia
			WHERE freq_data = OLD.fer_data
			AND freq_hora_entrada IS NOT NULL
			OR freq_hora_saida IS NOT NULL
		LOOP
			IF TG_OP = 'INSERT' THEN
				UPDATE banco_horas
				SET ban_total_horas = (ban_total_horas + 6 + freq.freq_horas_excedentes)
				WHERE ban_ano = ano AND ban_mes = mes AND emp_matricula = freq.emp_matricula;
			ELSEIF TG_OP = 'DELETE' THEN
				UPDATE banco_horas
				SET ban_total_horas = (ban_total_horas - 6 - freq.freq_horas_excedentes)
				WHERE ban_ano = ano AND ban_mes = mes AND emp_matricula = freq.emp_matricula;
			END IF;
		END LOOP;


		RETURN NEW;
	END;
$BODY$
LANGUAGE 'PLPGSQL';

CREATE TRIGGER trg_calcular_feriado
AFTER INSERT OR DELETE
ON feriado
FOR EACH ROW
EXECUTE PROCEDURE prd_calcular_feriado();

--------------------------------------------------------------------------------
