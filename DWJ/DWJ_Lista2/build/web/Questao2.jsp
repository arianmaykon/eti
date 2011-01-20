<%@page import="br.ufc.eti.eti2010.dwj.lista2.Calculadora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="calc" class="br.ufc.eti.eti2010.dwj.lista2.Calculadora" />
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ETI2010 - DWJ - Lista 2: Questão 2</title>
    </head>
    <body>
        <jsp:setProperty name="calc" property="*" />

        <h1>Questão 2</h1>
        <br />
        Digite os números e a operação desejada e pressione "Calcular".
        <br />
        <br />
        <form action="/DWJ_Lista2/Questao2.jsp" method="post">
            <label for="valor1">V1:</label>
            <input type="text" name="valor1" id="valor1" />
            <label for="valor2">V2:</label>
            <input type="text" name="valor2" id="valor2" />
            <label for="operacao">Operação:</label>
            <select name="operacao" id="operacao">
                <option value="som">Somar</option>
                <option value="sub">Subtrair</option>
                <option value="mul">Multiplicar</option>
                <option value="div">Dividir</option>
            </select>
            <br />
            <br />
            <label for="resultado">Resultado:</label>
            <input type="text" name="resultado" id="resultado" value="<%=calc.processar().toString() %>" />
            <input type="submit" name="btn" value="Calcular" />
            <input type="submit" name="btn" value="Salvar M" />
            <input type="submit" name="btn" value="Recuperar M" />
        </form>
    </body>
</html>