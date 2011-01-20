package br.ufc.eti.eti2010.dwj.lista1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Arian Maykon de Araújo Diógenes
 */
public class Questao3 extends HttpServlet {

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
            if (request.getMethod().equalsIgnoreCase("POST")) {
                String comentario = request.getParameter("txtComentario");
                String tema = request.getParameter("txtTema");

                String operacao = request.getParameter("operation");

                if (operacao.equalsIgnoreCase("addtema")) {
                    this.insertTema(tema);
                } else {
                    tema = request.getParameter("cbTema");
                    this.insertComentario(new Integer(tema), comentario);
                }
            }

            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
            out.println("<title>ETI2010 - DWJ - Lista 1: Questão 3</title>");
            out.println("</head>");
            out.println("<body>");
                out.println("<h1>Temas de discussão (Questão 3)</h1>");
                out.println("<br />");
                out.println("<form action='/DWJ_Lista1/Questao3' method='post'>");
                    out.println("<input type='hidden' name='operation' value='addtema' />");
                    out.println("<label for='txtTema'>Tema:</label><input type='text' name='txtTema' id='txtTema' size='40' />");
                    out.println("<br />");
                    out.println("<input type='submit' value='Inserir Tema' />");
                out.println("</form>");

                out.println("<hr />");

                out.println("<h1>Comente um tema</h1>");
                out.println("<br />");
                out.println("<form action='/DWJ_Lista1/Questao3' method='post'>");
                    out.println("<input type='hidden' name='operation' value='addcomentario' />");
                    out.println("<label for='cbTema'>Tema:</label>");
                    out.println("<select name='cbTema' id='cbTema'>");
                        out.println("<option value=''>Selecione...</option>");

                        ResultSet temas = this.getTemas();
                        while (temas.next()) {
                            out.println("<option value='" + temas.getString("id") + "'>" + temas.getString("nome") + "</option>");
                        }

                    out.println("</select>");
                    out.println("<br />");
                    out.println("<label for='txtComentario'>Comentário:</label><input type='text' name='txtComentario' id='txtComentario' size='40' />");
                    out.println("<br />");
                    out.println("<input type='submit' value='Inserir Comentário' />");
                out.println("</form>");

                out.println("<hr />");


//                temas.first();
                temas.beforeFirst();
                while (temas.next()) {
                    out.println("<strong>TEMA:</strong>" + temas.getString("nome") + "<br />");
                    out.println("<strong>COMENTÁRIOS:</strong><br />");

                    ResultSet comentarios = this.getComentariosFromTema(temas.getString("id"));
                    while(comentarios.next()) {
                        out.println("<br />" + comentarios.getString("conteudo") + "<br />------");
                    }
                    out.println("------------------------------<br />");
                }
            out.println("</body>");
            out.println("</html>");
        } catch(Exception e) {
            out.println(e.getMessage());
            out.println("<hr />");
        } finally {
            out.close();
        }
    }

    private int insertTema(String tema) {
        Connection con = this.getConnection();

        int ret = 0;
        try {
            if (!tema.equals("")) {
                String sql = "INSERT INTO temas (nome) VALUES (?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, tema);
                ret = stmt.executeUpdate();
System.out.println("INSERINDO TEMA=" + ret);
            }
        } catch(Exception ex) {
            System.err.println("Exception: " + ex.getMessage());
        }
        return ret;
    }

    private int insertComentario(Integer idTema, String comentario) {
        Connection con = this.getConnection();

        int ret = 0;
        try {
            if (!idTema.equals(new Integer(0)) && !comentario.equals("")) {
                String sql = "INSERT INTO comentarios (id_tema, conteudo) VALUES (?, ?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, idTema);
                stmt.setString(2, comentario);
                ret = stmt.executeUpdate();
System.out.println("INSERINDO COMENTARIO=" + ret);
            }
        } catch(Exception ex) {
            System.err.println("Exception: " + ex.getMessage());
        }
        return ret;
    }

    private ResultSet getTemas() {
        Connection con = this.getConnection();

        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM temas";
            PreparedStatement stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery();
        } catch(Exception ex) {
            System.err.println("Exception: " + ex.getMessage());
        }
        return rs;
    }

    private ResultSet getComentariosFromTema(String id) {
        Connection con = this.getConnection();

        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM comentarios WHERE id_tema = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));
            rs = stmt.executeQuery();
        } catch(Exception ex) {
            System.err.println("Exception: " + ex.getMessage());
        }
        return rs;
    }

    private Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/eti2010";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
        Connection con = null;
        try {
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "admin");
            props.setProperty("charSet", "UTF8");
            props.setProperty("allowEncodingChanges", "true");
            con = DriverManager.getConnection(url, props);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        System.out.println("Conexão aberta");

        return con;
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
