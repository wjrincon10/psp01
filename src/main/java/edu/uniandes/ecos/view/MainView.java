/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.ecos.view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrador
 */
public class MainView {
     public static void showHome(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        PrintWriter pw = resp.getWriter();
        pw.write("<html>");
        pw.println("<h1>PSP01 Program!</h1>");
        
        pw.write("<form action=\"calcLine\" method=\"post\"> \n" +
"    <input type=\"submit\" value=\"Calcular Lineas\">\n" +
"</form> ");
        pw.write("</html>");

    }
    
    public static void showResults(HttpServletRequest req, HttpServletResponse resp, String vlrProgram1, String vlrProgram2)
            throws ServletException, IOException {
        resp.getWriter().println("<b>Programa 1:</b> "+ vlrProgram1 +"<br>");
        resp.getWriter().println("<b>Programa 2:</b> "+ vlrProgram2 + "<br>");
    }
    
    public static void error(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.getWriter().println("Error!!!");
    }
}
