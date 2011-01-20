<%@page import="br.ufc.eti.eti2010.dwj.lista2.Sentenca"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String alterada = null;
    if (request.getMethod().equalsIgnoreCase("POST")) {
        String texto = request.getParameter("texto");
        String operacao= request.getParameter("operacao");

        if (!texto.equalsIgnoreCase("") && !operacao.equalsIgnoreCase("")) {

            Sentenca sentenca = new Sentenca();
            sentenca.setTexto(texto);

            if (operacao.equalsIgnoreCase("toupper")) {
                alterada = sentenca.toUpper();
            } else if (operacao.equalsIgnoreCase("tolower")) {
                alterada = sentenca.toLower();
            } else if (operacao.equalsIgnoreCase("invert")) {
                alterada = sentenca.invert();
            }
        }
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ETI2010 - DWJ - Lista 2: Questão 1</title>
    </head>
    <body>
        <h1>Questão 1</h1>
        <br />
        <form action="/DWJ_Lista2/Questao1.jsp" method="post">
            <label for="texto">Texto:</label>
            <input type="text" name="texto" id="texto" />
            <br />
            <label for="operacao">Operação:</label><select name="operacao" id="operacao">
                <option value="toupper">Conversão para maiúsculas</option>
                <option value="tolower">Conversão para minúsculas</option>
                <option value="invert">Inversão na ordem dos caracteres</option>
            </select>
            <br />
            <input type="submit" value="Enviar" />
        </form>
        <%
            if (alterada != null) {
                out.println("<br />");
                out.println("<h3>String alterada = </h3>");
                out.println(alterada);
            }
        %>
    </body>
</html>