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
    
    public static void showResults(HttpServletRequest req, HttpServletResponse resp, Double stdDev, String values, Double mean)
            throws ServletException, IOException {
        resp.getWriter().println("<b>List:</b> "+values +"<br>");
        resp.getWriter().println("<b>Mean:</b> "+mean + "<br>");
        resp.getWriter().println("<b>STD: </b> "+stdDev + "<br>");
    }
    
    public static void error(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.getWriter().println("Error!!!");
    }
}
