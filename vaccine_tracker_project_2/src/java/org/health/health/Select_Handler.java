/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.health.health;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 *
 * @author HP
 */
public class Select_Handler extends SimpleTagSupport {

    private String table;
    private String where;
    private String displayformat;

    /**
     * Called by the container to invoke this tag.The implementation of this
 method is provided by the tag library developer, and handles all tag
 processing, body iteration, etc.
     * @throws jakarta.servlet.jsp.JspException
     */
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException ex){
             System.out.println(ex);
        }
        
        String retrieveQuery = "SELECT * FROM " + table + " ORDER BY id DESC";
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccine_tracker","root","");  
            Statement st= con.createStatement();
            
            //health centers results
            ResultSet r = st.executeQuery(retrieveQuery);
            int x = 1;
            
            if("table".equals(displayformat)){
                
                out.println("<table class='table'>"
                    + "<tr>"
                    + "<th>ID</th>"
                    + "<th>HEALTH CENTER</th>"
                    + "<th>LOCATION</th>"
                    + "<th>REG.DATE</th>"
                    + "<th>ACTION</th>"
                    + "</tr>");
                         
                    while(r.next()){
                        out.println("<tr><td>"+r.getString("id")+"</td>");
                        out.println("<td>"+r.getString("name")+"</td>");
                        out.println("<td>"+r.getString("location")+"</td>");
                        out.println("<td>"+r.getString("added_on")+"</td>");
                        out.println("<td><a type='button' class='btn btn-sm btn-primary' href='/vaccine_tracker_project_2/update_health_center.jsp?id="+r.getString("id")+"&hc="+r.getString("name")+"&loc="+r.getString("location")+"'><i class=\"fa fa-pencil-square-o\" aria-hidden=\"true\"></i></a> ");
        
                    }
                out.println("</table>");
            
                st.close();
                con.close();
                
            }else{
               
                out.println("<ol>");
                while(r.next()){
                        out.println("<li>");
                        out.println("<p>"+r.getString("id")+"</p>");
                        out.println("<p>"+r.getString("name")+"</p>");
                        out.println("<p>"+r.getString("location")+"</p>");
                        out.println("<p>"+r.getString("added_on")+"</p>");
                        out.println("<p><a type='button' class='btn btn-sm btn-primary' href='/vaccine_tracker_project/update_health_center.jsp?id="+r.getString("id")+"&hc="+r.getString("name")+"&loc="+r.getString("location")+"'>UPDATE</p> ");
                        out.println("</li>");
                }
                out.println("</ol>");
                st.close();
                con.close();
            }
            
            
        } catch (SQLException ex) {
            out.println(ex);
        }
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public void setDisplayformat(String displayformat) {
        this.displayformat = displayformat;
    }
    
}
