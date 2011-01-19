package br.ufc.eti.eti2010.dwj.lista1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Arian Maykon de Araújo Diógenes <arian.maykon@gmail.com>
 */
public class Questao2 extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            Object teste = request.getSession().getAttribute("temas");

            HashMap<String, Vector> temas;
            Vector comentarios;

            String comentario;
            String tema;

            if (teste == null) {
                temas = new HashMap<String, Vector>();
            } else {
                temas = (HashMap<String, Vector>) teste;
            }

            if (request.getMethod().equalsIgnoreCase("POST")) {
                comentario = request.getParameter("txtComentario");
                tema = request.getParameter("txtTema");
                String operacao = request.getParameter("operation");
                if (operacao.equalsIgnoreCase("addtema")) {
                    if (tema != null) {
                        //TODO: Erro quando o tema contém caracteres especiais.
                        temas.put(tema, new Vector());
                    }
                } else {
                    tema = request.getParameter("cbTema");
                    if (tema != null) {
                        for(Map.Entry entry: temas.entrySet()) {
                            if (((String)entry.getKey()).equalsIgnoreCase(tema)) {
                                comentarios = (Vector) entry.getValue();
                                comentarios.add(comentario);
                            }
                        }
                    }
                }
            }

            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />");
            out.println("<title>ETI2010 - DWJ - Lista 1: Questão 2</title>");
            out.println("</head>");
            out.println("<body>");
                out.println("<h1>Temas de discussão (Questão 2)</h1>");
                out.println("<br />");
                out.println("<form action='/DWJ_Lista1/Questao2' method='post'>");
                    out.println("<input type='hidden' name='operation' value='addtema' />");
                    out.println("<label for='txtTema'>Tema:</label><input type='text' name='txtTema' id='txtTema' size='40' />");
                    out.println("<br />");
                    out.println("<input type='submit' value='Inserir Tema' />");
                out.println("</form>");

                out.println("<hr />");

                out.println("<h1>Comente um tema</h1>");
                out.println("<br />");
                out.println("<form action='/DWJ_Lista1/Questao2' method='post'>");
                    out.println("<input type='hidden' name='operation' value='addcomentario' />");
                    out.println("<label for='cbTema'>Tema:</label>");
                    out.println("<select name='cbTema' id='cbTema'>");
                        out.println("<option value=''>Selecione...</option>");
                        for(Map.Entry entry: temas.entrySet()) {
                            out.println("<option value='" + entry.getKey() + "'>" + entry.getKey() + "</option>");
                        }
                        out.println("<option value=''></option>");
                    out.println("</select>");
                    out.println("<br />");
                    out.println("<label for='txtComentario'>Comentário:</label><input type='text' name='txtComentario' id='txtComentario' size='40' />");
                    out.println("<br />");
                    out.println("<input type='submit' value='Inserir Comentário' />");
                out.println("</form>");

                out.println("<hr />");

                Iterator it;
                for(Map.Entry entry: temas.entrySet()) {
                    comentarios = (Vector) entry.getValue();
                    out.println("<strong>TEMA:</strong>" + entry.getKey() + "<br />");
                    out.println("<strong>COMENTÁRIOS:</strong><br />");
                    it = comentarios.iterator();
                    while(it.hasNext()) {
                        comentario = (String) it.next();
                        out.println("<br />" + comentario + "<br />------");
                    }
                    out.println("<br />------------------------------<br />");
                }
            out.println("</body>");
            out.println("</html>");

            request.getSession().setAttribute("temas", temas);
        } catch(Exception e) {
            out.println(e.getMessage());
            out.println("<hr />");
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
