<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ETI2010 - DWJ - Lista 2: Questão 1</title>
    </head>
    <body>
        <form action="/DWJ_Lista2/Questao1.jsp" method="post">
            <label for="texto">Texto:</label>
            <input type="text" name="texto" />
            <br />
            <select name="operacao">
                <option value="toupper">Conversão para maiúsculas</option>
                <option value="tolower">Conversão para minúsculas</option>
                <option value="invert">Inversão na ordem dos caracteres</option>
            </select>
            <br />
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>