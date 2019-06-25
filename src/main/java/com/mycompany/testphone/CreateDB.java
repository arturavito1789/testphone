package com.mycompany.testphone;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Артур
 */
@WebServlet(urlPatterns = {"/CreateDB"})
public class CreateDB extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Servlet</title>");            
        out.println("</head>");
        out.println("<body>");
        String DB_URL = "jdbc:postgresql://ec2-54-247-189-1.eu-west-1.compute.amazonaws.com/d6fiu0pc67d9bi";
        String USER = "sdtrgakfniagrm";
        String PASS = "1fe29b0c878ff780c2fcdd7c9d9c1d1f351c3e7a659d9e070c547d3f040696be";
        boolean res_operation = false;
        try {
		Class.forName("org.postgresql.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
		e.printStackTrace();
	}
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("подключились");
            Statement stetmant = connection.createStatement();
            String qs = "CREATE TABLE IF NOT EXISTS  users (name character varying, id integer NOT NULL, phone character varying, foto bytea[], CONSTRAINT fk_id PRIMARY KEY (id)) WITH ( OIDS=FALSE )";
            res_operation = stetmant.execute(qs);
            System.out.println("результат создания таблицы" + res_operation);
            res_operation = stetmant.execute("CREATE SEQUENCE IF NOT EXISTS users_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 2 CACHE 1");
            System.out.println("результат создания последовательности " + res_operation);
           // String sql = "INSERT INTO users (name,phone) VALUES ('Виктор', '567888888')";
           // int i = stetmant.executeUpdate(sql);
           // out.println("<h1> update table: " +  i + "</h1>" );
  	} catch (SQLException e) {
	    System.out.println("Connection Failed");
	    e.printStackTrace();
	}
 
        
       
        out.println("<h1> Create table: " + res_operation + "</h1>");
        out.println("</body>");
        out.println("</html>");
            
            
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
