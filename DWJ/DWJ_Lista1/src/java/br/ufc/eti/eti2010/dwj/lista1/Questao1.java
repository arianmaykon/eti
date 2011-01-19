package br.ufc.eti.eti2010.dwj.lista1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Arian Maykon de Araújo Diógenes <arian.maykon@gmail.com>
 */
public class Questao1 extends HttpServlet {
   
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

            Object teste = request.getSession().getAttribute("comentarios");
            Vector comentarios;
            String comentario;
            if (teste == null) {
                comentarios = new Vector();
            } else {
                comentarios = (Vector) teste;
            }
            if (request.getMethod().equalsIgnoreCase("POST")) {
                comentario = request.getParameter("txtComentario");
                if (comentario != null && !comentario.isEmpty()) {
                    comentarios.add(comentario);
                }
            }

            out.println("<html>");
            out.println("<head>");
            out.println("<title>ETI2010 - DWJ - Lista 1: Questão 1</title>");
            out.println("</head>");
            out.println("<body>");
                out.println("<h1>Lista de Comentários (Questão 1)</h1>");
                out.println("<br />");
                Iterator it = comentarios.iterator();
                while(it.hasNext()) {
                    comentario = (String) it.next();
                    out.println("<br />" + comentario + "<br />------");
                }
                out.println("<hr />");
                out.println("<form action='/DWJ_Lista1/Questao1' method='post'>");
                    out.println("<label for='txtComentario'>Comentário:</label><input type='text' name='txtComentario' id='txtComentario' size='40' />");
                    out.println("<br />");
                    out.println("<input type='submit' value='Inserir Comentário' />");
                out.println("</form>");
            out.println("</body>");
            out.println("</html>");

            request.getSession().setAttribute("comentarios", comentarios);
        } catch(Exception e) {
            out.println(e.getMessage());
            out.println("<hr />");
//            out.println(e.printStackTrace());
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
